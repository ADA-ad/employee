package com.kadai10.employee.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * EmployeeCreateResponse クラスは、ユーザーのレスポンスを表現するためのクラスです.
 */
@Data
@AllArgsConstructor
public class EmployeeResponse {
    /**
     * ユーザーレスポンスを作成するためのコンストラクタです.
     * @param message メッセージ

     */
    private String message;

}
