package com.kadai10.employee.mapper;

import com.kadai10.employee.entity.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

/**
 * 従業員エンティティとデータベースの間でデータの変換を担当するマッパーインターフェースです。従業員エンティティのデータベースへの保存や、データベースからの取得などの操作が含まれます。
 */
@Mapper
public interface EmployeeMapper {
    /**
     * 従業員を全て取得する。
     * @return 全ての従業員のリスト
     */
    @Select("SELECT * FROM employees")
    List<Employee> findAll();

    /**
     * 指定された名前に対応する従業員を取得する。
     * @param name 取得したい従業員の名前
     * @return 指定された名前に対応する従業員を返す
     */
    @Select("SELECT * FROM employees WHERE name LIKE CONCAT('%', #{name}, '%')")
    List<Employee> findByName(String name);

    /**
     * 指定されたIDに対応する従業員を取得する。
     * @param id 取得したい従業員のid
     * @return 指定されたIDに対応する従業員を返す。存在しない場合は例外ハンドリングでエラーを返す。
     */
    @Select("SELECT * FROM employees WHERE id = #{id}")
    Optional<Employee> findById(Integer id);

    /**
     * 指定された年齢に対応する従業員を取得する。
     * @param age 取得したい従業員の年齢
     * @return 指定された年齢に対応する従業員を返す
     */
    @Select("SELECT * FROM employees WHERE age = #{age}")
    List<Employee> findByAge(Integer age);

    /**
     * 指定された住所に対応する従業員を取得する。
     * @param address 取得したい従業員の住所
     * @return 指定された住所に対応する従業員を返す
     */
    @Select("SELECT * FROM employees WHERE address LIKE CONCAT('%', #{address}, '%')")
    List<Employee> findByAddress(String address);

    /**
     * 従業員をデータベースに登録.
     * @param employee 登録する従業員オブジェクト
     */
    @Insert("INSERT INTO employees (name, age, address) VALUES (#{name}, #{age}, #{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Employee employee);

    /**
     * 指定された名前と住所に対応する従業員を取得し、重複チェック確認用
     * @param name 登録する従業員オブジェクト
     * @param address 登録する従業員オブジェクト
     *
     */
    @Select("SELECT * FROM employees WHERE name = #{name} AND address = #{address}")
    List<Employee> findByNameAndAddress(String name, String address);


    /**
     * データベースの従業員情報を更新.
     * @param  employee 更新する従業員オブジェクト
     */
    @Update("UPDATE employees SET name = #{name}, age = #{age}, address = #{address} WHERE id = #{id}")
    void updateEmployee(Employee employee);

    /**
     * データベースの従業員を削除。
     * @param id 削除するユーザーのid
     */
    @Delete("DELETE FROM employees WHERE id = #{id}")
    void deleteEmployee(Integer id);
}
