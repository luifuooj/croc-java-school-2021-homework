package ru.luifuooj.utils.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.luifuooj.model.IncomingFlightData;
import ru.luifuooj.model.OutboundFlightData;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Тестирование xml-конвертера.
 */
public class JaxbConverterTest {

    @Test
    public void toXmlTest() throws JsonProcessingException {

        final IncomingFlightData input = new IncomingFlightData(
                "9332",
                "YC",
                "Jamal",
                LocalDateTime.of(2020,01,20,20,20),
                LocalTime.of(2, 30));

        JaxbConverter converter = new JaxbConverter();
        String xml = converter.toXml(input);
        System.out.println(xml);

        final OutboundFlightData output = new OutboundFlightData(
                "1234",
                "LN",
                "Yamal",
                LocalDateTime.of(2020,10,10,22,22),
                LocalTime.of(3,50));

        JaxbConverter jaxbConverter = new JaxbConverter();
        String toXml = converter.toXml(output);
        System.out.println(toXml);
    }
    @Test
    public void fromXmlTest() throws IOException {
        final IncomingFlightData input = new IncomingFlightData(
                "9332",
                "YC",
                "Jamal",
                LocalDateTime.of(2020,1,20,20,20),
                LocalTime.of(2, 30));

        JaxbConverter converter = new JaxbConverter();
        String xmlString = converter.toXml(input);
        IncomingFlightData flightData = converter.fromXml(xmlString, IncomingFlightData.class);
        System.out.println(flightData);
        Assertions.assertEquals(input, flightData);

    }
}
