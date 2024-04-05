package org.example.examjee.dao;

import org.example.examjee.model.Project;
import java.util.List;

public interface ProjectDao {
    List<Project> findAll();
    List<String> findAllNames();
    Project findByName(String name);
}
