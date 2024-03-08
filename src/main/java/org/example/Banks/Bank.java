package org.example.Banks;

import org.example.Accounts.Account;
import org.example.Accounts.DebitAccount;
import org.example.Accounts.DepositAccount;
import org.example.Transaction;
import org.example.User;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a bank.
 */
public class Bank {
    private final String name;
    private double creditLimit;
    private double commission;
    private double debitInterestRate;
    private double depositInterestRate;
    private double suspiciousLimit;
    private Map<Account, Boolean> accounts;

    /**
     * Constructs a new Bank with the specified name.
     * @param name the name of the bank.
     */
    public Bank(String name){
        this.name = name;
        accounts = new HashMap<>();
    }

    /**
     * Adds an account to the bank.
     * @param account the account to be added.
     */
    public void addAccount(Account account) {
        accounts.put(account, false);
    }

    /**
     * Gets an account by its ID.
     * @param id the ID of the account.
     * @return the account if found, null otherwise.
     */
    public Account getAccountById(int id) {
        for (Account account : accounts.keySet())
            if (account.getId() == id)
                return account;
        return null;
    }

    /**
     * Transfers an amount from one account to another.
     * @param from the account to transfer from.
     * @param to the account to transfer to.
     * @param amount the amount to be transferred.
     * @return true if the transfer was successful, false otherwise.
     */
    public boolean transfer(Account from, Account to, double amount) {
        if (from.withdraw(amount))
            return to.deposit(amount);
        else
            return false;
    }

    /**
     * Cancels a transaction.
     * @param transaction the transaction to be cancelled.
     * @return true if the cancellation was successful, false otherwise.
     */
    public boolean cancelTransaction(Transaction transaction) {
        if (!transaction.isCancelled()) {
            transaction.cancel();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Subscribes an account to the bank's notifications.
     * @param account the account to be subscribed.
     */
    public void subscribe(Account account) {
        accounts.put(account, true);
    }

    /**
     * Unsubscribes an account from the bank's notifications.
     * @param account the account to be unsubscribed.
     */
    public void unsubscribe(Account account) {
        accounts.put(account, false);
    }

    /**
     * Notifies all subscribed clients with a message.
     * @param message the message to be sent.
     */
    public void notifyClients(String message) {
        for (Map.Entry<Account, Boolean> entry : accounts.entrySet()) {
            if (entry.getValue()) {
                User user = entry.getKey().getUser();
                if (user != null)
                    user.notify(message);
            }
        }
    }

    /**
     * Updates all accounts daily.
     */
    public void dailyUpdate() {
        for (Account account : accounts.keySet()) {
            if (account instanceof DebitAccount)
                ((DebitAccount) account).dailyUpdate();
            else if (account instanceof DepositAccount)
                ((DepositAccount) account).dailyUpdate();
        }
    }

    /**
     * Updates all accounts monthly.
     */
    public void monthlyUpdate() {
        for (Account account : accounts.keySet()) {
            if (account instanceof DebitAccount)
                ((DebitAccount) account).monthlyUpdate();
            else if (account instanceof DepositAccount)
                ((DepositAccount) account).monthlyUpdate();
        }
    }

    /**
     * Gets an account.
     * @param account the account to be got.
     * @return the account if found, null otherwise.
     */
    public Account getAccount(Account account) {
        if (accounts.containsKey(account))
            return account;
        else
            return null;
    }

    /**
     * Gets the credit limit of the bank.
     * @return the credit limit.
     */
    public double getCreditLimit() {
        return creditLimit;
    }

    /**
     * Gets the commission of the bank.
     * @return the commission.
     */
    public double getCommission() {
        return commission;
    }

    /**
     * Gets the debit interest rate of the bank.
     * @return the debit interest rate.
     */
    public double getDebitInterestRate() {
        return debitInterestRate;
    }

    /**
     * Gets the deposit interest rate of the bank.
     * @return the deposit interest rate.
     */
    public double getDepositInterestRate() {
        return depositInterestRate;
    }

    /**
     * Gets the suspicious limit of the bank.
     * @return the suspicious limit.
     */
    public double getSuspiciousLimit() {
        return suspiciousLimit;
    }

    /**
     * Sets the deposit interest rate of the bank.
     * @param depositInterestRate the deposit interest rate to be set.
     */
    public void setDepositInterestRate(double depositInterestRate) {
        this.depositInterestRate = depositInterestRate;
    }

    /**
     * Sets the debit interest rate of the bank.
     * @param debitInterestRate the debit interest rate to be set.
     */
    public void setDebitInterestRate(double debitInterestRate) {
        this.debitInterestRate = debitInterestRate;
    }

    /**
     * Sets the commission of the bank.
     * @param commission the commission to be set.
     */
    public void setCommission(double commission) {
        this.commission = commission;
    }

    /**
     * Sets the credit limit of the bank.
     * @param creditLimit the credit limit to be set.
     */
    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    /**
     * Sets the suspicious limit of the bank.
     * @param suspiciousLimit the suspicious limit to be set.
     */
    public void setSuspiciousLimit(double suspiciousLimit) {
        this.suspiciousLimit = suspiciousLimit;
    }
}
