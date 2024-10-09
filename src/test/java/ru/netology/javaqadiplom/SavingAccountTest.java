package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    //Тестируем негативный сценарий с возвращением IllegalArgumentException
    //когда MinBalance больше MaxBalance
    @Test
    public void negativeMinBalanceOverMaxBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                //new CreditAccount(0, 5_000, -15));
                new SavingAccount(100, 100, 50, 5));
    }

    //Тестируем негативный сценарий с возвращением IllegalArgumentException
    //когда InitialBalance меньше нуля
    @Test
    public void negativeInitialBalanceLessZero() {

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                //new CreditAccount(0, 5_000, -15));
                new SavingAccount(-100, 100, 250, 5));
    }

    //1
    //Тестируем негативный сценарий с возвращением IllegalArgumentException
    //когда MinBalance и MaxBalance меньше 0
    @Test
    public void negativeMinBalanceAndMaxBalanceLessZero() {

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                //new CreditAccount(0, 5_000, -15));
                new SavingAccount(100, -100, -150, 5));
    }

    //2
    //Тестируем негативный сценарий с возвращением IllegalArgumentException
    //когда InitialBalance меньше MinBalance
    @Test
    public void negativeInitialBalanceLessMinBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                //new CreditAccount(0, 5_000, -15));
                new SavingAccount(10, 50, 150, 5));
    }

    //3
    //Тестируем негативный сценарий с возвращением IllegalArgumentException
    //когда InitialBalance больше MaxBalance
    @Test
    public void negativeInitialBalanceOverMaxBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                //new CreditAccount(0, 5_000, -15));
                new SavingAccount(250, 50, 150, 5));
    }

    //4
    //Тестируем негативный сценарий с возвращением IllegalArgumentException
    //когда Rate меньше нуля
    @Test
    public void negativeRateLessZero() {

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                //new CreditAccount(0, 5_000, -15));
                new SavingAccount(150, 50, 250, -5));
    }


    @Test
    public void PayFalseForAmountEqZero() {
        SavingAccount savingAccount = new SavingAccount(
                10, 0, 100, 5
        );
        boolean expected = false;
        boolean actual = savingAccount.pay(0);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void PayFalseForAmountLessZero() {
        SavingAccount savingAccount = new SavingAccount(
                10, 0, 100, 5
        );
        boolean expected = false;
        boolean actual = savingAccount.pay(-2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void PayTrueForBalanceOverMinBalance() {
        SavingAccount savingAccount = new SavingAccount(
                10, 0, 100, 5
        );
        int amount = 5;
        boolean expected = true;
        boolean actual = savingAccount.pay(amount);
        Assertions.assertEquals(expected, actual);
    }

    //Тестируем расчёт Balance при оплате, когда Balance меньше MinBalance
    //initialBalance = -5, т.е. меньше MinBalance = 0
    //значит согласно условию Balance не должен меняться
    //но получаем -7, т.е. фактически срабатывает формула вне зависимости от условия
    @Test
    public void PayCalculateForBalanceLessMinBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new SavingAccount(-5, 0, 100, 5));


//        SavingAccount SavingAccount = new SavingAccount(
//                -5, 0, 100, 5
//        );
//        int amount = 2;
//        int expected = -5;
//
//        boolean res = SavingAccount.pay(amount);
//        int actual = SavingAccount.balance;
//        Assertions.assertEquals(expected, actual);
    }


    //Тестируем расчёт Balance при пополнении, когда сумма initialBalance и amount
    //меньше MaxBalance
    //initialBalance = 10, amount = 70, их сумма меньше MaxBalance = 100
    //значит согласно формуле Balance = Balance + amount = 80
    //но получаем 70, т.е. фактически Balance = amount
    @Test
    public void AddCalculateForBalancePlusAmountLessMaxBalance() {
        SavingAccount savingAccount = new SavingAccount(
                10, 0, 100, 5
        );
        int amount = 70;
        int expected = 80;

        boolean res = savingAccount.add(amount);
        int actual = savingAccount.balance;
        Assertions.assertEquals(expected, actual);
    }


    //Тестируем расчёт Balance при пополнении, когда сумма initialBalance и amount
    //равна MaxBalance
    //initialBalance = 10, amount = 90, их сумма равна MaxBalance = 100
    //значит согласно формуле Balance = Balance + amount = 100
    //но получаем 10, т.е. фактически формула не срабатывает
    @Test
    public void AddCalculateForBalancePlusAmountEqMaxBalance() {
        SavingAccount savingAccount = new SavingAccount(
                10, 0, 100, 5
        );
        int amount = 90;
        int expected = 100;

        boolean res = savingAccount.add(amount);
        int actual = savingAccount.balance;
        Assertions.assertEquals(expected, actual);
    }

    //

    //Тестируем расчёт процентов на остаток счёта при условии, что
    // счёт не меняется год
    //initialBalance = 200, rate = 15
    //значит согласно формуле balance / 100 * rate получаем 30
    @Test
    public void annualLoanDebt0() {
        SavingAccount savingAccount = new SavingAccount(
                200, 10, 300, 15
        );

        Assertions.assertEquals(30, savingAccount.yearChange());
    }

    //5
    //Расчёт процентов на остаток счёта не исключен если со счёта
    //Тестируем расчёт процентов на остаток счёта
    //initialBalance = 200, rate = 15 и пополнение на 200
    //значит   расчёт процентов на остаток счёта выполняется по формуле balance / 100 * rate
    @Test
    public void annualLoanDebt() {
        SavingAccount savingAccount = new SavingAccount(
                200, 10, 300, 15
        );

        savingAccount.pay(100);

        Assertions.assertEquals(15, savingAccount.yearChange());
    }


}
