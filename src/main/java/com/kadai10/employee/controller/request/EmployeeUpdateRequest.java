package com.kadai10.employee.controller.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.annotations.Param;

/**
 * ユーザー情報の更新リクエストを表すレコードクラスです. このクラスはユーザーの名前や年齢、住所などの更新が必要な情報を受け取ります。 ユーザー情報を更新する際に使用されます。
 *
 */
@Data
@AllArgsConstructor
public class EmployeeUpdateRequest {
        @NotBlank(message = "名前を入力してください")
        @Size(max = 8)
        private String name;

        @NotNull(message = "年齢を入力してください")
        @Min(0)
        @Max(200)
        private Integer age;

        @NotBlank(message = "住所を入力してください")
        @Size(max = 50)
        private String address;
}
