package flight.reservation.payment;

public interface PaymentGateway {
    public boolean pay(double amount);
}
