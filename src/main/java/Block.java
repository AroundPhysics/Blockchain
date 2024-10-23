import java.security.MessageDigest;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private long timeStamp;
    private int nonce;
    public ArrayList<Transaction> transactions = new ArrayList<>();
    public PublicKey minerAddress; // Address of the miner who mined this block
    public static final float MINER_REWARD = 10.0f; // Block reward

    // Constructor
    public Block(String previousHash, PublicKey minerAddress) {
        this.previousHash = previousHash;
        this.minerAddress = minerAddress;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    // Add transaction
    public boolean addTransaction(Transaction transaction) {
        if (transaction == null) return false;
        if (!previousHash.equals("0")) {
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
        // Reward the miner
        transactions.add(new Transaction(null, minerAddress, MINER_REWARD)); // Reward miner
        System.out.println("Block mined: " + hash);
    }

    // Calculate the hash
    public String calculateHash() {
        return Transaction.applySHA256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + transactions.toString());
    }
}