import java.util.ArrayList;

public class Blockchain {
    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 4;

    // Add new block to the blockchain
    public static void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }

    // Check the validity of the blockchain
    public static boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            if (!currentBlock.previousHash.equals(previousBlock.hash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
}