package org.example;

import org.example.data_model.Cat;
import org.example.repos.CatRepository;
import org.example.repos.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class Validator {
    private OwnerRepository ownerRepository;

    private CatRepository catRepository;

    @Autowired
    public Validator(OwnerRepository userRepo, CatRepository catRepository){
        this.ownerRepository = userRepo;
        this.catRepository = catRepository;
    }

    public List<Cat> getCatsByColor(String color, Principal principal) {
        List<Cat> allCats = catRepository.getAllCats();
        List<Cat> catsByColor = allCats.stream()
                .filter(cat -> Objects.equals(cat.getColor(), color))
                .filter(cat -> Objects.equals(cat.getOwnerId(), ownerRepository.findByName(principal.getName()).get().getId())
                        || Objects.equals(ownerRepository.findByName(principal.getName()).get().getRole(), "ROLE_ADMIN"))
                .collect(Collectors.toList());
        return catsByColor;
    }

    public List<Cat> getCatsByName(String name, Principal principal) {
        List<Cat> allCats = catRepository.getAllCats();
        List<Cat> catsByName = allCats.stream()
                .filter(cat -> Objects.equals(cat.getName(), name))
                .filter(cat -> Objects.equals(cat.getOwnerId(), ownerRepository.findByName(principal.getName()).get().getId())
                        || Objects.equals(ownerRepository.findByName(principal.getName()).get().getRole(), "ROLE_ADMIN"))
                .collect(Collectors.toList());
        return catsByName;
    }
}
