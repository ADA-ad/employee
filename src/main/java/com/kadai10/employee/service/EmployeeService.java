package com.kadai10.employee.service;

import com.kadai10.employee.controller.request.EmployeeUpdateRequest;
import com.kadai10.employee.entity.Employee;
import com.kadai10.employee.exception.EmployeeAlreadyExistsException;
import com.kadai10.employee.exception.EmployeeNotFoundException;
import com.kadai10.employee.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

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
    public Optional<Employee> findById(Integer id){
        return Optional.ofNullable(employeeMapper.findById(id).orElseThrow(() -> new EmployeeNotFoundException(
                "ユーザーは存在しないです。")));
    }

    /**
     * 指定された年齢に対応するユーザーを取得するメソッド。
     * @param age 取得したいユーザーの年齢
     * @return 指定された年齢に対応するユーザー情報
     */
    public List<Employee> findByAge(Integer age){
        return employeeMapper.findByAge(age);
    }

    /**
     * 指定された住所に対応するユーザーを取得するメソッド
     * @param address 取得したいユーザーの住所
     * @return 指定された住所に対応するユーザー情報
     */

    public List<Employee> findByAddress(String address){
        return employeeMapper.findByAddress(address);
    }

    /**
     * 新しいユーザーを登録するメソッド.
     *
     * @param name       登録するユーザーの名前
     * @param age 登録するユーザーの年齢
     * @param address 登録するユーザーの住所
     * @return 登録されたユーザー情報
     */

    public Employee insert(String name, Integer age, String address) {

        Employee employee = new Employee(name, age, address);
        if (!employeeMapper.findByNameAndAddress(name, address).isEmpty()) {
            throw new EmployeeAlreadyExistsException("ユーザーは重複不可。");
        }
        employeeMapper.insert(employee);
        return employee;
    }

    /**
     * ユーザー情報を更新するメソッド.
     *
     * @param id 更新するユーザーのID
     * @return 更新されたユーザー情報
     * @throws EmployeeNotFoundException            指定されたIDのユーザーが見つからない場合
     *
     */
    public Employee updateEmployee( final Integer id, EmployeeUpdateRequest employeeUpdateRequest) {

        Employee employee = employeeMapper.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("ユーザーは存在しない"));
        if (employeeUpdateRequest.getName() != null) {
            employee.setName(employeeUpdateRequest.getName());
        }
        if (employeeUpdateRequest.getAge() != null) {
            employee.setAge(employeeUpdateRequest.getAge());
        }
        if (employeeUpdateRequest.getAddress() != null) {
            employee.setAddress(employee.getAddress());
        }
        if (!employeeMapper.findByNameAndAddress(employeeUpdateRequest.getName(), employeeUpdateRequest.getAddress()).isEmpty()) {
            throw new EmployeeAlreadyExistsException("ユーザーは重複不可。");
        }

        employeeMapper.updateEmployee(employee);
        return employee;

    }

}
