import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private long timeStamp;
    private int nonce;
    public ArrayList<Transaction> transactions = new ArrayList<>();

    // Constructor
    public Block(String previousHash) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    // Calculate the hash
    public String calculateHash() {
        return Transaction.applySHA256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + transactions.toString());
    }

    // Add transaction to the block
    public boolean addTransaction(Transaction transaction) {
        if (transaction == null) return false;
        if (!previousHash.equals("0")) { // Genesis block doesn't need verification
            if (!transaction.verifySignature()) {
                System.out.println("Transaction failed to verify");
                return false;
            }
        }
        transactions.add(transaction);
        return true;
    }

    // Mining process (Proof of Work)
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block mined: " + hash);
    }
}