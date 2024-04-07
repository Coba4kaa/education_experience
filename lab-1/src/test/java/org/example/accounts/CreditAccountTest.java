package org.example.accounts;

import org.example.banks.Bank;
import org.example.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the functionality of the CreditAccount class.
 */
public class CreditAccountTest {
    private Bank bank;
    private User user1;
    private Account account1;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        bank = new Bank("Alpha");
        bank.setCommission(100);
        bank.setCreditLimit(1000);
        bank.setSuspiciousLimit(1000);

        user1 = new User("Mister", "Johnson");

        account1 = new CreditAccount(user1, bank.getCreditLimit(), bank.getCommission(), bank.getSuspiciousLimit());
    }

    /**
     * Tests the credit limit functionality of the CreditAccount class.
     */
    @Test
    void creditLimitTest() {
        account1.withdraw(100);
        Assertions.assertFalse(account1.withdraw(901));
    }

    /**
     * Tests the commission functionality of the CreditAccount class.
     */
    @Test
    void commissionTest() {
        account1.withdraw(100);
        Assertions.assertEquals(-200, account1.getBalance());
    }

    /**
     * Tests the suspicious limit functionality of the CreditAccount class.
     */
    @Test
    void suspiciousLimitTest() {
        Assertions.assertFalse(account1.withdraw(1001));
    }
}
