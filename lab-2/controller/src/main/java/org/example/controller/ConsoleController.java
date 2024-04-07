package org.example.controller;

import org.example.dataModel.Cat;
import org.example.dataModel.Owner;
import org.example.service.GeneralService;

import java.util.Scanner;

public class ConsoleController implements IController {
    private final GeneralService generalService;
    private final Scanner scanner;

    public ConsoleController() {
        this.generalService = new GeneralService();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить кота");
            System.out.println("2. Удалить кота");
            System.out.println("3. Добавить владельца");
            System.out.println("4. Удалить владельца");
            System.out.println("0. Выйти");

            int choice = scanner.nextInt();
            long id;
            switch (choice) {
                case 1:
                    addCat();
                    break;
                case 2:
                    id = scanner.nextLong();
                    deleteCat(id);
                    break;
                case 3:
                    addOwner();
                    break;
                case 4:
                    id = scanner.nextLong();
                    deleteOwner(id);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }

    @Override
    public void addCat() {
        generalService.createCat(new Cat());
    }

    @Override
    public void deleteCat(long id) {
        generalService.getCatService().deleteCat(id);
    }

    @Override
    public void addOwner() {
        generalService.createOwner(new Owner());
    }

    @Override
    public void deleteOwner(long id) {
        generalService.getOwnerService().deleteOwner(id);
    }

}
