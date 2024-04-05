package org.example.examjee.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.example.examjee.dao.ProjectDao;
import org.example.examjee.model.Project;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {
    @Override
    public List<Project> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink");
        EntityManager em = emf.createEntityManager();

        List<Project> projects;

        try {
            projects = em.createQuery("SELECT e FROM Project e", Project.class).getResultList();
            return projects;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public List<String> findAllNames() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink");
        EntityManager em = emf.createEntityManager();

        List<String> names;

        try {
            names = em.createQuery("SELECT e.name FROM Project e", String.class).getResultList();
            return names;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public Project findByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink");
        EntityManager em = emf.createEntityManager();
        Project project;

        try {
            Query query = em.createQuery("SELECT p FROM Project p WHERE p.name LIKE :name", Project.class);
            query.setParameter("name", name);
            project = (Project) query.getSingleResult();
            return project;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
            emf.close();
        }
    }
}
