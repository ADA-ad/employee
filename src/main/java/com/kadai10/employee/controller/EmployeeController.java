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
     *
     * @param id ユーザーidのインスタンス.
     * @return idによるユーザー情報の取得
     */
    @GetMapping("/employees/{id}")
    public Optional<Employee> findById(@PathVariable("id") Integer id){ return employeeService.findById(id);}

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
    public List<Employee> findByAddress(@RequestParam String address){ return employeeService.findByAddress(address);}

    /**
     * 新しいユーザーを登録するメソッド.
     *
     * @param employeeCreateRequest ユーザー登録に使用されるリクエストオブジェクト
     * @param uriBuilder  レスポンスヘッダーに含まれるLocation URI を構築するための UriComponentsBuilder
     */
    @PostMapping("/employees")
    public ResponseEntity<EmployeeResponse> insert(final @RequestBody @Validated EmployeeCreateRequest employeeCreateRequest,
                                                   final UriComponentsBuilder uriBuilder) {
        Employee employee = employeeService.insert(employeeCreateRequest.getName(), employeeCreateRequest.getAge(),
                employeeCreateRequest.getAddress());
        URI location = uriBuilder.path("/employees/{id}").buildAndExpand(employee.getId()).toUri();
        EmployeeResponse body = new EmployeeResponse( employee.getName(), employee.getAge(),
                employee.getAddress() + "を登録しました");
        return ResponseEntity.created(location).body(body);
    }

    /**
     * ユーザー情報を更新するメソッド.
     * @param id            更新対象のユーザーID
     * @param employeeUpdateRequest ユーザー情報の更新に使用されるリクエストオブジェクト
     * @param uriBuilder    レスポンスヘッダーに含まれるLocation URI を構築するための UriComponentsBuilder
     * @return HTTP 201 Created ステータスでレスポンスされるユーザー情報
     */
    @PatchMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponse> updateUser(final @PathVariable @Valid Integer id,
                                                       final @RequestBody EmployeeUpdateRequest employeeUpdateRequest,
                                                       final UriComponentsBuilder uriBuilder) {
        Employee employee = employeeService.updateEmployee(id, employeeUpdateRequest);
        URI location = uriBuilder.path("/employees/{id}").buildAndExpand(employee.getId()).toUri();
        EmployeeResponse body = new EmployeeResponse(employee.getName(), employee.getAge(), employee.getAddress() + "を更新しました");
        return ResponseEntity.created(location).body(body);
    }


}
