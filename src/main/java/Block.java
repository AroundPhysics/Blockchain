import java.util.ArrayList;
import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private String data; // Block data (transaction details)
    private long timeStamp;
    private int nonce;
    public ArrayList<Transaction> transactions = new ArrayList<>();

    // Constructor
    public Block(String previousHash, String data) {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    // Hash calculation
    public String calculateHash() {
        return StringUtil.applySha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);
    }

    // Mine block (Proof of Work)
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    // Add transaction to block
    public boolean addTransaction(Transaction transaction) {
        if (transaction == null) return false;
        transactions.add(transaction);
        return true;
    }
}