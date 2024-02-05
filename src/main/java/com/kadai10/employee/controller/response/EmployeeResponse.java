package com.kadai10.employee.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * EmployeeCreateResponse クラスは、従業員のレスポンスを表現するためのクラスです.
 */
@Data
@AllArgsConstructor
public class EmployeeResponse {
    /**
     * 従業員レスポンスを作成するためのコンストラクタです.
     * @param message メッセージ

     */
    private String message;

}
