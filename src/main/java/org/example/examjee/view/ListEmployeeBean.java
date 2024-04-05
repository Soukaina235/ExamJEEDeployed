package org.example.examjee.view;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import org.example.examjee.model.Assignment;
import org.example.examjee.model.Employee;
import org.example.examjee.service.AssignmentService;
import org.example.examjee.service.EmployeeService;
import java.util.List;

@ManagedBean(name="listEmployeeBean")
@RequestScoped
public class ListEmployeeBean {
    private EmployeeService employeeService;
    private AssignmentService assignmentService;
    private List<Employee> employees ;

    /**
     * Constructor to initialize:
     * - the ListEmployeeBean with a default EmployeeService implementation
     * - the ListEmployeeBean with a default AssignmentService implementation.
     * - the list of existing employees
     */
    public ListEmployeeBean(){
        employeeService = new EmployeeService();
        assignmentService = new AssignmentService();
        employees = employeeService.findAllService();
    }

    // Getters and Setters
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Calls the service for deleting an employee.
     * @param employee The employee to be deleted.
     */
    public void delete(Employee employee){
        employeeService.deleteService(employee);
        employees.remove(employee);
    }

    /**
     * Calls the service for finding the assignments that belong to the employee passed in parameters.
     * @param employee The employee to whom belong the assignments.
     */
    public List<Assignment> getAssignments(Employee employee) {
        return assignmentService.findAssignmentsByEmployeeService(employee);
    }
}
