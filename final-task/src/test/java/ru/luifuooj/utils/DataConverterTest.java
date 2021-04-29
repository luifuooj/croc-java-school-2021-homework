package ru.luifuooj.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.luifuooj.model.InputFlightData;
import ru.luifuooj.model.FlightType;
import ru.luifuooj.utils.DataConverter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Тестирование xml-конвертера.
 */
public class DataConverterTest {

    @Test
    public void toXmlTest() throws JsonProcessingException {

        final InputFlightData input = new InputFlightData(
                FlightType.INCOMING,
                "9332",
                "YC",
                "Jamal",
                LocalDateTime.of(2020, 1, 20, 20, 20),
                LocalTime.of(2, 30));

        DataConverter converter = new DataConverter();
        String xml = converter.toXml(input);
        System.out.println(xml);

        final InputFlightData output = new InputFlightData(
                FlightType.OUTBOUND,
                "1234",
                "LN",
                "Yamal",
                LocalDateTime.of(2020, 10, 10, 22, 22),
                LocalTime.of(3, 50));

        DataConverter jaxbConverter = new DataConverter();
        String toXml = converter.toXml(output);
        System.out.println(toXml);
    }

    @Test
    public void fromXmlTest() throws IOException {
        final InputFlightData input = new InputFlightData(
                FlightType.INCOMING,
                "9332",
                "YC",
                "Jamal",
                LocalDateTime.of(2020, 1, 20, 20, 20),
                LocalTime.of(2, 30));

        DataConverter converter = new DataConverter();
        String xmlString = converter.toXml(input);
        InputFlightData flightData = converter.fromXml(xmlString, InputFlightData.class);
        System.out.println(flightData);
        Assertions.assertEquals(input, flightData);

    }
}
