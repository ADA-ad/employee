package com.kadai10.employee.controller.request;

import jakarta.validation.constraints.*;

/**
 * 従業員情報のリクエストを表すレコードクラスです. このクラスは新しい従業員の作成の要求を受け取ります。
 *  @param name  従業員の名前を表すフィールド。
 *  @param age  従業員の年齢を表すフィールド。
 *  @param address  従業員の住所を表すフィールド。
 */

public record EmployeeCreateRequest (
        @NotBlank (message = "名前を入力してください")
        @Size(max = 8)
        String name,

        @NotNull(message = "年齢を入力してください")
        @Min(0)
        @Max(200)
        Integer age,

        @NotBlank(message = "住所を入力してください")
        @Size(max = 50)
        String address
) {



}
