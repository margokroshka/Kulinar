package com.tms.kulinar.repository;

import com.tms.kulinar.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;

@Repository
public class UsersRepository {
    public SessionFactory sessionFactory;

    public UsersRepository() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public ArrayList<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User ");
        ArrayList<User> list = (ArrayList<User>) query.getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public User getUserById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public User createUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public User updateUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public User deleteUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }
}
