package flight.reservation.payment;

public class PaypalAdapter implements PaymentGateway {
    private final PaypalInstance paypalInstance;

    public PaypalAdapter(PaypalInstance paypal) {
        this.paypalInstance = paypal;
    }

    @Override
    public boolean pay(double amount) {
        // Process payment with Paypal
        System.out.println("Paying " + amount + " using Paypal.");
        return true;
    }
    
}
