package org.example;

import org.example.accounts.Account;
import org.example.accounts.CreditAccount;
import org.example.accounts.DebitAccount;
import org.example.accounts.DepositAccount;
import org.example.banks.Bank;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class UserInterface {
    private Bank bank;
    private Scanner scanner;
    private User user;
    private Account account;

    public UserInterface(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
        bank.setSuspiciousLimit(1000);
        bank.setCreditLimit(1000);
        bank.setCommission(10);
    }

    public void start() {
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Создать пользователя");
            System.out.println("2. Создать аккаунт");
            System.out.println("3. Перевод по ID");
            System.out.println("4. Внести деньги на аккаунт");
            System.out.println("5. Снять деньги с аккаунта");
            System.out.println("6. Просмотреть баланс по ID");
            System.out.println("7. Выход");

            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1:
                    System.out.println("Введите имя:");
                    String firstname = scanner.nextLine();
                    System.out.println("Введите фамилию:");
                    String lastname = scanner.nextLine();
                    user = new User(firstname, lastname);
                    System.out.println("Пользователь " + user.getName() + " " + user.getSurname() + " успешно создан");
                    break;
                case 2:
                    System.out.println("Введите тип аккаунта (credit, deposit, debit):");
                    String type = scanner.nextLine();
                    if (Objects.equals(type, "credit")) {
                        account = new CreditAccount(user, bank.getCreditLimit(), bank.getCommission(), bank.getSuspiciousLimit());
                        bank.addAccount(account);
                    } else if (Objects.equals(type, "deposit")) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.MONTH, 1);
                        Date depositEndDate = calendar.getTime();
                        account = new DepositAccount(user, bank.getDepositInterestRate(), depositEndDate, bank.getSuspiciousLimit());
                        bank.addAccount(account);
                    } else if (Objects.equals(type, "debit")) {
                        account = new DebitAccount(user, bank.getDebitInterestRate(), bank.getSuspiciousLimit());
                        bank.addAccount(account);
                    }
                    System.out.println("Аккаунт с ID:" + account.getId() + " успешно создан");
                    break;
                case 3:
                    System.out.println("Введите ID аккаунта-отправителя:");
                    int fromId = scanner.nextInt();
                    System.out.println("Введите ID аккаунта-получателя:");
                    int toId = scanner.nextInt();
                    System.out.println("Введите сумму для перевода:");
                    double amount = scanner.nextDouble();
                    if (bank.transfer(bank.getAccountById(fromId), bank.getAccountById(toId), amount))
                        System.out.println("Перевод успешно выполнен");
                    else
                        System.out.println("Перевод не удался");
                    break;
                case 4:
                    System.out.println("Введите ID аккаунта:");
                    int depositId = scanner.nextInt();
                    System.out.println("Введите сумму для внесения:");
                    double depositAmount = scanner.nextDouble();
                    Account depositAccount = bank.getAccountById(depositId);
                    if (depositAccount != null && depositAccount.deposit(depositAmount))
                        System.out.println("Внесение успешно выполнено, на счету: " + account.getBalance());
                    else
                        System.out.println("Внесение не удалось");
                    break;
                case 5:
                    System.out.println("Введите ID аккаунта:");
                    int withdrawId = scanner.nextInt();
                    System.out.println("Введите сумму для снятия:");
                    double withdrawAmount = scanner.nextDouble();
                    Account withdrawAccount = bank.getAccountById(withdrawId);
                    if (withdrawAccount != null && withdrawAccount.withdraw(withdrawAmount))
                        System.out.println("Снятие успешно выполнено, на счету: " + account.getBalance());
                    else
                        System.out.println("Снятие не удалось");
                    break;
                case 6:
                    System.out.println("Введите ID аккаунта:");
                    int id = scanner.nextInt();
                    Account account = bank.getAccountById(id);
                    if (account != null)
                        System.out.println("Баланс аккаунта: " + account.getBalance());
                    else
                        System.out.println("Аккаунт не найден");
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Неверный ввод. Пожалуйста, попробуйте еще раз.");
            }
        }
    }
}
