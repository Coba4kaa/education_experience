package org.example.service.services;

import org.example.CatDao;
import org.example.interfaces.ICatDao;
import org.example.dataModel.Cat;

import java.util.List;

public class CatService {
    private final ICatDao catDao;

    public CatService() {
        this.catDao = new CatDao();
    }

    public void createCat(Cat cat) {
        catDao.save(cat);
    }

    public void updateCat(Cat cat) {
        catDao.update(cat);
    }

    public void deleteCat(Long catId) {
        catDao.delete(catId);
    }

    public Cat getCatById(Long catId) {
        return catDao.findById(catId);
    }

    public List<Cat> getAllCats() {
        return catDao.findAll();
    }

    public List<Cat> getCatFriends(Long ownerId) {
        return catDao.getCatFriends(ownerId);
    }

    public void addFriendship(Long catId, Long friendCatId) {
        catDao.addFriendship(catId, friendCatId);
    }

    public void removeFriendship(Long catId, Long friendCatId) {
        catDao.removeFriendship(catId, friendCatId);
    }
}
