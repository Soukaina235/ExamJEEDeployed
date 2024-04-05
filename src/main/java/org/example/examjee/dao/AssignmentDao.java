package org.example.examjee.dao;

import org.example.examjee.model.Assignment;
import org.example.examjee.model.Employee;
import org.example.examjee.model.Project;
import java.util.List;

public interface AssignmentDao {
    boolean affect(Employee employee, Project project, double implication);
    List<Assignment> findAssignmentsByEmployee(int employee_id);
    boolean doesAssignmentExist(int employee_id, int project_id);
}
