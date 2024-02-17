package com.kadai10.employee.service;

import com.kadai10.employee.entity.Employee;
import com.kadai10.employee.exception.EmployeeNotFoundException;
import com.kadai10.employee.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    EmployeeService employeeService;
    @Mock
    EmployeeMapper employeeMapper;

    //READ機能のテスト(全件取得)GET
    @Test
    void すべての従業員が取得できること() {
        List<Employee> employees = List.of(
                new Employee("鈴木 碧", 16, "東京都品川区1-1-1"),
                new Employee("佐藤 陽葵", 20, "静岡県伊豆市1-2-31"),
                new Employee("高橋 大雅", 18, "大阪府和泉市2-2-3"),
                new Employee("田中 ゆいと", 25, "奈良県生駒市3-1-3"),
                new Employee("伊藤 はると", 28, "京都府京都市4-1-3"));

        doReturn(employees).when(employeeMapper).findAll();
        List<Employee> actual = employeeService.findAll();
        assertThat(actual).isEqualTo(employees);
        verify(employeeMapper).findAll();
    }

    //READ機能のテスト(指定id)GET
    @Test
    public void 存在するIDを指定したときに正常に従業員情報が返されること() {
        doReturn(Optional.of(new Employee(1,"鈴木 碧", 16, "東京都品川区1-1-1"))).when(employeeMapper).findById(1);
        Employee actual = employeeService.findById(1);
        assertThat(actual).isEqualTo(new Employee(1, "鈴木 碧", 16, "東京都品川区1-1-1"));
        verify(employeeMapper).findById(1);
    }

    //READ機能のテスト(指定idの例外)GET
    @Test
    public void 存在しないIDを指定したときに例外処理が動作すること() throws
         EmployeeNotFoundException {
         doReturn(Optional.empty()).when(employeeMapper).findById(100);
         assertThrows(EmployeeNotFoundException.class, () -> {
         employeeService.findById(100);
         });
         verify(employeeMapper).findById(100);
   }

    //READ機能のテスト(指定name)GET
    @Test
    public void 存在する名前を指定したときに正常に従業員情報が返されること() {
        doReturn(List.of(new Employee(1,"鈴木 碧", 16, "東京都品川区1-1-1"))).when(employeeMapper).findByName("鈴木 碧");
        List<Employee> actual = employeeService.findByName("鈴木 碧");
        assertThat(actual).isEqualTo(List.of(new Employee(1, "鈴木 碧", 16, "東京都品川区1-1-1")));
        verify(employeeMapper).findByName("鈴木 碧");
   }

    //READ機能のテスト(指定age)GET
    @Test
    public void 存在する年齢を指定したときに正常に従業員情報が返されること() {
        doReturn(List.of(new Employee(1,"鈴木 碧", 16, "東京都品川区1-1-1"))).when(employeeMapper).findByAge(16);
        List<Employee> actual = employeeService.findByAge(16);
        assertThat(actual).isEqualTo(List.of(new Employee(1, "鈴木 碧", 16, "東京都品川区1-1-1")));
        verify(employeeMapper).findByAge(16);
    }

    //READ機能のテスト(指定address)GET
    @Test
    public void 存在する住所を指定したときに正常に従業員情報が返されること() {
        doReturn(List.of(new Employee(1,"鈴木 碧", 16, "東京都品川区1-1-1"))).when(employeeMapper).findByAddress("東京都品川区1-1-1");
        List<Employee> actual = employeeService.findByAddress("東京都品川区1-1-1");
        assertThat(actual).isEqualTo(List.of(new Employee(1, "鈴木 碧", 16, "東京都品川区1-1-1")));
        verify(employeeMapper).findByAddress("東京都品川区1-1-1");
    }
}
