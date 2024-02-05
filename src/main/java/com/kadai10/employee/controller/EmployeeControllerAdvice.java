package com.kadai10.employee.controller;

import com.kadai10.employee.exception.EmployeeAlreadyExistsException;
import com.kadai10.employee.exception.EmployeeNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
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
public class EmployeeControllerAdvice {
    /**
     * 従業員が見つからない場合の例外ハンドリングメソッド。
     * @param e       従業員が見つからない例外
     * @param request HTTPリクエスト
     * @return エラーレスポンス
     */
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(
            final EmployeeNotFoundException e, final @NotNull HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getLocalizedMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * 入力が不足している場合の例外ハンドリングメソッド.
     * @param e       入力が不足している例外
     * @return エラーレスポンス
     */
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

    /**
     * 重複した従業員が見つかった場合の例外ハンドリングメソッド。
     * @param e       重複した職業が見つかった例外
     * @param request HTTPリクエスト
     * @return エラーレスポンス
     */
    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmployeeAlreadyExistsException(
            final EmployeeAlreadyExistsException e, final HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.CONFLICT.value()),
                "error", HttpStatus.CONFLICT.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }


}
