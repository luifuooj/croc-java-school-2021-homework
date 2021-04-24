package ru.luifuooj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FlightTest {

    Flight flight = new Flight();

    String fileNameIncomingFlightData = "incomingFlightData.xml";
    String fileNameOutboundFlightData = "outboundFlightData.xml";

    @Test
    public void flightTest() throws IOException {
        flight.getService().deleteAll();
        Assertions.assertTrue(flight.getService().getAll().isEmpty());

        flight.transferData(fileNameIncomingFlightData, fileNameOutboundFlightData);

        Assertions.assertFalse(flight.getService().getAll().isEmpty());
    }

}
