package com.mqp.service;

import com.mqp.dao.EmployeeDao;
import com.mqp.pojo.Employee;

import java.util.List;


public interface EmployeeService {
    List<EmployeeDao> selectAllEmployeeDao();
    Employee selectEmployeeById(int id);
    int addEmployee(Employee employee);
    int updateEmployee(Employee employee);
    int deleteEmployee(int id);
}
