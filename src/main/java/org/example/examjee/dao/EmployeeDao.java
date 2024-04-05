package org.example.examjee.dao;

import org.example.examjee.model.Employee;
import java.util.List;

public interface EmployeeDao {
    List<Employee> findAll();
    int save(Employee employee);
    boolean delete(Employee employee);
    boolean isEmailUnique(String email);
    List<String> findAllNames();
    Employee findByName(String name);
}
