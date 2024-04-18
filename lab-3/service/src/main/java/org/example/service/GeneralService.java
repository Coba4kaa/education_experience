package org.example.service;

import lombok.Getter;
import org.example.service.interfaces.IGeneralService;
import org.example.service.services.CatService;
import org.example.service.services.OwnerService;
import org.example.service.wrappers.CatWrapper;
import org.example.service.wrappers.OwnerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class GeneralService implements IGeneralService {
    @Autowired
    private CatService catService;
    @Autowired
    private OwnerService ownerService;

    @Override
    public void createCat(CatWrapper cat) {
        catService.createCat(cat);
    }

    @Override
    public void createOwner(OwnerWrapper owner) {
        ownerService.createOwner(owner);
    }
}
