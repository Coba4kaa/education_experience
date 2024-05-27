package org.example;

import org.example.dao.interfaces.OwnerRepository;
import org.example.service.interfaces.IGeneralService;
import org.example.service.wrappers.CatWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class Validator {
    private OwnerRepository ownerRepository;

    private IGeneralService generalService;

    @Autowired
    public Validator(OwnerRepository userRepo, IGeneralService generalService){
        this.ownerRepository = userRepo;
        this.generalService = generalService;
    }

    public List<CatWrapper> getCatsByColor(String color, Principal principal) {
        List<CatWrapper> allCats = generalService.getCatService().getAllCats();
        List<CatWrapper> catsByColor = allCats.stream()
                .filter(cat -> Objects.equals(cat.getColor(), color))
                .filter(cat -> Objects.equals(cat.getOwnerId(), ownerRepository.findByName(principal.getName()).get().getId())
                        || Objects.equals(ownerRepository.findByName(principal.getName()).get().getRole(), "ROLE_ADMIN"))
                .collect(Collectors.toList());
        return catsByColor;
    }

    public List<CatWrapper> getCatsByName(String name, Principal principal) {
        List<CatWrapper> allCats = generalService.getCatService().getAllCats();
        List<CatWrapper> catsByName = allCats.stream()
                .filter(cat -> Objects.equals(cat.getName(), name))
                .filter(cat -> Objects.equals(cat.getOwnerId(), ownerRepository.findByName(principal.getName()).get().getId())
                        || Objects.equals(ownerRepository.findByName(principal.getName()).get().getRole(), "ROLE_ADMIN"))
                .collect(Collectors.toList());
        return catsByName;
    }
}
