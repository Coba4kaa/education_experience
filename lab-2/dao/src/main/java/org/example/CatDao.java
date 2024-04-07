package org.example;

import org.example.dataModel.Cat;
import org.example.interfaces.ICatDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CatDao implements ICatDao {
    private Session session;

    public CatDao() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        this.session = sessionFactory.openSession();
    }

    @Override
    public Cat save(Cat cat) {
        Transaction transaction = session.beginTransaction();
        session.save(cat);
        transaction.commit();
        return cat;
    }

    @Override
    public Cat update(Cat cat) {
        Transaction transaction = session.beginTransaction();
        session.merge(cat);
        transaction.commit();
        return cat;
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = session.beginTransaction();
        Cat cat = session.get(Cat.class, id);
        if (cat != null)
            session.delete(cat);
        transaction.commit();
    }

    @Override
    public Cat findById(Long id) {
        return session.get(Cat.class, id);
    }

    @Override
    public List<Cat> findAll() {
        return session.createQuery("FROM Cat", Cat.class).list();
    }

    @Override
    public List<Cat> getCatFriends(Long catId) {
        return session.createQuery("SELECT c FROM Cat c JOIN c.friends f WHERE f.id = :catId", Cat.class)
                .setParameter("catId", catId)
                .list();
    }

    @Override
    public void addFriendship(Long catId, Long friendCatId) {
        Transaction transaction = session.beginTransaction();
        Cat cat = session.get(Cat.class, catId);
        Cat friendCat = session.get(Cat.class, friendCatId);
        if (cat != null && friendCat != null) {
            cat.getFriends().add(friendCat);
            friendCat.getFriends().add(cat);
            session.update(cat);
            session.update(friendCat);
        }
        transaction.commit();
    }

    @Override
    public void removeFriendship(Long catId, Long friendCatId) {
        Transaction transaction = session.beginTransaction();
        Cat cat = session.get(Cat.class, catId);
        Cat friendCat = session.get(Cat.class, friendCatId);
        if (cat != null && friendCat != null) {
            cat.getFriends().remove(friendCat);
            friendCat.getFriends().remove(cat);
            session.update(cat);
            session.update(friendCat);
        }
        transaction.commit();
    }

    public Session getSession() {
        return session;
    }
}
