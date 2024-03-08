package org.example.Accounts;

import org.example.Banks.Bank;
import org.example.Banks.CentralBank;
import org.example.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the functionality of the CentralBank class.
 */
public class CentralBankTest {
    private CentralBank centralBank;
    private Bank bank1;
    private Bank bank2;
    private User user1;
    private User user2;
    private Account account1;
    private Account account2;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        centralBank = new CentralBank();
        bank1 = centralBank.getBankBuilder().name("Alpha").debitInterestRate(3650).suspiciousLimit(10000).build();
        bank2 = centralBank.getBankBuilder().name("Sber").debitInterestRate(365).suspiciousLimit(1000).build();

        centralBank.registerBank(bank1);
        centralBank.registerBank(bank2);

        user1 = new User("Mister", "Johnson");
        user2 = new User("Miss", "Smith");

        account1 = new DebitAccount(user1, bank1.getDebitInterestRate(), bank1.getSuspiciousLimit());
        account2 = new DebitAccount(user2, bank2.getDebitInterestRate(), bank2.getSuspiciousLimit());
        bank1.addAccount(account1);
        bank2.addAccount(account2);

        account1.deposit(100);
    }

    /**
     * Tests the transfer functionality of the CentralBank class.
     */
    @Test
    void transferTest() {
        centralBank.transfer(bank1, account1, bank2, account2, 100);
        Assertions.assertEquals(0, account1.getBalance());
        Assertions.assertEquals(100, account2.getBalance());
    }

    /**
     * Tests the interest rate functionality of the CentralBank class.
     */
    @Test
    void interestRateTest(){
        account2.deposit(100);

        centralBank.dailyUpdate();
        centralBank.monthlyUpdate();

        Assertions.assertEquals(110, account1.getBalance());
        Assertions.assertEquals(101, account2.getBalance());
    }
}
