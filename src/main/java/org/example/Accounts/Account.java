package org.example.Accounts;

import org.example.Transaction;
import org.example.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This abstract class represents a generic account.
 */
public abstract class Account {
    protected User user;
    protected double balance;
    protected double suspiciousLimit;
    protected List<Transaction> transactions = new ArrayList<>();
    protected int id;

    /**
     * Generates a unique ID for the account.
     * @return a unique ID.
     */
    protected int generateUniqueId() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

    /**
     * Deposits an amount to the account.
     * @param amount the amount to be deposited.
     * @return true if the deposit was successful, false otherwise.
     */
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction(null, this, amount));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Withdraws an amount from the account.
     * @param amount the amount to be withdrawn.
     * @return true if the withdrawal was successful, false otherwise.
     */
    public boolean withdraw(double amount) {
        if (amount > 0 && balance - amount >= 0 && (!user.isSuspicious() || amount <= suspiciousLimit)) {
            balance -= amount;
            transactions.add(new Transaction(this, null, amount));
        }
        else
            return false;
        return true;
    }

    /**
     * Gets the current balance of the account.
     * @return the current balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gets the user of the account.
     * @return the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the ID of the account.
     * @return the ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user of the account.
     * @param user the user to be set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Sets the suspicious limit of the account.
     * @param suspiciousLimit the suspicious limit to be set.
     */
    public void setSuspiciousLimit(double suspiciousLimit) {
        this.suspiciousLimit = suspiciousLimit;
    }
}
