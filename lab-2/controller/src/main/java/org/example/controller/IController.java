package org.example.controller;

public interface IController {
    void start();
    void addCat();
    void deleteCat(long id);

    void addOwner();
    void deleteOwner(long id);
}
