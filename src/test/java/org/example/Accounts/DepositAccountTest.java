package org.example.Accounts;

import org.example.Banks.Bank;
import org.example.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * This class tests the functionality of the DepositAccount class.
 */
public class DepositAccountTest {
    private Bank bank;
    private User user1;
    private Account account1;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        bank = new Bank("Alpha");
        bank.setDepositInterestRate(365);
        bank.setSuspiciousLimit(1000);

        user1 = new User("Mister", "Johnson");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date depositEndDate = calendar.getTime();

        account1 = new DepositAccount(user1, bank.getDepositInterestRate(), depositEndDate, bank.getSuspiciousLimit());
        account1.deposit(100);
        bank.addAccount(account1);
    }

    /**
     * Tests the withdraw method of the DepositAccount class.
     */
    @Test
    void withdrawTest() {
        Assertions.assertFalse(account1.withdraw(100));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        account1 = new DepositAccount(user1, bank.getDepositInterestRate(), yesterday, bank.getSuspiciousLimit());
        account1.deposit(100);

        Assertions.assertTrue(account1.withdraw(100));
    }

    /**
     * Tests the interest rate functionality of the DepositAccount class.
     */
    @Test
    void interestRateTest(){
        bank.dailyUpdate();
        bank.monthlyUpdate();
        Assertions.assertEquals(101, account1.getBalance());
    }
}
