package org.example.dao.interfaces;

import org.example.dataModel.Cat;

import java.util.List;

public interface ICatDao {
    List<Cat> getCatFriends(Long ownerId);

    void addFriendship(Long catId, Long friendCatId);

    public void removeFriendship(Long catId, Long friendCatId);

    CatRepository getCatRepository();
}
