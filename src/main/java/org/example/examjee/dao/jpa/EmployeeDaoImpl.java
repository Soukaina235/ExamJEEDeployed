package org.example.examjee.dao.jpa;

import jakarta.persistence.*;
import org.example.examjee.dao.EmployeeDao;
import org.example.examjee.model.Employee;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public List<Employee> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink");
        EntityManager em = emf.createEntityManager();

        List<Employee> employees = null;

        try {
            employees = em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
            return employees;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public int save(Employee employee) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            em.persist(employee);
            tx.commit();
            return 1; // Indicates successful persistence
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return 0; // Indicates failure in persistence
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public boolean delete(Employee employee) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            if (employee != null) {
                Employee managedEmployee = em.merge(employee); // Merge the detached entity into the current persistence context
                em.remove(managedEmployee);
                tx.commit();
                return true; // Indicates successful deletion
            } else {
                return false; // Indicates employee not found
            }
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false; // Indicates failure in deletion
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public boolean isEmailUnique(String email) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink");
        EntityManager em = emf.createEntityManager();
        boolean unique;

        try {
            Query query = em.createQuery("SELECT COUNT(e) FROM Employee e WHERE e.email LIKE :email");
            query.setParameter("email", email);
            long count = (Long) query.getSingleResult();
            unique = (count == 0);
        } catch(Exception e) {
            e.printStackTrace();
            return false; // if error: returns false
        } finally {
            em.close();
            emf.close();
        }

        return unique;
    }

    @Override
    public List<String> findAllNames() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink");
        EntityManager em = emf.createEntityManager();

        List<String> names;

        try {
            names = em.createQuery("SELECT e.name FROM Employee e", String.class).getResultList();

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
    public Employee findByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink");
        EntityManager em = emf.createEntityManager();
        Employee employee;

        try {
            Query query = em.createQuery("SELECT e FROM Employee e WHERE e.name LIKE :name", Employee.class);
            query.setParameter("name", name);
            employee = (Employee) query.getSingleResult();
            return employee;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
            emf.close();
        }
    }
}