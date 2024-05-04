package org.example.service.services;

import lombok.Getter;
import org.example.dao.interfaces.ICatDao;
import org.example.service.wrappers.CatWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Component
public class CatService {
    private ICatDao catDao;

    @Autowired
    public CatService(ICatDao catDao){
        this.catDao = catDao;
    }

    public void createCat(CatWrapper cat) {
        catDao.getCatRepository().save(cat.getCat());
    }

    public void updateCat(CatWrapper cat) {
        catDao.getCatRepository().save(cat.getCat());
    }

    public void deleteCat(CatWrapper cat) {
        catDao.getCatRepository().delete(cat.getCat());
    }

    public CatWrapper getCatById(Long catId) {
        return new CatWrapper(catDao.getCatRepository().findById(catId).get());
    }

    public List<CatWrapper> getAllCats() {
        return catDao.getCatRepository().findAll().stream()
                .map(CatWrapper::new)
                .collect(Collectors.toList());
    }

    public List<CatWrapper> getCatFriends(Long ownerId) {
        return catDao.getCatFriends(ownerId).stream()
                .map(CatWrapper::new)
                .collect(Collectors.toList());
    }

    public void addFriendship(Long catId, Long friendCatId) {
        catDao.addFriendship(catId, friendCatId);
    }

    public void removeFriendship(Long catId, Long friendCatId) {
        catDao.removeFriendship(catId, friendCatId);
    }
}
