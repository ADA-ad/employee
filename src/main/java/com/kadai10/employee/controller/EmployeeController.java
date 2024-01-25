package com.kadai10.employee.controller;

import com.kadai10.employee.controller.request.EmployeeCreateRequest;
import com.kadai10.employee.controller.response.EmployeeCreateResponse;
import com.kadai10.employee.entity.Employee;
import com.kadai10.employee.exception.UserNotFoundException;
import com.kadai10.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * ユーザー関連のHTTPリクエストに対するハンドラーを提供するコントローラークラスです。 ユーザー情報の取得、登録、更新、削除などの操作を処理します。
 * エンドポイントを提供し、外部からのユーザー関連の操作を可能にします。
 */

@RestController
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
        this.employeeService = employeeService;
    }

    /**
     * 全てのユーザー情報を取得するメソッド.
     *
     * @return 全てのユーザー情報のリスト
     */
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }


    /**
     * nameによるユーザー情報を取得するメソッド.
     * @param name ユーザーidのインスタンス.
     * @return nameによるユーザー情報の取得
     */
    @GetMapping("/employees/names")
    public List<Employee> findByName(@RequestParam String name) { return  employeeService.findByName(name);}

    /**
     * idによるユーザー情報を取得するメソッド.
     * @param id ユーザーidのインスタンス.
     * @return idによるユーザー情報の取得
     */
    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable("id") Integer id){ return employeeService.findById(id);}

    /**
     * ageによるユーザー情報を取得するメソッド.
     * @param age ユーザーidのインスタンス.
     * @return ageによるユーザー情報の取得
     */
    @GetMapping("/employees/ages")
    public List<Employee> findByAge(@RequestParam Integer age){ return employeeService.findByAge(age);}

    /**
     * addressによるユーザー情報を取得するメソッド.
     * @param address ユーザーidのインスタンス.
     * @return addressによるユーザー情報の取得
     */
    @GetMapping("/employees/address")
    public Optional<Employee> findByAddress(@RequestParam String address){ return employeeService.findByAddress(address);}

    /**
     * 新しいユーザーを登録するメソッド.
     *
     * @param employeeCreateRequest ユーザー登録に使用されるリクエストオブジェクト
     * @param uriBuilder  レスポンスヘッダーに含まれるLocation URI を構築するための UriComponentsBuilder
     */
    @PostMapping("/employees")
    public ResponseEntity<EmployeeCreateResponse> insert(final @RequestBody @Validated EmployeeCreateRequest employeeCreateRequest,
                                                         final UriComponentsBuilder uriBuilder) {
        Employee employee = employeeService.insert(employeeCreateRequest.name(), employeeCreateRequest.age(),
                employeeCreateRequest.address());
        URI location = uriBuilder.path("/employees/{id}").buildAndExpand(employee.getId()).toUri();
        EmployeeCreateResponse body = new EmployeeCreateResponse(employee.getName(), employee.getAge(),
                employee.getAddress() + " を登録しました");
        return ResponseEntity.created(location).body(body);

    }
}
