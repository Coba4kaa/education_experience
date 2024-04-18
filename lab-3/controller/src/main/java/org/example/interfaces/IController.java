package org.example.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface IController {
    ResponseEntity<String> createOwner(@RequestParam(name = "ownerName") String ownerName);

    ResponseEntity<String> createCat(@RequestParam(name = "catName") String catName);

    ResponseEntity<String> getCatById(@PathVariable("id") Long id);

    ResponseEntity<String> getOwnerById(@PathVariable("id") Long id);
}
