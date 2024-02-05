package com.kadai10.employee.exception;

/**
 * 従業員が既に存在する場合にスローされる例外クラスです。従業員が特定の条件を満たさない場合や、IDに対応する従業員が存在する場合に使用されます。
 */
public class EmployeeAlreadyExistsException extends RuntimeException{
    /**
     * 従業員が既に存在する場合にスローされる例外クラス. この例外は、指定された職業がデータベースやリポジトリで見つかった場合にスローされます
     * @param message 例外メッセージ
     */

    public EmployeeAlreadyExistsException(final String message) {
        super(message);
    }
}
