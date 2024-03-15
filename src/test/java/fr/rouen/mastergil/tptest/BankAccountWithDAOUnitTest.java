package fr.rouen.mastergil.tptest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BankAccountWithDAOUnitTest {

    @Mock
    JdbcDAO dao;

    @InjectMocks
    BankAccountWithDAO bankAccount;

    @BeforeEach
    public void init() {
        Connection connection = Mockito.mock(Connection.class);
        Mockito.when(dao.setUpConnection()).thenReturn(connection);
    }

    @Test
    public void shouldCreerCompteWithValidAccountNotFail() {
        //GIVEN
        //THEN
        assertThatNoException().isThrownBy(
                //WHEN
                bankAccount::creerCompte
        );

    }

    @Test
    public void shouldCreerCompteWithReadOnlyConnectionFail() {
        //GIVEN
        Connection connection = Mockito.mock(Connection.class);
        try {
            Mockito.when(connection.isReadOnly()).thenReturn(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Mockito.when(dao.setUpConnection()).thenReturn(connection);
        //THEN
        assertThatExceptionOfType(ConnectException.class).isThrownBy(
                //WHEN
                bankAccount::creerCompte
        );

    }

    @Test
    public void shouldCreerCompteWithClosedConnectionFail() {
        //GIVEN
        Connection connection = Mockito.mock(Connection.class);
        try {
            Mockito.when(connection.isClosed()).thenReturn(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Mockito.when(dao.setUpConnection()).thenReturn(connection);
        //THEN
        assertThatExceptionOfType(ConnectException.class).isThrownBy(
                //WHEN
                bankAccount::creerCompte
        );

    }

    @Test
    public void shouldCreerCompteWithInvalidDAOFail() {
        //GIVEN
        Connection connection = Mockito.mock(Connection.class);
        try {
            Mockito.when(connection.isReadOnly()).thenThrow(SQLException.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Mockito.when(dao.setUpConnection()).thenReturn(connection);
        //THEN
        assertThatExceptionOfType(SQLException.class).isThrownBy(
                //WHEN
                bankAccount::creerCompte
        );

    }

    @Test
    public void shouldCreerCompteWithMontantAndDeviseNotFail() {
        //GIVEN
        int montant = 15;
        Devise devise = Devise.DOLLAR;
        //THEN
        assertThatNoException().isThrownBy(
                //WHEN
                () -> bankAccount.creerCompte(montant, devise)
        );
    }

    @Test
    public void shouldConsulterSoldeWith15DollarReturnValidString() {
        //GIVEN
        int amount = 15;
        Devise devise = Devise.DOLLAR;
        Money money = new Money(amount, devise);
        Mockito.when(dao.getSolde()).thenReturn(money);
        String expectedResult = "Votre solde actuel est de "
                + amount + " " + devise.name();
        //WHEN
        String result = "";
        try {
            result = bankAccount.consulterSolde();
        } catch (SQLException | ConnectException e) {
            throw new RuntimeException(e);
        }
        //THEN
        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    public void shouldDeposerArgentOn0EuroWith15EuroReturn15Euro() {
        //GIVEN
        int amount = 0;
        Devise devise = Devise.EURO;
        Money money = new Money(amount, devise);
        Mockito.when(dao.getSolde()).thenReturn(money);
        int addedMoney = 15;
        //WHEN
        Money result;
        try {
            result = bankAccount.deposerArgent(addedMoney);
        } catch (SQLException | ConnectException e) {
            throw new RuntimeException(e);
        }
        //THEN
        assertThat(result).isNotNull();
        assertThat(result.getMontant()).isEqualTo(addedMoney);
        assertThat(result.getDevise()).isEqualTo(devise);
    }

    @Test
    public void shouldRetirerArgentOn15EuroWith15EuroReturn0Euro() {
        //GIVEN
        int amount = 15;
        Devise devise = Devise.EURO;
        Money money = new Money(amount, devise);
        Mockito.when(dao.getSolde()).thenReturn(money);
        int deletedMoney = 15;
        int expectedResult = 0;
        //WHEN
        Money result;
        try {
            result = bankAccount.retirerArgent(deletedMoney);
        } catch (SQLException | ConnectException e) {
            throw new RuntimeException(e);
        }
        //THEN
        assertThat(result).isNotNull();
        assertThat(result.getMontant()).isEqualTo(expectedResult);
        assertThat(result.getDevise()).isEqualTo(devise);
    }


    @Test
    public void shouldIsSoldePositifWithPositifSoldeReturnTrue() {
        //GIVEN
        int amount = 1;
        Devise devise = Devise.EURO;
        Money money = new Money(amount, devise);
        Mockito.when(dao.getSolde()).thenReturn(money);
        //WHEN
        boolean result;
        try {
            result = bankAccount.isSoldePositif();
        } catch (SQLException | ConnectException e) {
            throw new RuntimeException(e);
        }
        //THEN
        assertThat(result).isTrue();
    }




}