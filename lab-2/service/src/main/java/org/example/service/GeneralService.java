package org.example.service;

import org.example.dataModel.Cat;
import org.example.dataModel.Owner;
import org.example.service.interfaces.IGeneralService;
import org.example.service.services.CatService;
import org.example.service.services.OwnerService;

public class GeneralService implements IGeneralService {
    private final CatService catService;
    private final OwnerService ownerService;

    public GeneralService() {
        this.catService = new CatService();
        this.ownerService = new OwnerService();
    }

    @Override
    public void createCat(Cat cat) {
        catService.createCat(cat);
    }

    @Override
    public void createOwner(Owner owner) {
        ownerService.createOwner(owner);
    }

    @Override
    public OwnerService getOwnerService() {
        return ownerService;
    }

    @Override
    public CatService getCatService() {
        return catService;
    }
}
