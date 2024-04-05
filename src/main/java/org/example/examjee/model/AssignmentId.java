package org.example.examjee.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AssignmentId implements Serializable {
    @Column(name = "employee_id")
    private int employeeId;
    @Column(name = "project_id")
    private int projectId;

    public AssignmentId() {
        super();
    }

    public AssignmentId(int employeeId, int projectId) {
        this.employeeId = employeeId;
        this.projectId = projectId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "AssignmentId{" +
                "employeeId=" + employeeId +
                ", projectId=" + projectId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssignmentId)) return false;
        AssignmentId that = (AssignmentId) o;
        return getEmployeeId() == that.getEmployeeId() &&
                getProjectId() == that.getProjectId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeId(), getProjectId());
    }
}
