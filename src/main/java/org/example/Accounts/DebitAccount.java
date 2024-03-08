package org.example.Accounts;

import org.example.User;

/**
 * This class represents a debit account.
 * It extends the abstract Account class.
 */
public class DebitAccount extends Account {
    private double interestRate;
    private double accruedInterest;

    /**
     * Constructs a new DebitAccount with the specified user, interest rate, and suspicious limit.
     * @param user the user of the account.
     * @param interestRate the interest rate of the account.
     * @param suspiciousLimit the suspicious limit of the account.
     */
    public DebitAccount(User user, double interestRate, double suspiciousLimit) {
        this.user = user;
        this.interestRate = interestRate;
        this.id = generateUniqueId();
        this.suspiciousLimit = suspiciousLimit;
    }

    /**
     * Updates the accrued interest daily based on the balance and interest rate.
     */
    public void dailyUpdate() {
        accruedInterest += balance * ( interestRate / ( 365 * 100 ) );
    }

    /**
     * Updates the balance monthly by adding the accrued interest and resets the accrued interest.
     */
    public void monthlyUpdate() {
        balance += accruedInterest;
        accruedInterest = 0;
    }

    /**
     * Sets the interest rate of the account.
     * @param interestRate the interest rate to be set.
     */
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
