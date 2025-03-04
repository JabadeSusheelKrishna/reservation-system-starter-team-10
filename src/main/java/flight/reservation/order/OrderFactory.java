package flight.reservation.order;

import flight.reservation.Customer;
import flight.reservation.flight.ScheduledFlight;

import java.util.List;

public interface OrderFactory {
    Order createOrder(Customer customer, List<String> passengerNames, List<ScheduledFlight> flights, double price);
}
