package org.example.Banks;

import org.example.Accounts.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a central bank.
 */
public class CentralBank {
    private List<Bank> banks;
    private BankBuilder bankBuilder;

    /**
     * Constructs a new CentralBank.
     */
    public CentralBank() {
        this.banks = new ArrayList<>();
        this.bankBuilder = new BankBuilder();
    }

    /**
     * Gets the bank builder of the central bank.
     * @return the bank builder.
     */
    public BankBuilder getBankBuilder() {
        return bankBuilder;
    }

    /**
     * Registers a bank to the central bank.
     * @param bank the bank to be registered.
     */
    public void registerBank(Bank bank) {
        banks.add(bank);
    }

    /**
     * Transfers an amount from one account in a bank to another account in another bank.
     * @param fromBank the bank to transfer from.
     * @param fromAccount the account to transfer from.
     * @param toBank the bank to transfer to.
     * @param toAccount the account to transfer to.
     * @param amount the amount to be transferred.
     * @return true if the transfer was successful, false otherwise.
     */
    public boolean transfer(Bank fromBank, Account fromAccount, Bank toBank, Account toAccount, double amount) {
        if (fromBank.getAccount(fromAccount).withdraw(amount))
            return toBank.getAccount(toAccount).deposit(amount);
        else
            return false;
    }

    /**
     * Updates all banks daily.
     */
    public void dailyUpdate() {
        for (Bank bank : banks)
            bank.dailyUpdate();
    }

    /**
     * Updates all banks monthly.
     */
    public void monthlyUpdate() {
        for (Bank bank : banks)
            bank.monthlyUpdate();
    }
}
