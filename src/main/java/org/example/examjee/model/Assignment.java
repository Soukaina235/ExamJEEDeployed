package org.example.examjee.model;

import jakarta.persistence.*;

@Entity
@Table(name = "assignment")
public class Assignment {
    @EmbeddedId
    private AssignmentId id;
    @ManyToOne
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    private Project project;

    @Column(name = "implication")
    private double implication;

    public Assignment() {
        super();
    }

    public Assignment(AssignmentId id, Employee employee, Project project, double implication) {
        this.id = id;
        this.employee = employee;
        this.project = project;
        this.implication = implication;
    }

    public AssignmentId getId() {
        return id;
    }

    public void setId(AssignmentId id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public double getImplication() {
        return implication;
    }

    public void setImplication(double implication) {
        this.implication = implication;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", employee=" + employee.getName() +
                ", project=" + project.getName() +
                ", implication=" + implication +
                '}';
    }
}
