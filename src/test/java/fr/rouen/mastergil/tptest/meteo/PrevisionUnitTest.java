package fr.rouen.mastergil.tptest.meteo;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PrevisionUnitTest {

    @Test
    public void shouldToStringReturnFormattedString() {
        //GIVEN
        Prevision prevision = new Prevision();
        Date date = new Date();
        double tempDay = 0.4;
        double tempNight = 0.3;
        double tempMax = 0.5;
        double tempMin = 0.6;
        String description = "test";
        prevision.setDate(date)
                .setTempDay(tempDay)
                .setTempMax(tempMax)
                .setTempMin(tempMin)
                .setTempNight(tempNight)
                .setDescription(description);
        String expectedResult = "Prevision{" +
            "date=" + prevision.getDate() +
                    ", tempMin=" + prevision.getTempMin() +
                    ", tempMax=" + prevision.getTempMax() +
                    ", tempDay=" + prevision.getTempDay() +
                    ", tempNight=" + prevision.getTempNight() +
                    ", description='" + prevision.getDescription() + '\'' +
                    '}';
        //WHEN
        String result = prevision.toString();
        //THEN
        assertEquals(expectedResult,result);
    }

}