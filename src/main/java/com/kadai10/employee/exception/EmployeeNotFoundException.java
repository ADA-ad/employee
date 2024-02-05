package com.kadai10.employee.exception;

/**
 * 従業員が見つからない場合にスローされる例外クラスです。従業員が特定の条件を満たさない場合や、IDに対応する従業員が存在しない場合に使用されます。
 */
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(final String message) {
        super(message);
    }
}
