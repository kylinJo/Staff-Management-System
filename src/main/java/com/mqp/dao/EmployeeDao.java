package com.mqp.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class EmployeeDao {
        private int id;
        private String employeeName;
        private String email;
        private int gender;
        private String departmentName;
        private Date date;
}
