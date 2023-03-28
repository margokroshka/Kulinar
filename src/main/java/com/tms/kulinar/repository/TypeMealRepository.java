package com.tms.kulinar.repository;

import com.tms.kulinar.domain.Recipe;
import com.tms.kulinar.domain.TypeMeal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;

@Repository
public class TypeMealRepository {
    public SessionFactory sessionFactory;

    public TypeMealRepository() {
        sessionFactory=new Configuration().configure().buildSessionFactory();
    }

    public ArrayList<TypeMeal> getAllTypeMeal(){
        Session session= sessionFactory.openSession();
        session.beginTransaction();
        Query query=session.createQuery("from TypeMeal ");
        ArrayList<TypeMeal> list= (ArrayList<TypeMeal>) query.getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }
    public TypeMeal getTypeMealById(int id){
        Session session= sessionFactory.openSession();
        session.beginTransaction();
        TypeMeal type_meal=session.get(TypeMeal.class,id);
        session.getTransaction().commit();
        session.close();
        return type_meal;
    }
    public void createTypeMeal(TypeMeal type_meal){
        Session session= sessionFactory.openSession();
        session.beginTransaction();
        session.save(type_meal);
        session.getTransaction().commit();
        session.close();

    }
    public void updateTypeMeal(TypeMeal type_meal){
        Session session= sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(type_meal);
        session.getTransaction().commit();
        session.close();

    }
    public void deleteTypeMeal(TypeMeal type_meal){
        Session session= sessionFactory.openSession();
        session.beginTransaction();
        session.delete(type_meal);
        session.getTransaction().commit();
        session.close();
    }
}
