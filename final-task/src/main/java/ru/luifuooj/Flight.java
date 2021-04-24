package ru.luifuooj;

import ru.luifuooj.dbprovider.DataSourceProvider;
import ru.luifuooj.model.FlightData;
import ru.luifuooj.model.IncomingFlightData;
import ru.luifuooj.model.OutboundFlightData;
import ru.luifuooj.repository.FlightRepository;
import ru.luifuooj.service.FlightService;
import ru.luifuooj.utils.file.Storage;
import ru.luifuooj.utils.xml.JaxbConverter;

import java.io.IOException;

/**
 * Основная логика задачи.
 */
public class Flight {
    JaxbConverter converter;
    FlightService service;
    Storage storage;

    public Flight() {
        converter = new JaxbConverter();
        service = new FlightService(prepareConnect());
        storage = new Storage();
    }

    public FlightRepository prepareConnect() {
        DataSourceProvider dataSourceProvider;
        try {
            dataSourceProvider = new DataSourceProvider();
            return new FlightRepository(dataSourceProvider.getDataSource());
        } catch (IOException e) {
            System.out.println("Ошибка создания провайдера: " + e.getMessage());
            return null;
        }
    }

    /**
     * Метод обработки входных данных с загрузкой результата в базу данных.
     * @param incomingDataFileName данные о входящих рейсах
     * @param outboundDataFileName данные об исходящих рейсах
     * @throws IOException
     */
    public void transferData(String incomingDataFileName, String outboundDataFileName) throws IOException {
        String xmlIncoming = storage.loadXmlData(incomingDataFileName);
        String xmlOutbound = storage.loadXmlData(outboundDataFileName);

        IncomingFlightData incomingFlightData = converter.fromXml(xmlIncoming, IncomingFlightData.class);
        OutboundFlightData outboundFlightData = converter.fromXml(xmlOutbound, OutboundFlightData.class);

        FlightData flightData = new FlightData(incomingFlightData, outboundFlightData);

        service.create(flightData);
    }

    public FlightService getService() {
        return service;
    }
}
