package com.kadai10.employee.mapper;

import com.kadai10.employee.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

}
