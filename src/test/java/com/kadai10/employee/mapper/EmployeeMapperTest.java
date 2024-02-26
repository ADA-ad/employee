package com.kadai10.employee.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.kadai10.employee.entity.Employee;
import com.kadai10.employee.exception.EmployeeAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeMapperTest {
    @Autowired
    EmployeeMapper employeeMapper;

    //READ機能のDBテスト(全件取得)
    @Test
    @DataSet(value = "datasets/employees.yml")
    @Transactional
    void すべての従業員が取得できること() {
        List<Employee> employees = employeeMapper.findAll();
        assertThat(employees)
                .hasSize(5)
                .contains(
                        new Employee(1,"鈴木 碧", 16, "東京都品川区1-1-1"),
                        new Employee(2,"佐藤 陽葵", 20, "静岡県伊豆市1-2-3"),
                        new Employee(3,"高橋 大雅", 18, "大阪府和泉市2-2-3"),
                        new Employee(4,"田中 ゆいと", 25, "奈良県生駒市3-1-3"),
                        new Employee(5,"伊藤 はると", 28, "京都府京都市4-1-3"));
    }

    //READ機能のDBテスト(指定したID)
    @Test
    @DataSet(value = "datasets/employees.yml")
    @Transactional
    void 指定したIDの従業員情報を獲得すること() {
        Optional<Employee> employee = employeeMapper.findById(1);
        assertThat(employee).contains(new Employee(1, "鈴木 碧", 16, "東京都品川区1-1-1"));
    }

    //READ機能のDBテスト(指定したIDが存在しない場合)
    @Test
    @DataSet(value = "datasets/employees.yml")
    @Transactional
    void 存在しないIDを指定する場合に空の情報を獲得すること() {
        Optional<Employee> employee = employeeMapper.findById(100);
        assertThat(employee).isEmpty();
    }

    //READ機能のDBテスト(指定したname)
    @Test
    @DataSet(value = "datasets/employees.yml")
    @Transactional
    void 指定した名前の従業員情報を獲得すること() {
        List<Employee> employee = employeeMapper.findByName("鈴木 碧");
        assertThat(employee).contains(new Employee(1, "鈴木 碧", 16, "東京都品川区1-1-1"));
    }

    //READ機能のDBテスト(指定したage)
    @Test
    @DataSet(value = "datasets/employees.yml")
    @Transactional
    void 指定した年齢の従業員情報を獲得すること() {
        List<Employee> employee = employeeMapper.findByAge(16);
        assertThat(employee).contains(new Employee(1, "鈴木 碧", 16, "東京都品川区1-1-1"));
    }

    //READ機能のDBテスト(指定したaddress)
    @Test
    @DataSet(value = "datasets/employees.yml")
    @Transactional
    void 指定した住所の従業員情報を獲得すること() {
        List<Employee> employee = employeeMapper.findByAddress("東京都品川区1-1-1");
        assertThat(employee).contains(new Employee(1, "鈴木 碧", 16, "東京都品川区1-1-1"));
    }

    //CREATE機能のDBテスト
    @Test
    @DataSet(value = "datasets/employees.yml")
    @ExpectedDataSet(value = "datasets/insertEmployeesTest.yml", ignoreCols = "id")
    @Transactional
    public void 新規の従業員が登録できること() {
        Employee employee = new Employee("鶴見 良一", 24, "鳥取県鳥取市5-2-3");
        employeeMapper.insert(employee);
    }

    //UPDATE機能のDBテスト
    @Test
    @DataSet(value = "datasets/employees.yml")
    @ExpectedDataSet(value = "datasets/updateEmployeesTest.yml")
    @Transactional
    public void 存在する従業員を更新すること() {
        Employee employee = new Employee(2, "花房 清", 25, "岡山県岡山市5-2-3");
        employeeMapper.updateEmployee(employee);
    }

    @Test
    @DataSet(value = "datasets/employees.yml")
    @ExpectedDataSet(value = "datasets/employees.yml")
    @Transactional
    public void 存在しないIDで従業員を更新処理した場合は更新されないこと() {
        Employee employee = new Employee(100, "花房 清", 25, "岡山県岡山市5-2-3");
        employeeMapper.updateEmployee(employee);
    }
    //DELETE機能のDBテスト
    @Test
    @DataSet(value = "datasets/employees.yml")
    @ExpectedDataSet(value = "datasets/deleteEmployeesTest.yml")
    @Transactional
    public void 存在する従業員情報を削除すること() {
        employeeMapper.deleteEmployee(1);
    }

    @Test
    @DataSet(value = "datasets/employees.yml")
    @ExpectedDataSet(value = "datasets/employees.yml")
    @Transactional
    public void 存在しないIDの従業員情報を指定した場合は削除されないこと() {
        employeeMapper.deleteEmployee(100);
    }
}
