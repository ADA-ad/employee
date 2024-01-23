package com.kadai10.employee.controller.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ユーザー情報のリクエストを表すクラスです. このクラスは新しいユーザーの作成の要求を受け取ります。
 *
 *
 */
@Data
@AllArgsConstructor
public class EmployeeCreateRequest  {

        @NotBlank (message = "名前を入力してください")
        @Size(max = 8)
        private String name;
        @NotBlank(message = "年齢を入力してください")
        @Min(0)
        @Max(200)
        private Integer age;
        @NotBlank(message = "住所を入力してください")
        @Size(max = 50)
        private String address;


}
