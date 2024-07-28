interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using credit card");
    }
}

class PayPalStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}

class PaymentGateway {
    private PaymentStrategy paymentStrategy;

    public PaymentGateway(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void pay(int amount) {
        paymentStrategy.pay(amount);
    }
}

public class Strategy_Pattern{
    public static void main(String[] args) {
        PaymentGateway paymentGateway = new PaymentGateway(new CreditCardStrategy());
        paymentGateway.pay(100);
    }
}