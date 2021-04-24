package ru.luifuooj.model;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Структура данных после обработки.
 */
public class FlightData {
    private Integer id;
    private String flightNumber;
    private String airline;
    private String pointOfDeparture;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime travelTime;
    private String pointOfArrival;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;

    public FlightData() {
    }

    public FlightData(IncomingFlightData incomingFlightData, OutboundFlightData outboundFlightData) {
        this.flightNumber = incomingFlightData.getFlightNumber();
        this.airline = incomingFlightData.getAirline();
        this.pointOfDeparture = incomingFlightData.getPointOfDeparture();
        this.departureDate = incomingFlightData.getDepartureDateTime().toLocalDate();
        this.departureTime = incomingFlightData.getDepartureDateTime().toLocalTime();
        this.travelTime = incomingFlightData.getTravelTime();
        this.pointOfArrival = outboundFlightData.getPointOfArrival();
        this.arrivalDate = outboundFlightData.getArrivalDateTime().toLocalDate();
        this.arrivalTime = outboundFlightData.getTravelTime();
    }

    public FlightData(int id, String flightNumber, String airline,
                      String pointOfDeparture, LocalDate departureDate,
                      LocalTime departureTime, LocalTime travelTime, String pointOfArrival,
                      LocalDate arrivalDate, LocalTime arrivalTime) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.pointOfDeparture = pointOfDeparture;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.travelTime = travelTime;
        this.pointOfArrival = pointOfArrival;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;

    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getPointOfDeparture() {
        return pointOfDeparture;
    }

    public void setPointOfDeparture(String pointOfDeparture) {
        this.pointOfDeparture = pointOfDeparture;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(LocalTime travelTime) {
        this.travelTime = travelTime;
    }

    public String getPointOfArrival() {
        return pointOfArrival;
    }

    public void setPointOfArrival(String pointOfArrival) {
        this.pointOfArrival = pointOfArrival;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightData that = (FlightData) o;
        return flightNumber.equals(that.flightNumber) &&
                airline.equals(that.airline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, airline);
    }

    @Override
    public String toString() {
        return "FlightData{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", airline='" + airline + '\'' +
                ", pointOfDeparture='" + pointOfDeparture + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", travelTime=" + travelTime +
                ", pointOfArrival='" + pointOfArrival + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}

