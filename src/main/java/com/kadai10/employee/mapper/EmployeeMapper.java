package com.kadai10.employee.mapper;

import com.kadai10.employee.entity.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

/**
 * ユーザーエンティティとデータベースの間でデータの変換を担当するマッパーインターフェースです。ユーザーエンティティのデータベースへの保存や、データベースからの取得などの操作が含まれます。
 */
@Mapper
public interface EmployeeMapper {
    /**
     * ユーザを全て取得する。
     * @return 全てのユーザーのリスト
     */
    @Select("SELECT * FROM employees")
    List<Employee> findAll();

    /**
     * 指定された名前に対応するユーザーを取得する。
     * @param name 取得したいユーザーの名前
     * @return 指定された名前に対応するユーザーを返す
     */
    @Select("SELECT * FROM employees WHERE name LIKE CONCAT('%', #{name}, '%')")
    List<Employee> findByName(String name);

    /**
     * 指定されたIDに対応するユーザーを取得する。
     * @param id 取得したいユーザーのid
     * @return 指定されたIDに対応するユーザーを返す。存在しない場合は空のOptionalを返す
     */
    @Select("SELECT * FROM employees WHERE id = #{id}")
    Optional<Employee> findById(Integer id);

    /**
     * 指定された年齢に対応するユーザーを取得する。
     * @param age 取得したいユーザーの年齢
     * @return 指定された年齢に対応するユーザーを返す
     */
    @Select("SELECT * FROM employees WHERE age = #{age}")
    List<Employee> findByAge(Integer age);

    /**
     * 指定された住所に対応するユーザーを取得する。
     * @param address 取得したいユーザーの住所
     * @return 指定された住所に対応するユーザーを返す
     */
    @Select("SELECT * FROM employees WHERE address LIKE CONCAT('%', #{address}, '%')")
    List<Employee> findByAddress(String address);

    /**
     * ユーザーをデータベースに登録.
     *
     * @param employee 登録するユーザーオブジェクト
     */
    @Insert("INSERT INTO employees (name, age, address) VALUES (#{name}, #{age}, #{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Employee employee);

    /**
     * 指定された名前と住所に対応するユーザーを取得し、重複チェック確認用
     * @param name 登録するユーザーオブジェクト
     * @param address 登録するユーザーオブジェクト
     *
     */
    @Select("SELECT * FROM employees WHERE name = #{name} AND address = #{address}")
    List<Employee> findByNameAndAddress(@Param("name")String name, @Param("address")String address);


    /**
     * データベースのユーザー情報を更新.
     *
     * @param  employee 更新するユーザーオブジェクト
     */
    @Update("UPDATE employees SET name = #{name}, age = #{age}, address = #{address} WHERE id = #{id}")
    void updateEmployee(@Param("name") Employee employee);

}
