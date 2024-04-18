package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.example.dataModel.Cat;
import org.example.dao.interfaces.IOwnerDao;
import org.example.dao.interfaces.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Getter
@Component
public class OwnerDao implements IOwnerDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private OwnerRepository ownerRepository;

    @Transactional
    public List<Cat> getCatsByOwner(long ownerId) {
        return entityManager.createQuery("SELECT c FROM Cat c WHERE c.ownerId = :ownerId", Cat.class)
                .setParameter("ownerId", ownerId)
                .getResultList();
    }
}
