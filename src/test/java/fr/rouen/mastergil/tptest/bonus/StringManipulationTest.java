package fr.rouen.mastergil.tptest.bonus;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringManipulationTest {

    @Test
    public void shouldMethodAReturnTrueWithValidPalindrome() {
        //GIVEN
        String parameter = "kayak";
        //WHEN
        boolean result = StringManipulation.a(parameter);
        //THEN
        assertThat(result).isTrue();
    }

    @Test
    public void shouldMethodAReturnFalseWithInvalidPalindrome() {
        //GIVEN
        String parameter = "gilbert";
        //WHEN
        boolean result = StringManipulation.a(parameter);
        //THEN
        assertThat(result).isFalse();
    }

}