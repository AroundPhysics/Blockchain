public class Transaction {
    public String sender;
    public String recipient;
    public float amount;

    // Constructor
    public Transaction(String sender, String recipient, float amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return sender + " -> " + recipient + " : " + amount;
    }
}