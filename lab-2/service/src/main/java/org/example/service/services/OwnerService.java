package org.example.service.services;

import org.example.interfaces.IOwnerDao;
import org.example.OwnerDao;
import org.example.dataModel.Cat;
import org.example.dataModel.Owner;

import java.util.List;

public class OwnerService {
    private final IOwnerDao ownerDao;

    public OwnerService() {
        this.ownerDao = new OwnerDao();
    }

    public void createOwner(Owner owner) {
        ownerDao.save(owner);
    }

    public void updateOwner(Owner owner) {
        ownerDao.update(owner);
    }

    public void deleteOwner(Long ownerId) {
        ownerDao.delete(ownerId);
    }

    public Owner getOwnerById(Long ownerId) {
        return ownerDao.findById(ownerId);
    }

    public List<Owner> getAllOwners() {
        return ownerDao.findAll();
    }

    public List<Cat> getCatsByOwner(Long ownerId) {
        return ownerDao.getCatsByOwner(ownerId);
    }
}
