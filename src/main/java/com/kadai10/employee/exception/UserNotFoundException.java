package com.kadai10.employee.exception;

/**
 * ユーザーが見つからない場合にスローされる例外クラスです。ユーザーが特定の条件を満たさない場合や、IDに対応するユーザーが存在しない場合に使用されます。
 */
public class UserNotFoundException extends RuntimeException {
    /**
     * ユーザーが見つからない場合にスローされる例外クラス。 この例外は、指定されたユーザーがデータベースやリポジトリで見つからない場合にスローされます
     *
     * @param message 例外メッセージ
     */
    public UserNotFoundException(final String message) {
        super(message);
    }
}
