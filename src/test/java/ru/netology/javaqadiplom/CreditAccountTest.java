package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldNotAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(-3_000);

        Assertions.assertEquals(0, account.getBalance());
    }


    @Test
    public void negativeInitialBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new CreditAccount(-3000, 5_000, 15));
    }


    @Test
    public void negativeCreditLimit() {

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new CreditAccount(0, -5_000, 15));
    }


    @Test
    public void negativeRate() {

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new CreditAccount(0, 5_000, -15));
    }


    @Test
    public void negativePayment() {
        CreditAccount account = new CreditAccount(
                3000,
                5_000,
                15
        );
        account.pay(-2_000);

        Assertions.assertEquals(3000, account.getBalance());
    }


    @Test
    public void balanceAbovePaymentAmount() {
        CreditAccount account = new CreditAccount(
                3000,
                5_000,
                15
        );
        account.pay(2_000);

        Assertions.assertEquals(1000, account.getBalance());
    }


    @Test
    public void balanceBelowPaymentAmount() {
        CreditAccount account = new CreditAccount(
                3000,
                5_000,
                15
        );
        account.pay(5_000);

        Assertions.assertEquals(-2000, account.getBalance());
    }


    @Test
    public void balancePlusCreditLimitBelowPaymentAmount() { // оплата не прошла
        CreditAccount account = new CreditAccount(
                3000,
                5_000,
                15
        );
        account.pay(10_000);

        Assertions.assertEquals(3000, account.getBalance());
    }


    @Test
    public void annualLoanDebt() {
        CreditAccount account = new CreditAccount(
                3000,
                5_000,
                15
        );

        account.pay(6_000);

        Assertions.assertEquals(-450, account.yearChange());
    }


    @Test
    public void annualLoanDebt0() {
        CreditAccount account = new CreditAccount(
                3000,
                5_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange());
    }

}
