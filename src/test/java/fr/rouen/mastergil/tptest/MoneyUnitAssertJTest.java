package fr.rouen.mastergil.tptest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class MoneyUnitAssertJTest {
    @Test
    public void shouldMoneyConstructReturnZeroEuro() {
        //GIVEN
        Money money;
        int expectedAmount = 0;
        Devise expectedDevise = Devise.EURO;
        //WHEN
        money = new Money();
        //THEN
        assertThat(money.getMontant()).isEqualTo(expectedAmount);
        assertThat(money.getDevise()).isEqualTo(expectedDevise);
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
        assertThat(money.getMontant()).isEqualTo(amount);
        assertThat(money.getDevise()).isEqualTo(devise);
    }

    @Test
    public void shouldMoneyConstructFailWithNoDevise() {
        //GIVEN
        int amount = 5;
        Devise devise = null;
        //THEN
        Assertions.assertThrows(IllegalArgumentException.class,
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
        assertThat(result).isTrue();
    }

    @Test
    public void shouldSetDeviseThrowIllegalArgumentExceptionWithInvalidDevice() {
        //GIVEN
        Money money = new Money();
        Devise devise = null;
        //THEN
        Assertions.assertThrows(IllegalArgumentException.class,
                //WHEN
                () -> money.setDevise(devise));
    }
}