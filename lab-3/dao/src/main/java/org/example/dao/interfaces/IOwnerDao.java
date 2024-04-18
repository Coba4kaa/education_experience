package org.example.dao.interfaces;

import org.example.dataModel.Cat;

import java.util.List;

public interface IOwnerDao {
    List<Cat> getCatsByOwner(long ownerId);

    OwnerRepository getOwnerRepository();
}
