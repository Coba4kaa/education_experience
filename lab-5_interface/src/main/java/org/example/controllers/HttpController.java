package org.example.controllers;

import org.example.data_model.Cat;
import org.example.data_model.Owner;
import org.example.kafka.KafkaService;
import org.example.repos.CatRepository;
import org.example.repos.OwnerRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class HttpController {
    private final KafkaService kafkaService;

    private final OwnerRepository ownerRepository;

    private final CatRepository catRepository;

    public HttpController(KafkaService kafkaService, OwnerRepository ownerRepository, CatRepository catRepository) {
        this.kafkaService = kafkaService;
        this.ownerRepository = ownerRepository;
        this.catRepository = catRepository;
    }

    @GetMapping("/cats/{id}")
    public Cat getCat(@PathVariable("id") Long id, Principal principal) throws Exception {
        Object cat = kafkaService.kafkaRequestReply("cats_request", id.toString());
        Object owner = kafkaService.kafkaRequestReply("owners_request", Cat.fromString(cat.toString()).getOwnerId().toString());
        if (!Objects.equals(principal.getName(), Owner.fromString(owner.toString()).getName()) && !Objects.equals(ownerRepository.findByName(principal.getName()).get().getRole(), "ROLE_ADMIN"))
            return null;
        return Cat.fromString(cat.toString());
    }

    @GetMapping("/owners/{id}")
    public Owner getOwner(@PathVariable("id") Long id, Principal principal) throws Exception {
        Object owner = kafkaService.kafkaRequestReply("owners_request", id.toString());
        if (!Objects.equals(principal.getName(), Owner.fromString(owner.toString()).getName()) && !Objects.equals(ownerRepository.findByName(principal.getName()).get().getRole(), "ROLE_ADMIN"))
            return null;
        return Owner.fromString(owner.toString());
    }

    @GetMapping("/owners/name/{name}")
    public Owner getOwnerByName(@PathVariable("name") String name) {
        return ownerRepository.findByName(name).get();
    }

    @GetMapping("/cats/all")
    public List<Cat> getAllCats(Principal principal){
        String currentUserName = principal.getName();

        Owner currentOwner = ownerRepository.findByName(currentUserName)
                .orElseThrow(() -> new RuntimeException("Owner not found: " + currentUserName));

        List<Cat> allCats = catRepository.getAllCats();

        if (Objects.equals(currentOwner.getRole(), "ROLE_ADMIN"))
            return allCats;

        List<Cat> filteredCats = allCats.stream()
                .filter(cat -> Objects.equals(cat.getOwnerId(), currentOwner.getId()))
                .collect(Collectors.toList());

        return filteredCats;
    }


    @GetMapping("/owners/all")
    public List<Owner> getAllOwners(Principal principal) {
        Owner currentUser = ownerRepository.findByName(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if ("ROLE_ADMIN".equals(currentUser.getRole()))
            return ownerRepository.getAllOwners();
        else
            return List.of(currentUser);
    }
}
