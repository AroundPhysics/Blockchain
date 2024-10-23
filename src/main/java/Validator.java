import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Random;

public class Validator {
    public PublicKey publicKey;
    public float stake;

    // Constructor
    public Validator(PublicKey publicKey, float stake) {
        this.publicKey = publicKey;
        this.stake = stake;
    }

    // Select a validator based on their stake
    public static Validator selectValidator(ArrayList<Validator> validators) {
        float totalStake = 0;
        for (Validator v : validators) {
            totalStake += v.stake;
        }

        Random rand = new Random();
        float randomValue = rand.nextFloat() * totalStake; // Random value in the range of total stakes

        float cumulativeStake = 0;
        for (Validator v : validators) {
            cumulativeStake += v.stake;
            if (cumulativeStake >= randomValue) {
                return v; // This validator gets selected
            }
        }
        return null;
    }
}
