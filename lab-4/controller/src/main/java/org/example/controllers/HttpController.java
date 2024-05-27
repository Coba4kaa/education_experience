package org.example.controllers;

import org.example.Validator;
import org.example.dao.interfaces.OwnerRepository;
import org.example.service.interfaces.IGeneralService;
import org.example.service.wrappers.CatWrapper;
import org.example.service.wrappers.OwnerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class HttpController {
    private IGeneralService generalService;
    private ApplicationContext context;
    private OwnerRepository ownerRepository;
    private Validator validator;

    @Autowired
    public HttpController(IGeneralService generalService, ApplicationContext context, OwnerRepository ownerRepository, Validator validator) {
        this.generalService = generalService;
        this.context = context;
        this.ownerRepository = ownerRepository;
        this.validator = validator;
    }

    @PostMapping("/owners")
    public OwnerWrapper createOwner(@RequestParam(name = "ownerName") String ownerName) {
        OwnerWrapper owner = context.getBean(OwnerWrapper.class);
        owner.setName(ownerName);
        generalService.createOwner(owner);
        return owner;
    }

    @PostMapping("/cats")
    public CatWrapper createCat(@RequestParam(name = "catName") String catName) {
        CatWrapper cat = context.getBean(CatWrapper.class);
        cat.setName(catName);
        generalService.createCat(cat);
        return cat;
    }

    @GetMapping("/cats/{id}")
    public CatWrapper getCatById(@PathVariable("id") Long id, Principal principal) {
        CatWrapper cat = generalService.getCatService().getCatById(id);
        if (!Objects.equals(principal.getName(), ownerRepository.findById(cat.getOwnerId()).get().getName()) && !Objects.equals(ownerRepository.findByName(principal.getName()).get().getRole(), "ROLE_ADMIN"))
            return null;
        return cat;
    }

    @GetMapping("/owners/{id}")
    public OwnerWrapper getOwnerById(@PathVariable("id") Long id, Principal principal) {
        OwnerWrapper owner = generalService.getOwnerService().getOwnerById(id);
        if (!Objects.equals(principal.getName(), owner.getName()) && !Objects.equals(ownerRepository.findByName(principal.getName()).get().getRole(), "ROLE_ADMIN"))
            return null;
        return owner;
    }

    @GetMapping("/cats/color/{color}")
    public List<CatWrapper> getCatsByColor(@PathVariable("color") String color, Principal principal) {
        return validator.getCatsByColor(color, principal);
    }

    @GetMapping("/cats/name/{name}")
    public List<CatWrapper> getCatsByName(@PathVariable("name") String name, Principal principal) {
        return validator.getCatsByName(name, principal);
    }
}
