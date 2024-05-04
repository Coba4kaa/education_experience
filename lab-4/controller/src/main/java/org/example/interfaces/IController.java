package org.example.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

public interface IController {
    ResponseEntity<String> createOwner(@RequestParam(name = "ownerName") String ownerName);

    ResponseEntity<String> createCat(@RequestParam(name = "catName") String catName);

    ResponseEntity<String> getCatById(@PathVariable("id") Long id, Principal principal);

    ResponseEntity<String> getOwnerById(@PathVariable("id") Long id, Principal principal);
}
