package org.example.banks;

/**
 * This class represents a builder for a Bank.
 */
public class BankBuilder {
    private String name;
    private double debitInterestRate;
    private double depositInterestRate;
    private double creditLimit;
    private double commission;
    private double suspiciousLimit;

    /**
     * Sets the name of the bank.
     *
     * @param name the name of the bank.
     * @return this builder.
     */
    public BankBuilder name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the debit interest rate of the bank.
     *
     * @param rate the debit interest rate.
     * @return this builder.
     */
    public BankBuilder debitInterestRate(double rate) {
        this.debitInterestRate = rate;
        return this;
    }

    /**
     * Sets the deposit interest rate of the bank.
     *
     * @param rate the deposit interest rate.
     * @return this builder.
     */
    public BankBuilder depositInterestRate(double rate) {
        this.depositInterestRate = rate;
        return this;
    }

    /**
     * Sets the credit limit of the bank.
     *
     * @param creditLimit the credit limit.
     * @return this builder.
     */
    public BankBuilder creditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
        return this;
    }

    /**
     * Sets the commission of the bank.
     *
     * @param commission the commission.
     * @return this builder.
     */
    public BankBuilder commission(double commission) {
        this.commission = commission;
        return this;
    }

    /**
     * Sets the suspicious limit of the bank.
     *
     * @param suspiciousLimit the suspicious limit.
     * @return this builder.
     */
    public BankBuilder suspiciousLimit(double suspiciousLimit) {
        this.suspiciousLimit = suspiciousLimit;
        return this;
    }

    /**
     * Builds a Bank with the set parameters.
     *
     * @return a new Bank.
     */
    public Bank build() {
        Bank bank = new Bank(this.name);
        bank.setDebitInterestRate(this.debitInterestRate);
        bank.setDepositInterestRate(this.depositInterestRate);
        bank.setCreditLimit(this.creditLimit);
        bank.setCommission(this.commission);
        bank.setSuspiciousLimit(this.suspiciousLimit);
        return bank;
    }
}
