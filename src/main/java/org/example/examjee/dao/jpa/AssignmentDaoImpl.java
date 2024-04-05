package org.example.examjee.dao.jpa;

import jakarta.persistence.*;
import org.example.examjee.dao.AssignmentDao;
import org.example.examjee.model.Assignment;
import org.example.examjee.model.AssignmentId;
import org.example.examjee.model.Employee;
import org.example.examjee.model.Project;
import java.util.List;

public class AssignmentDaoImpl implements AssignmentDao {
    @Override
    public boolean affect(Employee employee, Project project, double implication) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        System.out.println(employee);
        System.out.println(project);

        Assignment assignment = new Assignment();

        assignment.setId(new AssignmentId(employee.getId(), project.getId()));
        assignment.setEmployee(employee);
        assignment.setProject(project);
        assignment.setImplication(implication);

        try {
            em.persist(assignment);
            tx.commit();
            return true; // Indicates successful persistence
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false; // Indicates failure in persistence
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public List<Assignment> findAssignmentsByEmployee(int employee_id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink");
        EntityManager em = emf.createEntityManager();

        List<Assignment> assignments = null;

        try {
            Query query = em.createQuery("SELECT a FROM Assignment a WHERE a.employee.id=:employee_id", Assignment.class);
            query.setParameter("employee_id", employee_id);
            assignments = query.getResultList();
            return assignments;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public boolean doesAssignmentExist(int employee_id, int project_id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink");
        EntityManager em = emf.createEntityManager();
        boolean exists = true;

        try {
            Query query = em.createQuery("SELECT COUNT(a) FROM Assignment a WHERE a.id.employeeId=:employee_id AND a.id.projectId=:project_id");
            query.setParameter("employee_id", employee_id);
            query.setParameter("project_id", project_id);
            long count = (Long) query.getSingleResult();
            exists = !(count == 0);
        } catch(Exception e) {
            e.printStackTrace();
            return exists; // if error: returns true
        } finally {
            em.close();
            emf.close();
        }
        return exists;
    }
}
