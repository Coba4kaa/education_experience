package org.example.accounts;

import org.example.banks.Bank;
import org.example.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the functionality of the DebitAccount class.
 */
class DebitAccountTest {
    private Bank bank;
    private User user1;
    private User user2;
    private Account account1;
    private Account account2;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        bank = new Bank("Alpha");
        bank.setSuspiciousLimit(1000);

        user1 = new User("Mister", "Johnson");
        user2 = new User("Miss", "Smith");

        account1 = new DebitAccount(user1, bank.getDebitInterestRate(), bank.getSuspiciousLimit());
        account2 = new DebitAccount(user2, bank.getDebitInterestRate(), bank.getSuspiciousLimit());
        bank.addAccount(account1);
        bank.addAccount(account2);
        account1.deposit(100);
    }

    /**
     * Tests the getBalance method of the DebitAccount class.
     */
    @Test
    void getBalanceTest() {
        Assertions.assertEquals(100, account1.getBalance());
    }

    /**
     * Tests the deposit method of the DebitAccount class.
     */
    @Test
    void depositTest() {
        account1.deposit(100);
        Assertions.assertEquals(200, account1.getBalance());
    }

    /**
     * Tests the withdraw method of the DebitAccount class.
     */
    @Test
    void withDrawTest() {
        account1.withdraw(50);
        Assertions.assertEquals(50, account1.getBalance());
    }

    /**
     * Tests the transfer method of the DebitAccount class.
     */
    @Test
    void transferTest() {
        bank.transfer(account1, account2, 100);
        Assertions.assertEquals(0, account1.getBalance());
        Assertions.assertEquals(100, account2.getBalance());
    }

    /**
     * Tests the suspicious limit functionality of the DebitAccount class.
     */
    @Test
    void suspiciousLimitTest() {
        account1.setSuspiciousLimit(0);
        Assertions.assertFalse(account1.withdraw(50));

        user1.setAddress("RU");
        user1.setPassportData("DATA");
        account1.setUser(user1);

        Assertions.assertTrue(account1.withdraw(50));
    }
}
