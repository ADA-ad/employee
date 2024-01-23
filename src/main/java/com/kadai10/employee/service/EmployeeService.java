package com.kadai10.employee.service;

import com.kadai10.employee.entity.Employee;
import com.kadai10.employee.exception.UserNotFoundException;
import com.kadai10.employee.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * システム内のユーザーサービスを提供するクラスです。 ユーザー関連の操作や処理を担当し、データベースからユーザー情報を取得・管理します。
 */
@Service
public class EmployeeService {
    /**
     * ユーザーマッパーへの参照を保持するためのフィールド。
     */
    private final EmployeeMapper employeeMapper;
    /**
     * UserServiceのコンストラクタ。
     * @param employeeMapper ユーザーマッパーへの参照。
     */
    public EmployeeService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    /**
     * ユーザを全て取得する。
     * @return 全てのユーザーのリスト
     */
    public List<Employee> findAll() {
        return employeeMapper.findAll();
    }

    /**
     * 指定された名前に対応するユーザー情報を取得するメソッド。
     * @param name 取得したいユーザーの名前
     * @return 指定された名前に対応するユーザー情報
     */
    public List<Employee> findByName(String name) {
        return employeeMapper.findByName(name);
    }

    /**
     * 指定されたIDに対応するユーザーを取得するメソッド。
     * @param id 取得したいユーザーのid
     * @return 指定されたIDに対応するユーザー情報。存在しない場合は空のOptionalを返す
     */
    public Employee findById(Integer id){
        return employeeMapper.findById(id);
    }

    /**
     * 指定された年齢に対応するユーザーを取得するメソッド。
     * @param age 取得したいユーザーの年齢
     * @return 指定された年齢に対応するユーザー情報
     */
    public List<Employee> findByAge(Integer age){ return employeeMapper.findByAge(age);}

    /**
     * 指定された住所に対応するユーザーを取得するメソッド
     * @param address 取得したいユーザーの住所
     * @return 指定された住所に対応するユーザー情報
     */

    public List<Employee> findByAddress(String address){ return employeeMapper.findByAddress(address);}

    /**
     * 新しいユーザーを登録するメソッド.
     *
     * @param name       登録するユーザーの名前
     * @param age 登録するユーザーの年齢
     * @param address 登録するユーザーの住所
     * @return 登録されたユーザー情報
     */

    public Employee insert(String name, Integer age, String address) {
        Employee employee = Employee.createEmployee(name, age, address);
        employeeMapper.insert(employee);
        return employee;
    }
}
