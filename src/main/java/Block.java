import java.util.ArrayList;
import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private long timeStamp;
    private int nonce;
    public ArrayList<Transaction> transactions = new ArrayList<>();
    public String minerAddress;
    public static final float MINER_REWARD = 10.0f;

    // Constructor
    public Block(String previousHash, String minerAddress) {
        this.previousHash = previousHash;
        this.minerAddress = minerAddress;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    // Hash calculation
    public String calculateHash() {
        return StringUtil.applySha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + transactions.toString());
    }

    // Mine block (Proof of Work)
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        transactions.add(new Transaction(null, minerAddress, MINER_REWARD)); // Reward miner
    }

    // Add transaction to block
    public boolean addTransaction(Transaction transaction) {
        if (transaction == null) return false;
        transactions.add(transaction);
        return true;
    }
}