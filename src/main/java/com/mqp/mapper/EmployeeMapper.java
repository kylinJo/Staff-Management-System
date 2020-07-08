package com.mqp.mapper;

import com.mqp.dao.EmployeeDao;
import com.mqp.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface EmployeeMapper {

    List<EmployeeDao> selectAllEmployeeDao();
    Employee selectEmployeeById(int id);
    int addEmployee(Employee employee);
    int updateEmployee(Employee employee);
    int deleteEmployee(int id);
}
