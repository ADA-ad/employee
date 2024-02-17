package com.kadai10.employee.entity;

import com.kadai10.employee.mapper.EmployeeMapper;
import lombok.Data;
/**
 * ＠Dataアノテーションを付与すると、対象クラス内のインスタンス変数に対してgetter/setterでアクセスすることが可能となります。
 * getterは従業員の情報を取得する。
 * setterは従業員の情報を定義する。
 */

/**
 * アプリケーション内で扱う従業員情報を表現するクラスです. 従業員の識別子、名前、年齢、住所などの属性が含まれます。
 */
@Data
public class Employee {
    /**
     * 従業員の一意の識別子（ID）を表すフィールド。
     */
    private Integer id;

    /**
     * 従業員の一意の識別子（名前）を表すフィールド。
     */
    private String name;

    /**
     * 従業員の一意の識別子（年齢）を表すフィールド。
     */
    private Integer age;

    /**
     * 従業員の一意の識別子（住所）を表すフィールド。
     */
    private String address;


    /**
     * 従業員オブジェクトのコンストラクタ。
     *
     * @param id         従業員の一意の識別子
     * @param name       従業員の名前
     * @param age        従業員の年齢
     * @param address        従業員の住所
     */

    public Employee(Integer id, String name, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    /**
     * コンストラクタのオーバーロードを作成する。
     * @param name
     * @param age
     * @param address
     */
    public Employee(String name, Integer age, String address) {
        this.id = null;
        this.name = name;
        this.age = age;
        this.address = address;
    }


}
