package com.kadai10.employee.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * EmployeeCreateResponse クラスは、ユーザーのレスポンスを表現するためのクラスです.
 */
@Data
@AllArgsConstructor
public class EmployeeCreateResponse {
    /**
     * ユーザーレスポンスを作成するためのコンストラクタです.
     * @param name 名前
     * @param age 年齢
     * @param address 住所
     */

    private String name;
    private Integer age;
    private String address;

}
