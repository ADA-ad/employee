package com.kadai10.employee.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.kadai10.employee.entity.Employee;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeMapperTest {
    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    @DataSet(value = "datasets/employees.yml")
    @Transactional
    void すべてのユーザーが取得できること() {
        List<Employee> employees = employeeMapper.findAll();
        assertThat(employees)
                .hasSize(5)
                .contains(
                        new Employee("鈴木 碧", 16, "東京都品川区1-1-1"),
                        new Employee("佐藤 陽葵", 20, "静岡県伊豆市1-2-31"),
                        new Employee("高橋 大雅", 18, "大阪府和泉市2-2-3"),
                        new Employee("田中 ゆいと", 25, "奈良県生駒市3-1-3"),
                        new Employee("伊藤 はると", 28, "京都府京都市4-1-3"));
    }
}
