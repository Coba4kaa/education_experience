package org.example.dao;

import lombok.Getter;
import org.example.dataModel.Cat;
import org.example.dao.interfaces.CatRepository;
import org.example.dao.interfaces.ICatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Getter
@Component
public class CatDao implements ICatDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CatRepository catRepository;

    @Transactional
    public List<Cat> getCatFriends(Long catId) {
        return entityManager.createQuery("SELECT c.friends FROM Cat c WHERE c.id = :catId", Cat.class)
                .setParameter("catId", catId)
                .getResultList();
    }

    @Transactional
    public void addFriendship(Long catId, Long friendCatId) {
        Cat cat = entityManager.find(Cat.class, catId);
        Cat friendCat = entityManager.find(Cat.class, friendCatId);
        if (cat != null && friendCat != null) {
            cat.getFriends().add(friendCat);
            friendCat.getFriends().add(cat);
            entityManager.merge(cat);
            entityManager.merge(friendCat);
        }
    }

    @Transactional
    public void removeFriendship(Long catId, Long friendCatId) {
        Cat cat = entityManager.find(Cat.class, catId);
        Cat friendCat = entityManager.find(Cat.class, friendCatId);
        if (cat != null && friendCat != null) {
            cat.getFriends().remove(friendCat);
            friendCat.getFriends().remove(cat);
            entityManager.merge(cat);
            entityManager.merge(friendCat);
        }
    }
}
