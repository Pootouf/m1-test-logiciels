package fr.rouen.mastergil.tptest;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class BankAccountUnitTest {

    @Test
    public void shouldCreerCompteCreateAValidBankAccount(){
        //GIVEN
        BankAccount bankAccount = new BankAccount();
        //WHEN
        bankAccount.creerCompte();
        //THEN
        assertThat(bankAccount.solde).isNotNull();
    }

    @Test
    public void shouldCreerCompteWithMontantAndDeviseCreateAValidBankAccount(){
        //GIVEN
        BankAccount bankAccount = new BankAccount();
        int montant = 5;
        Devise devise = Devise.DOLLAR;
        //WHEN
        bankAccount.creerCompte(montant, devise);
        //THEN
        assertThat(bankAccount.solde).isNotNull();
        assertThat(bankAccount.solde.getDevise()).isEqualTo(devise);
        assertThat(bankAccount.solde.getMontant()).isEqualTo(montant);
    }

    @Test
    public void shouldConsulterSoldeWithEmptyAccountInEuroReturnValidString() {
        //GIVEN
        BankAccount bankAccount = new BankAccount();
        Money money = new Money();
        bankAccount.solde = money;
        String expectedString = "Votre solde actuel est de " +
                money.getMontant() + " " +
                money.getDevise().name();
        //WHEN
        String result = bankAccount.consulterSolde();
        //THEN
        assertThat(result).isEqualTo(expectedString);
    }

    @Test
    public void shouldDeposerArgentWithEmptyAccountAnd15MontantIncreaseMoney() {
        //GIVEN
        BankAccount bankAccount = new BankAccount();
        bankAccount.solde = new Money();
        int expectedMontant = 15;
        //WHEN
        bankAccount.deposerArgent(expectedMontant);
        //THEN
        assertThat(bankAccount.solde.getMontant()).isEqualTo(expectedMontant);
    }

    @Test
    public void shouldDeposerArgentWith5DollarAnd35MontantIncreaseMoney() {
        //GIVEN
        BankAccount bankAccount = new BankAccount();
        bankAccount.solde = new Money(5, Devise.DOLLAR);
        int addedMontant = 35;
        int expectedMontant = 40;
        //WHEN
        bankAccount.deposerArgent(addedMontant);
        //THEN
        assertThat(bankAccount.solde.getMontant()).isEqualTo(expectedMontant);
    }

    @Test
    public void shouldRetirerArgentWithEmptyAccountAnd15MontantReduceMoney() {
        //GIVEN
        BankAccount bankAccount = new BankAccount();
        bankAccount.solde = new Money();
        int withdrawMoney = 15;
        int expectedMontant = -15;
        //WHEN
        bankAccount.retirerArgent(withdrawMoney);
        //THEN
        assertThat(bankAccount.solde.getMontant()).isEqualTo(expectedMontant);
    }

    @Test
    public void shouldRetirerArgentWith76YenAnd15MontantReduceMoney() {
        //GIVEN
        BankAccount bankAccount = new BankAccount();
        bankAccount.solde = new Money(76, Devise.YEN);
        int withdrawMoney = 15;
        int expectedMontant = 61;
        //WHEN
        bankAccount.retirerArgent(withdrawMoney);
        //THEN
        assertThat(bankAccount.solde.getMontant()).isEqualTo(expectedMontant);
    }


    @Test
    public void shouldIsPositifWithPositifAccountReturnTrue() {
        //GIVEN
        BankAccount bankAccount = new BankAccount();
        bankAccount.solde = new Money(1, Devise.YEN);
        //WHEN
        boolean result = bankAccount.isSoldePositif();
        //THEN
        assertThat(result).isTrue();
    }

    @Test
    public void shouldIsPositifWithNegativeAccountReturnFalse() {
        //GIVEN
        BankAccount bankAccount = new BankAccount();
        bankAccount.solde = new Money(-1, Devise.PESO);
        //WHEN
        boolean result = bankAccount.isSoldePositif();
        //THEN
        assertThat(result).isFalse();
    }

    @Test
    public void shouldIsPositifWithEmptyAccountReturnTrue() {
        //GIVEN
        BankAccount bankAccount = new BankAccount();
        bankAccount.solde = new Money();
        //WHEN
        boolean result = bankAccount.isSoldePositif();
        //THEN
        assertThat(result).isTrue();
    }

}
