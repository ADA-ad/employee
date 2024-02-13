package com.kadai10.employee.service;

import com.kadai10.employee.entity.Employee;
import com.kadai10.employee.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    EmployeeService employeeService;
    @Mock
    EmployeeMapper employeeMapper;

    @Test
    void すべてのユーザーが取得できること() {
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
}
