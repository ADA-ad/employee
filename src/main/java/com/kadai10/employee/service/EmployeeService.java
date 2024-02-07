package com.kadai10.employee.service;

import com.kadai10.employee.controller.request.EmployeeUpdateRequest;
import com.kadai10.employee.entity.Employee;
import com.kadai10.employee.exception.EmployeeAlreadyExistsException;
import com.kadai10.employee.exception.EmployeeNotFoundException;
import com.kadai10.employee.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * システム内の従業員サービスを提供するクラスです。 従業員関連の操作や処理を担当し、データベースから従業員情報を取得・管理します。
 */
@Service
public class EmployeeService {
    /**
     * 従業員マッパーへの参照を保持するためのフィールド。
     */
    private final EmployeeMapper employeeMapper;
    /**
     * UserServiceのコンストラクタ。
     * @param employeeMapper 従業員マッパーへの参照。
     */
    public EmployeeService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    /**
     * 従業員を全て取得する。
     * @return 全ての従業員のリスト
     */
    public List<Employee> findAll() {
        return employeeMapper.findAll();
    }

    /**
     * 指定された名前に対応する従業員情報を取得するメソッド。
     * @param name 取得したい従業員の名前
     * @return 指定された名前に対応する従業員情報
     */
    public List<Employee> findByName(String name) {
        return employeeMapper.findByName(name);
    }

    /**
     * 指定されたIDに対応する従業員を取得するメソッド。
     * @param id 取得したい従業員のid
     * @return 指定されたIDに対応する従業員情報。存在しない場合は例外ハンドリングでエラーを返す。
     */
    public Employee findById(Integer id){
         return employeeMapper.findById(id).orElseThrow(() -> new EmployeeNotFoundException(
                "従業員は存在しないです。"));
    }

    /**
     * 指定された年齢に対応する従業員を取得するメソッド。
     * @param age 取得したい従業員の年齢
     * @return 指定された年齢に対応する従業員情報
     */
    public List<Employee> findByAge(Integer age){
        return employeeMapper.findByAge(age);
    }

    /**
     * 指定された住所に対応する従業員を取得するメソッド
     * @param address 取得したい従業員の住所
     * @return 指定された住所に対応する従業員情報
     */
    public List<Employee> findByAddress(String address){
        return employeeMapper.findByAddress(address);
    }

    /**
     * 新しい従業員を登録するメソッド.
     * @param name       登録する従業員の名前
     * @param age 登録する従業員の年齢
     * @param address 登録する従業員の住所
     * @return 登録された従業員情報
     */
    public Employee insert(String name, Integer age, String address) {

        Employee employee = new Employee(name, age, address);
        if (!employeeMapper.findByNameAndAddress(name, address).isEmpty()) {
            throw new EmployeeAlreadyExistsException("従業員は重複不可。");
        }
        employeeMapper.insert(employee);
        return employee;
    }

    /**
     * 従業員情報を更新するメソッド.
     * @param id 更新する従業員のID
     * @return 更新された従業員情報
     * @throws EmployeeNotFoundException  指定されたIDの従業員が見つからない場合
     * @throws EmployeeAlreadyExistsException  指定されたIDの従業員が名前と住所が重複した場合
     */
    public Employee updateEmployee(final Integer id, EmployeeUpdateRequest employeeUpdateRequest) {

        Employee employee = employeeMapper.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("従業員は存在しない。"));
        if (employeeUpdateRequest.getName() != null) {
            employee.setName(employeeUpdateRequest.getName());
        }
        if (employeeUpdateRequest.getAge() != null) {
            employee.setAge(employeeUpdateRequest.getAge());
        }
        if (employeeUpdateRequest.getAddress() != null) {
            employee.setAddress(employeeUpdateRequest.getAddress());
        }
        if (!employeeMapper.findByNameAndAddress(employeeUpdateRequest.getName(), employeeUpdateRequest.getAddress()).isEmpty()) {
            throw new EmployeeAlreadyExistsException("従業員は重複不可。");
        }

        employeeMapper.updateEmployee(employee);
        return employee;

    }

    /**
     * 従業員情報を削除するメソッド.
     * @param id 削除するユーザーのID
     * @return 削除されたユーザー情報
     * @throws EmployeeNotFoundException 指定されたIDのユーザーが見つからない場合
     */
    public Employee deleteEmployee(final Integer id) {
        Employee employee = employeeMapper.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("従業員は存在しない。"));
        employeeMapper.deleteEmployee(id);
        return employee;
    }
}
