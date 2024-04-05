package org.example.examjee.service;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.example.examjee.dao.EmployeeDao;
import org.example.examjee.dao.jpa.EmployeeDaoImpl;
import org.example.examjee.model.Employee;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeService {
    EmployeeDao employeeDao;


    /**
     * Constructor to initialize the EmployeeService with a default EmployeeDAO implementation.
     */
    public EmployeeService(){
        this.employeeDao = new EmployeeDaoImpl();
    }

    /**
     * Returns the list of existing employees
     */
    public List<Employee> findAllService(){
        return employeeDao.findAll();
    }

    /**
     * Deletes an employee then adds a message and returns the view
     * @param employee The employee to be added.
     */
    public String addService(Employee employee) {
        boolean isUnique = employeeDao.isEmailUnique(employee.getEmail());

        ResourceBundle bundle = ResourceBundle.getBundle("i18n.labels");

        if (isUnique) { // checks if the email is unique
            int result = employeeDao.save(employee);
            if (result > 0) {
                addSuccessMessage(bundle.getString("employee.add"));
                return "list.xhtml";
            } else {
                addErrorMessage(bundle.getString("employee.add.failed"));
                return "add-employee.xhtml";
            }
        } else { // if not add a warning message
            addWarningMessage(bundle.getString("employee.add.duplicateEmail"));
            return "add-employee.xhtml";
        }
    }

    /**
     * Deletes an employee then adds a message and returns the view
     * @param employee The employee to be deleted.
     */
    public void deleteService(Employee employee) {
        boolean result = employeeDao.delete(employee);

        ResourceBundle bundle = ResourceBundle.getBundle("i18n.labels");

        if (result) {
            addSuccessMessage(bundle.getString("employee.delete"));
        } else {
            addErrorMessage(bundle.getString("employee.delete.failed"));
        }
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
