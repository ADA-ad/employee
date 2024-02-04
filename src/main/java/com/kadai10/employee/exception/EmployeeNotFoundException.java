package com.kadai10.employee.exception;

/**
 * ユーザーが見つからない場合にスローされる例外クラスです。ユーザーが特定の条件を満たさない場合や、IDに対応するユーザーが存在しない場合に使用されます。
 */
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(final String message) {
        super(message);
    }
}