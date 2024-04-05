package org.example.examjee.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    @Column(name = "skills")
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private List<String> skills;

    @Enumerated(EnumType.STRING)
    private Post post;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Assignment> assignments;

    public Employee() {
        super();
        assignments = new ArrayList<>();
        skills = new ArrayList<>();
    }

    public Employee(int id, String name, String email, List<String> skills, Post post, List<Assignment> assignments) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.skills = skills;
        this.post = post;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    @Override
    public String toString() {
        StringBuilder employee = new StringBuilder("Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", skills={");
        if (skills != null) {
            for (String skill : skills) {
                employee.append(skill).append(", ");
            }
        } else {
            employee.append("null");
        }
        return employee + "}, post=" + post +
                ", asignments=" + assignments +
                '}';
    }
}
