package org.example;

import org.example.banks.Bank;

public class Program {
    public static void main(String[] args) {
        Bank bank = new Bank("Sber");
        UserInterface userInterface = new UserInterface(bank);
        userInterface.start();
    }
}
