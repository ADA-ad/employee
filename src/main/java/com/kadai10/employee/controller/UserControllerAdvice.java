package com.kadai10.employee.controller;

import com.kadai10.employee.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * アプリケーション内で発生する例外に対する処理を提供する. ControllerAdvice として機能するクラスです。
 * 例外処理、エラーハンドリング、および異常状態のレスポンス生成などを行います。
 */
@ControllerAdvice
public class UserControllerAdvice {
    /**
     * ユーザーが見つからない場合の例外ハンドリングメソッド.
     *
     * @param e       ユーザーが見つからない例外
     * @param request HTTPリクエスト
     * @return エラーレスポンス
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(
            final UserNotFoundException e, final @NotNull HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getLocalizedMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<Map<String, String>> errors = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            Map<String, String> error = new HashMap<>();
            error.put("field", fieldError.getField());
            error.put("message", fieldError.getDefaultMessage());
            errors.add(error);
        });
        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }
}
