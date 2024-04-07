package org.example.interfaces;

import org.example.dataModel.Cat;
import org.example.dataModel.Owner;

import java.util.List;

public interface IOwnerDao {
    Owner save(Owner owner);

    Owner update(Owner owner);

    void delete(Long id);

    Owner findById(Long id);

    List<Owner> findAll();

    List<Cat> getCatsByOwner(long ownerId);
}
