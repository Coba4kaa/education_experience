package org.example.service.interfaces;

import org.example.service.services.CatService;
import org.example.service.services.OwnerService;
import org.example.service.wrappers.CatWrapper;
import org.example.service.wrappers.OwnerWrapper;

public interface IGeneralService {
    void createCat(CatWrapper cat);
    void createOwner(OwnerWrapper owner);

    OwnerService getOwnerService();

    CatService getCatService();
}
