package org.example.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.interfaces.IController;
import org.example.service.interfaces.IGeneralService;
import org.example.service.wrappers.CatWrapper;
import org.example.service.wrappers.OwnerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class HttpController implements IController {
    private IGeneralService generalService;
    private ObjectMapper objectMapper;
    private ApplicationContext context;

    @Autowired
    public HttpController(IGeneralService generalService, ObjectMapper objectMapper, ApplicationContext context){
        this.generalService = generalService;
        this.objectMapper = objectMapper;
        this.context = context;
    }

    @Override
    @PostMapping("/owners")
    public ResponseEntity<String> createOwner(@RequestParam(name = "ownerName") String ownerName) {
        OwnerWrapper owner = context.getBean(OwnerWrapper.class);
        owner.setName(ownerName);
        generalService.createOwner(owner);
        return ResponseEntity.ok("Owner " + owner.getName() + " created successfully.");
    }

    @Override
    @PostMapping("/cats")
    public ResponseEntity<String> createCat(@RequestParam(name = "catName") String catName) {
        CatWrapper cat = context.getBean(CatWrapper.class);
        cat.setName(catName);
        generalService.createCat(cat);
        return ResponseEntity.ok("Cat " + cat.getName() + " created successfully.");
    }

    @Override
    @GetMapping("/cats/{id}")
    public ResponseEntity<String> getCatById(@PathVariable("id") Long id) {
        CatWrapper cat = generalService.getCatService().getCatById(id);
        String catJson = null;
        try {
            catJson = objectMapper.writeValueAsString(cat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(catJson);
    }

    @Override
    @GetMapping("/owners/{id}")
    public ResponseEntity<String> getOwnerById(@PathVariable("id") Long id) {
        OwnerWrapper owner = generalService.getOwnerService().getOwnerById(id);
        String ownerJson = null;
        try {
            ownerJson = objectMapper.writeValueAsString(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(ownerJson);
    }

    @GetMapping("/cats/color/{color}")
    public ResponseEntity<String> getCatsByColor(@PathVariable("color") String color) {
        List<CatWrapper> allCats = generalService.getCatService().getAllCats();
        List<CatWrapper> catsByColor = allCats.stream()
                .filter(cat -> Objects.equals(cat.getColor(), color))
                .collect(Collectors.toList());
        return convertListToJsonResponse(catsByColor);
    }

    @GetMapping("/cats/name/{name}")
    public ResponseEntity<String> getCatsByName(@PathVariable("name") String name) {
        List<CatWrapper> allCats = generalService.getCatService().getAllCats();
        List<CatWrapper> catsByName = allCats.stream()
                .filter(cat -> Objects.equals(cat.getName(), name))
                .collect(Collectors.toList());
        return convertListToJsonResponse(catsByName);
    }

    private ResponseEntity<String> convertListToJsonResponse(List<CatWrapper> cats) {
        String catsJson = null;
        try {
            catsJson = objectMapper.writeValueAsString(cats);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error converting object to JSON");
        }
        return ResponseEntity.ok(catsJson);
    }
}
