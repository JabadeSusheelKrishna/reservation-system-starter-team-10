package flight.reservation.payment;

public class PaypalInstance {
    private String email;
    private String password;
    public PaypalInstance(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getemail() {
        return email;
    }

    public String getpassword() {
        return password;
    }
}
