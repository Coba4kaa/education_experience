package org.example;

import org.example.dataModel.Cat;
import org.example.dataModel.Owner;
import org.example.interfaces.IOwnerDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class OwnerDao implements IOwnerDao {
    private Session session;

    public OwnerDao() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        this.session = sessionFactory.openSession();
    }

    @Override
    public Owner save(Owner owner) {
        Transaction transaction = session.beginTransaction();
        session.save(owner);
        transaction.commit();
        return owner;
    }

    @Override
    public Owner update(Owner owner) {
        Transaction transaction = session.beginTransaction();
        session.merge(owner);
        transaction.commit();
        return owner;
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = session.beginTransaction();
        Owner owner = session.get(Owner.class, id);
        if (owner != null)
            session.delete(owner);
        transaction.commit();
    }

    @Override
    public Owner findById(Long id) {
        return session.get(Owner.class, id);
    }

    @Override
    public List<Owner> findAll() {
        return session.createQuery("FROM Owner", Owner.class).list();
    }

    @Override
    public List<Cat> getCatsByOwner(long ownerId){
        return session.createQuery("FROM Cat WHERE ownerId = :ownerId", Cat.class)
                .setParameter("ownerId", ownerId)
                .list();
    }
}
