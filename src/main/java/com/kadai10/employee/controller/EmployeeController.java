package com.kadai10.employee.controller;

import com.kadai10.employee.entity.Employee;
import com.kadai10.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * ユーザー関連のHTTPリクエストに対するハンドラーを提供するコントローラークラスです。 ユーザー情報の取得、登録、更新、削除などの操作を処理します。
 * エンドポイントを提供し、外部からのユーザー関連の操作を可能にします。
 */
@Controller
public class EmployeeController {
    /**
     * EmployeeServiceのインスタンスを格納するためのフィールド.。このサービスはユーザー関連の機能を提供します。
     */
    private final EmployeeService employeeService;
    /**
     * コントローラのコンストラクタ。
     * @param employeeService ユーザーサービスのインスタンス
     */

    public  EmployeeController(EmployeeService employeeService) {
        this.employeeService =employeeService;
    }
    @RequestMapping
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
}
