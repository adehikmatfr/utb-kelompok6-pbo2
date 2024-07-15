/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controller;

import com.mycompany.Model.User;
import com.mycompany.Lib.Hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;
/**
 *
 * @author Clodeo
 */
public class UserController {
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // Hash the password before saving the user
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public List<User> getAllUsers() {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    public User getUserById(Long id) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.get(User.class, id);
        }
    }

    public User getUserByUsername(String username) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User where username = :username", User.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        }
    }

    public void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void deleteUser(Long id) {
        Transaction transaction = null;
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                System.out.println("User is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public User authenticate(String username, String password) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User where username = :username", User.class);
            query.setParameter("username", username);
            User user = query.uniqueResult();
            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                return user;
            } else {
                return null;
            }
        }
    }
}
