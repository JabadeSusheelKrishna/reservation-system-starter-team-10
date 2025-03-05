package flight.reservation;

public interface FlightObserver {
    void update(String flightNumber, String status);
}
