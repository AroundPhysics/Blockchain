import java.util.ArrayList;

public class Blockchain {
    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;

    // Main function
    public static void main(String[] args) {
        // Initialize wallets
        Wallet walletA = new Wallet();
        Wallet walletB = new Wallet();

        // Add genesis block
        Block genesisBlock = new Block("0");
        genesisBlock.mineBlock(difficulty);
        blockchain.add(genesisBlock);

        // Create transactions
        Block block1 = new Block(genesisBlock.hash);
        block1.addTransaction(walletA.sendFunds(walletB.publicKey, 10));
        block1.mineBlock(difficulty);
        blockchain.add(block1);

        Block block2 = new Block(block1.hash);
        block2.addTransaction(walletB.sendFunds(walletA.publicKey, 5));
        block2.mineBlock(difficulty);
        blockchain.add(block2);

        System.out.println("Blockchain is valid: " + isChainValid());
    }

    // Validate blockchain
    public static boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            // Check if current block's hash is valid
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Invalid block hash");
                return false;
            }

            // Check if the previous block's hash is correct
            if (!currentBlock.previousHash.equals(previousBlock.hash)) {
                System.out.println("Invalid previous block hash");
                return false;
            }

            // Check if the block is mined
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}