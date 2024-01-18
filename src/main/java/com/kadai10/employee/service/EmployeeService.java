package com.kadai10.employee.service;

import com.kadai10.employee.entity.Employee;
import com.kadai10.employee.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Employee> findAll() {
        return employeeMapper.findAll();
    }
}
