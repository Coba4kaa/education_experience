package org.example.service.services;

import org.example.dao.interfaces.IOwnerDao;
import org.example.service.wrappers.CatWrapper;
import org.example.service.wrappers.OwnerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OwnerService {
    @Autowired
    private IOwnerDao ownerDao;

    public void createOwner(OwnerWrapper owner) {
        ownerDao.getOwnerRepository().save(owner.getOwner());
    }

    public void updateOwner(OwnerWrapper owner) {
        ownerDao.getOwnerRepository().save(owner.getOwner());
    }

    public void deleteOwner(OwnerWrapper owner) {
        ownerDao.getOwnerRepository().delete(owner.getOwner());
    }

    public OwnerWrapper getOwnerById(Long ownerId) {
        return new OwnerWrapper(ownerDao.getOwnerRepository().findById(ownerId).get());
    }

    public List<OwnerWrapper> getAllOwners() {
        return ownerDao.getOwnerRepository().findAll().stream()
                .map(OwnerWrapper::new)
                .collect(Collectors.toList());
    }

    public List<CatWrapper> getCatsByOwner(Long ownerId) {
        return ownerDao.getCatsByOwner(ownerId).stream()
                .map(CatWrapper::new)
                .collect(Collectors.toList());
    }
}
