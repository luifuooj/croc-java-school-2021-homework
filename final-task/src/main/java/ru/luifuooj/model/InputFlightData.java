package ru.luifuooj.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Структура данных о рейсах.
 */
public class InputFlightData {

    /**
     * Идентификатор.
     */
    @XmlTransient
    private Integer id;

    /**
     * Тип данных.
     */
    @XmlElement(name = "FlightType")
    private FlightType flightType;

    /**
     * Номер рейса.
     */
    @XmlElement(name = "Flight")
    private String flightNumber;

    /**
     * Авиакомпания.
     */
    @XmlElement(name = "Airline")
    private String airline;

    /**
     * Место из xml .
     */
    @XmlElement(name = "Point")
    private String point;

    /**
     * Место отправления.
     */
    @XmlTransient
    private String pointOfDeparture;

    /**
     * Место прибытия.
     */
    @XmlTransient
    private String pointOfArrival;

    /**
     * Дата из xml.
     */
    @XmlTransient
    private LocalDateTime dateTime;

    /**
     * Дата из xml в строковом виде.
     */
    @XmlElement(name = "DateTime")
    private String dateTimeString;

    /**
     * Дата отправления.
     */
    @XmlTransient
    private LocalDateTime departureDateTime;

    /**
     * Дата отправления в строковом виде.
     */
    @XmlTransient
    private String departureDateTimeString;

    /**
     * Дата прибытия.
     */
    @XmlTransient
    private LocalDateTime arrivalDateTime;

    /**
     * Дата прибытия в строковом виде.
     */
    @XmlTransient
    private String arrivalDateTimeString;

    /**
     * Время в пути.
     */
    @XmlTransient
    private LocalTime travelTime;

    /**
     * Время в пути в строковом виде.
     */
    @XmlElement(name = "Duration")
    private String travelTimeString;

    /**
     * Форматтер даты и времени.
     */
    @XmlTransient
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Форматтер даты.
     */
    @XmlTransient
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Форматтер времени.
     */
    @XmlTransient
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public InputFlightData() {
    }

    public InputFlightData(FlightType flightType, String flightNumber, String airline, String point, LocalDateTime dateTime, LocalTime travelTime) {
        this.flightType = flightType;
        this.flightNumber = flightNumber;
        this.airline = airline;
        setPoint(point);
        setDateTime(dateTime);
        this.travelTime = travelTime;
        this.travelTimeString = timeFormatter.format(travelTime);
    }

    public InputFlightData(Integer id, String flightNumber, String airline, String pointOfDeparture,
                           LocalDate departureDate, LocalTime departureTime, LocalTime travelTime, String pointOfArrival,
                           LocalDate arrivalDate, LocalTime arrivalTime) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.pointOfDeparture = pointOfDeparture;
        this.departureDateTime = LocalDateTime.of(departureDate, departureTime);
        this.travelTime = travelTime;
        this.pointOfArrival = pointOfArrival;
        this.arrivalDateTime = LocalDateTime.of(arrivalDate, arrivalTime);
    }

    public boolean isSameFlight(InputFlightData otherData) {
        return this.airline.equals(otherData.airline) && this.flightNumber.equals(otherData.flightNumber);
    }

    public Integer getId() {
        return id;
    }

    public FlightType getFlightType() {
        return flightType;
    }

    private void setFlightType(FlightType flightType) {
        this.flightType = flightType;
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

    public String getPoint() {
        if (flightType == null) {
            if (this.pointOfDeparture == null) {
                return this.pointOfArrival;
            } else {
                return this.pointOfDeparture;
            }
        } else if (flightType.equals(FlightType.INCOMING)) {
            return this.pointOfDeparture;
        } else {
            return this.pointOfArrival;
        }
    }

    public void setPoint(String point) {
        if (flightType.equals(FlightType.INCOMING)) {
            this.pointOfDeparture = point;
        } else {
            this.pointOfArrival = point;
        }
        this.point = point;
    }

    public String getPointOfDeparture() {
        return pointOfDeparture;
    }

    public void setPointOfDeparture(String pointOfDeparture) {
        this.pointOfDeparture = pointOfDeparture;
        this.flightType = FlightType.INCOMING;
    }

    public String getPointOfArrival() {
        return pointOfArrival;
    }

    public void setPointOfArrival(String pointOfArrival) {
        this.pointOfArrival = pointOfArrival;
        this.flightType = FlightType.OUTBOUND;
    }

    public LocalDateTime getDateTime() {
        if (flightType.equals(FlightType.INCOMING)) {
            return this.departureDateTime;
        } else {
            return this.arrivalDateTime;
        }
    }

    public void setDateTime(LocalDateTime dateTime) {
        if (flightType.equals(FlightType.INCOMING)) {
            this.departureDateTime = dateTime;
            this.departureDateTimeString = dateTimeFormatter.format(dateTime);
        } else {
            this.arrivalDateTime = dateTime;
            this.arrivalDateTimeString = dateTimeFormatter.format(dateTime);
        }
        this.dateTime = dateTime;
        this.dateTimeString = dateTimeFormatter.format(dateTime);
    }

    public String getDateTimeString() {
        if (flightType.equals(FlightType.INCOMING)) {
            return this.departureDateTimeString;
        } else {
            return this.arrivalDateTimeString;
        }
    }

    public void setDateTimeString(String dateTimeString) {
        if (flightType.equals(FlightType.INCOMING)) {
            this.departureDateTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter);
            this.departureDateTimeString = dateTimeString;
        } else {
            this.arrivalDateTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter);
            this.arrivalDateTimeString = dateTimeString;
        }
        this.dateTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        this.dateTimeString = dateTimeString;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTimeString = dateTimeFormatter.format(departureDateTime);
        this.departureDateTime = departureDateTime;
        this.flightType = FlightType.INCOMING;
    }

    public String getDepartureDateTimeString() {
        return departureDateTimeString;
    }

    public void setDepartureDateTimeString(String departureDateTimeString) {
        this.departureDateTime = LocalDateTime.parse(departureDateTimeString, dateTimeFormatter);
        this.departureDateTimeString = departureDateTimeString;
        this.flightType = FlightType.INCOMING;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTimeString = dateTimeFormatter.format(arrivalDateTime);
        this.arrivalDateTime = arrivalDateTime;
        this.flightType = FlightType.OUTBOUND;
    }

    public String getArrivalDateTimeString() {
        return arrivalDateTimeString;
    }

    public void setArrivalDateTimeString(String arrivalDateTimeString) {
        this.arrivalDateTime = LocalDateTime.parse(arrivalDateTimeString, dateTimeFormatter);
        this.arrivalDateTimeString = arrivalDateTimeString;
        this.flightType = FlightType.OUTBOUND;
    }

    public String getTravelTimeString() {
        return travelTimeString;
    }

    public void setTravelTimeString(String travelTimeString) {
        this.travelTime = LocalTime.parse(travelTimeString, timeFormatter);
        this.travelTimeString = travelTimeString;
    }

    public LocalTime getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(LocalTime travelTime) {
        this.travelTimeString = timeFormatter.format(travelTime);
        this.travelTime = travelTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputFlightData that = (InputFlightData) o;
        return flightNumber.equals(that.flightNumber) &&
                airline.equals(that.airline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, airline);
    }

    @Override
    public String toString() {
        return "InputFlightData{" +
                "id=" + id +
                ", flightType=" + flightType +
                ", flightNumber='" + flightNumber + '\'' +
                ", airline='" + airline + '\'' +
                ", point='" + point + '\'' +
                ", dateTime=" + dateTime +
                ", travelTime=" + travelTime +
                '}';
    }
}
