package com.tms.kulinar.repository;

import com.tms.kulinar.domain.Recipe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;

@Repository
public class RecipeRepository {
    public SessionFactory sessionFactory;

    public RecipeRepository() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public ArrayList<Recipe> getAllRecipe() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Recipe ");
        ArrayList<Recipe> list = (ArrayList<Recipe>) query.getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public Recipe getRecipeById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Recipe recipe = session.get(Recipe.class, id);
        session.getTransaction().commit();
        session.close();
        return recipe;
    }

    public Recipe createRecipe(Recipe recipe) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(recipe);
        session.getTransaction().commit();
        session.close();

        return recipe;
    }

    public Recipe updateRecipe(Recipe recipe) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(recipe);
        session.getTransaction().commit();
        session.close();
        return recipe;
    }

    public Recipe deleteProducts(Recipe recipe) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(recipe);
        session.getTransaction().commit();
        session.close();
        return recipe;
    }
}
