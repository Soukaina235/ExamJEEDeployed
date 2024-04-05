package org.example.examjee.view;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.model.SelectItem;
import org.example.examjee.model.Employee;
import org.example.examjee.model.Post;
import org.example.examjee.service.EmployeeService;
import java.util.List;

@ManagedBean(name="addEmployeeBean")
@RequestScoped
public class AddEmployeeBean {
    EmployeeService employeeService;
    private Employee employee;
    private List<String> availableSkills;

    /**
     * Constructor to initialize:
     * - the AddEmployeeBean with a default EmployeeService implementation
     * - an employee object
     * - the list of available skills
     */
    public AddEmployeeBean() {
        employeeService = new EmployeeService();
        employee = new Employee();
        availableSkills = List.of("Java", ".NET", "node.JS", "Spring", "Angular", "Sales Cloud", "Marketing Cloud");
    }

    // Getters and Setters
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<String> getAvailableSkills() {
        return availableSkills;
    }

    public void setAvailableSkills(List<String> availableSkills) {
        this.availableSkills = availableSkills;
    }

    /**
     * Return the Post values to be selected.
     */
    public SelectItem[] getPostValues() {
        Post[] posts = Post.values();
        SelectItem[] items = new SelectItem[posts.length];

        for (int i = 0; i < posts.length; i++) {
            items[i] = new SelectItem(posts[i], posts[i].toString());
        }

        return items;
    }

    /**
     * Calls the service for adding an employee.
     * @param employee The employee to be added.
     */
    public String add(Employee employee) {
        return employeeService.addService(employee);
    }
}
