package ru.luifuooj.xmlUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import ru.luifuooj.model.IncomingFlightData;
import ru.luifuooj.model.OutboundFlightData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class JaxbConverterTest {

    @Test
    public void testConvert() throws JsonProcessingException {

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
}
