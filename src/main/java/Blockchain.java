import java.util.ArrayList;

public class Blockchain {
    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;
    public static ArrayList<Validator> validators = new ArrayList<>();

    // Add validators
    public static void addValidator(Validator validator) {
        validators.add(validator);
    }

    // Add block to blockchain
    public static boolean addBlock(Block newBlock) {
        if (validateBlock(newBlock)) {
            blockchain.add(newBlock);
            return true;
        } else {
            Validator.penalizeValidator(Validator.getValidator(newBlock.minerAddress), Validator.SLASHING_PENALTY);
            return false;
        }
    }

    // Block validation by validators
    public static boolean validateBlock(Block block) {
        int validVotes = 0;
        int invalidVotes = 0;
        for (Validator v : validators) {
            boolean vote = v.vote(block);
            if (vote) {
                validVotes++;
            } else {
                invalidVotes++;
            }
        }
        return validVotes > invalidVotes;
    }

    // Check blockchain integrity
    public static boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) return false;
            if (!currentBlock.previousHash.equals(previousBlock.hash)) return false;
        }
        return true;
    }
}