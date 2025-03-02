package flight.reservation.payment;

public class CreditCardAdapter implements PaymentGateway {
    private final CreditCard creditCard;

    public CreditCardAdapter(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public boolean pay(double amount) {
        // Process payment with CreditCard
        System.out.println("Paying " + amount + " using Credit Card.");
        double remainingAmount = this.creditCard.getAmount() - amount;
        if (remainingAmount < 0) {
            System.out.printf("Card limit reached - Balance: %f%n", remainingAmount);
            throw new IllegalStateException("Card limit reached");
        }
        this.creditCard.setAmount(remainingAmount);
        return true;
    }
}
