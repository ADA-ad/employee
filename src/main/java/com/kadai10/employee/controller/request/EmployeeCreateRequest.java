package com.kadai10.employee.controller.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ユーザー情報のリクエストを表すクラスです. このクラスは新しいユーザーの作成の要求を受け取ります。
 * @param name  ユーザーの名前を表すフィールド。
 * @param age  ユーザーの年齢を表すフィールド
 * @param address  ユーザーの住所を表すフィールド
 */

public record EmployeeCreateRequest (
        /**
         * EmployeeCreateRequest オブジェクトを生成するためのコンストラクタ.
         *
         * @param name ユーザーの名前
         * @param age ユーザーの年齢
         * @param address ユーザーの住所
         *
         */
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
