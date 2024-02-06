package com.kadai10.employee.controller;

import com.kadai10.employee.controller.request.EmployeeCreateRequest;
import com.kadai10.employee.controller.request.EmployeeUpdateRequest;
import com.kadai10.employee.controller.response.EmployeeResponse;
import com.kadai10.employee.entity.Employee;
import com.kadai10.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * 従業員関連のHTTPリクエストに対するハンドラーを提供するコントローラークラスです。 従業員情報の取得、登録、更新、削除などの操作を処理します。
 * エンドポイントを提供し、外部からの従業員関連の操作を可能にします。
 */

@RestController
public class EmployeeController {
    /**
     * EmployeeServiceのインスタンスを格納するためのフィールド.。このサービスは従業員関連の機能を提供します。
     */
    private final EmployeeService employeeService;
    /**
     * コントローラのコンストラクタ。
     * @param employeeService 従業員サービスのインスタンス
     */

    public  EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 全ての従業員情報を取得するメソッド.
     * @return 全ての従業員情報のリスト
     */
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }


    /**
     * nameによる従業員情報を取得するメソッド.
     * @param name 従業員idのインスタンス.
     * @return nameによる従業員情報の取得
     */
    @GetMapping("/employees/names")
    public List<Employee> findByName(@RequestParam String name) { return  employeeService.findByName(name);}

    /**
     * idによる従業員情報を取得するメソッド.
     * @param id 従業員idのインスタンス.
     * @return idによる従業員情報の取得
     */
    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable("id") Integer id){ return employeeService.findById(id);}

    /**
     * ageによる従業員情報を取得するメソッド.
     * @param age 従業員idのインスタンス.
     * @return ageによる従業員情報の取得
     */
    @GetMapping("/employees/ages")
    public List<Employee> findByAge(@RequestParam Integer age){ return employeeService.findByAge(age);}

    /**
     * addressによる従業員情報を取得するメソッド.
     * @param address 従業員idのインスタンス.
     * @return addressによる従業員情報の取得
     */
    @GetMapping("/employees/address")
    public List<Employee> findByAddress(@RequestParam String address){ return employeeService.findByAddress(address);}

    /**
     * 新しい従業員を登録するメソッド.
     * @param employeeCreateRequest 従業員登録に使用されるリクエストオブジェクト
     * @param uriBuilder  レスポンスヘッダーに含まれるLocation URI を構築するための UriComponentsBuilder
     */
    @PostMapping("/employees")

    public ResponseEntity<EmployeeResponse> insert(final @RequestBody @Validated EmployeeCreateRequest employeeCreateRequest,
                                                   final UriComponentsBuilder uriBuilder) {
        Employee employee = employeeService.insert(employeeCreateRequest.name(), employeeCreateRequest.age(),
                employeeCreateRequest.address());
        URI location = uriBuilder.path("/employees/{id}").buildAndExpand(employee.getId()).toUri();
        EmployeeResponse body = new EmployeeResponse("従業員を登録しました。");
        return ResponseEntity.created(location).body(body);
    }


    /**
     * 従業員情報を更新するメソッド.
     * @param id            更新対象の従業員ID
     * @param employeeUpdateRequest 従業員情報の更新に使用されるリクエストオブジェクト
     * @param uriBuilder    レスポンスヘッダーに含まれるLocation URI を構築するための UriComponentsBuilder
     * @return HTTP 201 Created ステータスでレスポンスされる従業員情報
     */
    @PatchMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponse> updateUser(final @PathVariable @Valid Integer id,
                                                       final @RequestBody @Valid EmployeeUpdateRequest employeeUpdateRequest,
                                                       final UriComponentsBuilder uriBuilder) {
        Employee employee = employeeService.updateEmployee(id, employeeUpdateRequest);
        URI location = uriBuilder.path("/employees/{id}").buildAndExpand(employee.getId()).toUri();
        EmployeeResponse body = new EmployeeResponse("従業員を更新しました。");
        return ResponseEntity.created(location).body(body);
    }


}
