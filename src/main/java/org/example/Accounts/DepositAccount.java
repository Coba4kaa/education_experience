package org.example.Accounts;

import org.example.Transaction;
import org.example.User;

import java.util.Date;

/**
 * This class represents a deposit account.
 * It extends the abstract Account class.
 */
public class DepositAccount extends Account {
    private final Date depositEndDate;
    private double interestRate;
    private double accruedInterest;

    /**
     * Constructs a new DepositAccount with the specified user, interest rate, deposit end date, and suspicious limit.
     * @param user the user of the account.
     * @param interestRate the interest rate of the account.
     * @param depositEndDate the deposit end date of the account.
     * @param suspiciousLimit the suspicious limit of the account.
     */
    public DepositAccount(User user, double interestRate, Date depositEndDate, double suspiciousLimit) {
        this.user = user;
        this.interestRate = interestRate;
        this.depositEndDate = depositEndDate;
        this.id = generateUniqueId();
        this.suspiciousLimit = suspiciousLimit;
    }

    /**
     * Withdraws an amount from the account considering the deposit end date.
     * @param amount the amount to be withdrawn.
     * @return true if the withdrawal was successful, false otherwise.
     */
    @Override
    public boolean withdraw(double amount) {
        Date currentDate = new Date();
        if (currentDate.after(depositEndDate) && amount > 0 && balance - amount >= 0 && (!user.isSuspicious() || amount <= suspiciousLimit)) {
            balance -= amount;
            transactions.add(new Transaction(this, null, amount));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates the accrued interest daily based on the balance and interest rate.
     */
    public void dailyUpdate() {
        accruedInterest += balance * ( interestRate / ( 365 * 100) );
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
