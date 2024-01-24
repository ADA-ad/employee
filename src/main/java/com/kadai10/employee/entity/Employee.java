package com.kadai10.employee.entity;

import com.kadai10.employee.exception.UserNotFoundException;
import lombok.Data;
/**
 * ＠Dataアノテーションを付与すると、対象クラス内のインスタンス変数に対してgetter/setterでアクセスすることが可能となります。
 * getterはユーザーの情報を取得する。
 * setterはユーザーの情報を定義する。
 */

/**
 * アプリケーション内で扱うユーザー情報を表現するクラスです. ユーザーの識別子、名前、年齢、住所などの属性が含まれます。
 */
@Data
public class Employee {
    /**
     * ユーザーの一意の識別子（ID）を表すフィールド。
     */
    private Integer id;

    /**
     * ユーザーの一意の識別子（名前）を表すフィールド。
     */
    private String name;

    /**
     * ユーザーの一意の識別子（年齢）を表すフィールド。
     */
    private Integer age;

    /**
     * ユーザーの一意の識別子（住所）を表すフィールド。
     */
    private String address;


    /**
     * ユーザーオブジェクトのコンストラクタ。
     *
     * @param id         ユーザーの一意の識別子
     * @param name       ユーザーの名前
     * @param age        ユーザーの年齢
     * @param address        ユーザーの住所
     */

    public Employee(Integer id, String name, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }
    /**
     * 新しいユーザーオブジェクトを作成する静的メソッド.
     *
     * @param name       ユーザーの名前
     * @param age ユーザーの年齢
     * @param address ユーザーの住所
     * @return 新しいユーザーオブジェクト
     */

    public static Employee createEmployee(String name, Integer age, String address) {
        return new Employee(null, name, age,address);
    }


}
