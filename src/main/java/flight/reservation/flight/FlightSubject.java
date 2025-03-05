package flight.reservation.flight;

import flight.reservation.FlightObserver;

interface FlightSubject {
    void addObserver(FlightObserver observer);
    void removeObserver(FlightObserver observer);
    void notifyObservers();
}
