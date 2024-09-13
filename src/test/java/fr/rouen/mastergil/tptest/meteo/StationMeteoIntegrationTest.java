package fr.rouen.mastergil.tptest.meteo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;

class StationMeteoIntegrationTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void restore() {
        System.setOut(standardOut);
    }

    @Test
    public void shouldMajPrevisionReturnForecastForParis() {
        //GIVEN
        IWeatherProvider weatherProvider = new OpenWeatherMapProvider();
        StationMeteo stationMeteo = new StationMeteo(weatherProvider);
        String city = "Paris,FR";
        //WHEN
        List<Prevision> result = stationMeteo.majPrevision(city);
        //THEN
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();

    }

    @Test
    public void shouldMajPrevisionThrowIllegalArgumentException() {
        //GIVEN
        IWeatherProvider weatherProvider = new OpenWeatherMapProvider();
        StationMeteo stationMeteo = new StationMeteo(weatherProvider);
        String city = null;
        //THEN
        assertThatException().isThrownBy(
                //WHEN
                () -> stationMeteo.majPrevision(city)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldMajPrevisionThrowRuntimeException() {
        //GIVEN
        IWeatherProvider weatherProvider = new OpenWeatherMapProvider();
        StationMeteo stationMeteo = new StationMeteo(weatherProvider);
        String city = "gilbertonin";
        //THEN
        assertThatException().isThrownBy(
                //WHEN
                () -> stationMeteo.majPrevision(city)
        ).isInstanceOf(RuntimeException.class);
    }

}
