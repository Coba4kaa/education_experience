package org.example.Accounts;

import org.example.Transaction;
import org.example.User;

/**
 * This class represents a credit account.
 * It extends the abstract Account class.
 */
public class CreditAccount extends Account {
    private double creditLimit;
    private double commission;

    /**
     * Constructs a new CreditAccount with the specified user, credit limit, commission, and suspicious limit.
     * @param user the user of the account.
     * @param creditLimit the credit limit of the account.
     * @param commission the commission of the account.
     * @param suspiciousLimit the suspicious limit of the account.
     */
    public CreditAccount(User user, double creditLimit, double commission, double suspiciousLimit) {
        this.user = user;
        this.creditLimit = creditLimit;
        this.commission = commission;
        this.id = generateUniqueId();
        this.suspiciousLimit = suspiciousLimit;
    }

    /**
     * Withdraws an amount from the account considering the credit limit and commission.
     * @param amount the amount to be withdrawn.
     * @return true if the withdrawal was successful, false otherwise.
     */
    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && balance - amount >= -creditLimit && (!user.isSuspicious() || amount <= suspiciousLimit)) {
            balance -= amount;
            if (balance < 0) {
                balance -= commission;
                transactions.add(new Transaction(this, null, amount));
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the commission of the account.
     * @param commission the commission to be set.
     */
    public void setCommission(double commission) {
        this.commission = commission;
    }

    /**
     * Sets the credit limit of the account.
     * @param creditLimit the credit limit to be set.
     */
    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }
}
