package org.example.interfaces;

import org.example.dataModel.Cat;
import org.hibernate.Session;

import java.util.List;

public interface ICatDao {
    Cat save(Cat cat);

    Cat update(Cat cat);

    void delete(Long id);

    Cat findById(Long id);

    List<Cat> findAll();

    List<Cat> getCatFriends(Long ownerId);

    void addFriendship(Long catId, Long friendCatId);

    public void removeFriendship(Long catId, Long friendCatId);

    Session getSession();
}
