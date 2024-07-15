package com.mycompany.Controller;

import com.mycompany.Model.Feedback;
import com.mycompany.Lib.Hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class FeedbackController {
    public void saveFeedback(Feedback feedback) {
        Transaction transaction = null;
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(feedback);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public List<Feedback> getAllFeedbacks() {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.createQuery("from Feedback", Feedback.class).list();
        }
    }

    public Feedback getFeedbackById(Long id) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.get(Feedback.class, id);
        }
    }

    public List<Feedback> searchFeedbacksByCustomerName(String customerName) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            String hql = "from Feedback where customer_name like :customerName";
            Query<Feedback> query = session.createQuery(hql, Feedback.class);
            query.setParameter("customerName", "%" + customerName + "%");
            return query.list();
        }
    }

    public void updateFeedback(Feedback feedback) {
        Transaction transaction = null;
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(feedback);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void deleteFeedback(Long id) {
        Transaction transaction = null;
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Feedback feedback = session.get(Feedback.class, id);
            if (feedback != null) {
                session.delete(feedback);
                System.out.println("Feedback is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
