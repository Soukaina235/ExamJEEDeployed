package org.example.examjee.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "budget")
    private double budget;

    @OneToMany(mappedBy = "project", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Assignment> assignments;

    public Project() {
        super();
        assignments = new ArrayList<>();
    }

    public Project(int id, String name, double budget, List<Assignment> assignments) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.assignments = assignments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", asignments=" + assignments +
                '}';
    }
}
