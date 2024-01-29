package com.kadai10.employee.exception;
/**
 * 職業が既に存在する場合にスローされる例外クラスです。ユーザーが特定の条件を満たさない場合や、IDに対応するユーザーが存在しない場合に使用されます。
 */
public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(final String message) {
        super(message);
    }
}
