import java.util.ArrayList;

public class Blockchain {
    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;
    public static ArrayList<Validator> validators = new ArrayList<>(); // Pool of validators

    // Add validators (usually based on their wallet holdings)
    public static void addValidator(Validator validator) {
        validators.add(validator);
    }

    // Main function
    public static void main(String[] args) {
        // Initialize wallets
        Wallet walletA = new Wallet();
        Wallet walletB = new Wallet();

        // Add genesis block
        Block genesisBlock = new Block("0", walletA.publicKey);
        genesisBlock.mineBlock(difficulty);
        blockchain.add(genesisBlock);

        // Add validators (wallets acting as validators)
        addValidator(new Validator(walletA.publicKey, 50));
        addValidator(new Validator(walletB.publicKey, 30));

        // Select validator for the next block
        Validator selectedValidator = Validator.selectValidator(validators);
        System.out.println("Selected validator: " + selectedValidator.publicKey.toString());

        // Validator creates and mines block
        Block block1 = new Block(genesisBlock.hash, selectedValidator.publicKey);
        block1.addTransaction(walletA.sendFunds(walletB.publicKey, 10));
        blockchain.add(block1);
    }
}