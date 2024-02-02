package fr.rouen.mastergil.tptest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyUnitTest {

    @Test
    public void shouldMoneyConstructReturnZeroEuro() {
         //GIVEN
         Money money;
         int amount = 0;
         Devise devise = Devise.EURO;
         //WHEN
         money = new Money();
         //THEN
         assertEquals(amount, money.getMontant());
         assertEquals(devise, money.getDevise());
    }

    @Test
    public void shouldMoneyConstructSuccessWithValidAmountAndValidDevise() {
        //GIVEN
        Money money;
        int amount = 5;
        Devise devise = Devise.DOLLAR;
        //WHEN
        money = new Money(amount, devise);
        //THEN
        assertEquals(amount, money.getMontant());
        assertEquals(devise, money.getDevise());
    }

    @Test
    public void shouldMoneyConstructFailWithNoDevise() {
        //GIVEN
        int amount = 5;
        Devise devise = null;
        //THEN
        assertThrows(IllegalArgumentException.class,
                //WHEN
                () -> new Money(amount, devise));
    }

    @Test
    public void shouldIsPositifReturnTrueWithPositifMontant() {
        //GIVEN
        int amount = 10;
        Money money = new Money(amount, Devise.DOLLAR);
        //WHEN
        boolean result = money.isPositif();
        //THEN
        assertTrue(result);
    }

    @Test
    public void shouldSetDeviseThrowIllegalArgumentExceptionWithInvalidDevice() {
        //GIVEN
        Money money = new Money();
        Devise devise = null;
        //THEN
        assertThrows(IllegalArgumentException.class,
                //WHEN
                () -> money.setDevise(devise));
    }
}