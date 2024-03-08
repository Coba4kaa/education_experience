package org.example;

import org.example.Accounts.Account;

/**
 * This class represents a transaction between two accounts.
 */
public class Transaction {
    private Account from;
    private Account to;
    private double amount;
    private boolean isCancelled;

    /**
     * Constructs a new Transaction with the specified from account, to account, and amount.
     * @param from the account to transfer from.
     * @param to the account to transfer to.
     * @param amount the amount to be transferred.
     */
    public Transaction(Account from, Account to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.isCancelled = false;
    }

    /**
     * Cancels the transaction.
     * If the transaction is not already cancelled, it deposits the amount back to the from account and withdraws it from the to account.
     */
    public void cancel() {
        if (!isCancelled) {
            from.deposit(amount);
            to.withdraw(amount);
            isCancelled = true;
        }
    }

    /**
     * Checks if the transaction is cancelled.
     * @return true if the transaction is cancelled, false otherwise.
     */
    public boolean isCancelled() {
        return isCancelled;
    }
}
