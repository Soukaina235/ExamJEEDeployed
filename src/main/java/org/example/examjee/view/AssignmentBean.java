package org.example.examjee.view;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import org.example.examjee.model.Employee;
import org.example.examjee.model.Project;
import org.example.examjee.service.AssignmentService;
import java.util.List;

@ManagedBean(name="assignmentBean")
@RequestScoped
public class AssignmentBean {
    private AssignmentService assignmentService;
    private List<String> employeesName;
    private List<String> projectNames;

    /**
     * Constructor to initialize:
     * - the AssignmentBean with a default AssignmentService implementation.
     * - a list of the names of available employees
     * - a list of the names of available projects
     */
    public AssignmentBean(){
        assignmentService = new AssignmentService();
        employeesName = assignmentService.findAllEmployeeNamesService();
        projectNames = assignmentService.findAllProjectNamesService();
    }

    // Getters and Setters
    public AssignmentService getAssignmentService() {
        return assignmentService;
    }

    public void setAssignmentService(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    public List<String> getEmployeesName() {
        return employeesName;
    }

    public void setEmployeesName(List<String> employeesName) {
        this.employeesName = employeesName;
    }

    public List<String> getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(List<String> projectNames) {
        this.projectNames = projectNames;
    }

    /**
     * Calls the service for affecting an employee.
     * @param employee The employee to be affected.
     * @param project The project to be affected.
     * @param implication The implication of the employee in the project.
     */
    public String affectEmployee(Employee employee, Project project, double implication) {
        return assignmentService.affectEmployeeService(employee, project, implication);
    }
}
