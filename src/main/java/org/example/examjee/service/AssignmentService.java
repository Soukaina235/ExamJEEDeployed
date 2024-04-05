package org.example.examjee.service;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.example.examjee.dao.AssignmentDao;
import org.example.examjee.dao.EmployeeDao;
import org.example.examjee.dao.ProjectDao;
import org.example.examjee.dao.jpa.AssignmentDaoImpl;
import org.example.examjee.dao.jpa.EmployeeDaoImpl;
import org.example.examjee.dao.jpa.ProjectDaoImpl;
import org.example.examjee.model.Assignment;
import org.example.examjee.model.Employee;
import org.example.examjee.model.Project;
import java.util.List;
import java.util.ResourceBundle;

public class AssignmentService {
    EmployeeDao employeeDao;
    ProjectDao projectDao;
    AssignmentDao assignmentDao;

    /**
     * Constructor to initialize the AssignmentService with default EmployeeDao, ProjectDao and AssignmentDao implementations.
     */
    public AssignmentService() {
        this.employeeDao = new EmployeeDaoImpl();
        this.projectDao = new ProjectDaoImpl();
        this.assignmentDao = new AssignmentDaoImpl();
    }

    /**
     * Returns a list of the names of the existing employees
     */
    public List<String> findAllEmployeeNamesService() {
        return employeeDao.findAllNames();
    }

    /**
     * Returns a list of the names of the existing projects
     */
    public List<String> findAllProjectNamesService() {
        return projectDao.findAllNames();
    }

    /**
     * Affects an employee then adds a message and returns the view
     * @param employee The employee to be assigned.
     * @param project The project to be assigned.
     * @param implication The implication of the employee in the project.
     */
    public String affectEmployeeService(Employee employee, Project project, double implication) {
        Employee managedEmployee = employeeDao.findByName(employee.getName());
        Project managedProject = projectDao.findByName(project.getName());

        boolean exists = assignmentDao.doesAssignmentExist(managedEmployee.getId(), managedProject.getId());

        ResourceBundle bundle = ResourceBundle.getBundle("i18n.labels");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        if (!exists) { // checks if the assignment doesn't already exist
            boolean result = assignmentDao.affect(managedEmployee, managedProject, implication);

            if (result) {
                addSuccessMessage(bundle.getString("employee.affect"));
                return "list.xhtml?faces-redirect=true";
            } else {
                addErrorMessage(bundle.getString("employee.affect.failed"));
                return "affect-employee.xhtml";
            }
        } else { // if it does exist: add a warning message
            addWarningMessage(bundle.getString("employee.affect.affectationAlreadyExists"));
            return "affect-employee.xhtml";
        }
    }

    /**
     * Returns the list of assignments that belong to the employee passed in the parameters
     * @param employee The employee to whom belong the assignments
     */
    public List<Assignment> findAssignmentsByEmployeeService(Employee employee) {
        return assignmentDao.findAssignmentsByEmployee(employee.getId());
    }

    /**
     * Adds an error message to the FacesContext.
     * @param message The error message to be added.
     */
    private void addErrorMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    /**
     * Adds a warning message to the FacesContext.
     * @param message The warning message to be added.
     */
    private void addWarningMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
    }

    /**
     * Adds a success message to the FacesContext.
     * @param message The success message to be added.
     */
    private void addSuccessMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }
}
