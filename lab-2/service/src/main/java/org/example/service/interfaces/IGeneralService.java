package org.example.service.interfaces;

import org.example.dataModel.Cat;
import org.example.dataModel.Owner;
import org.example.service.services.CatService;
import org.example.service.services.OwnerService;

public interface IGeneralService {
    void createCat(Cat cat);
    void createOwner(Owner owner);

    OwnerService getOwnerService();

    CatService getCatService();
}
