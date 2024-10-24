import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Random;

public class Validator {
    public PublicKey publicKey;
    public float stake;
    public float slashedStake = 0;
    public static final float SLASHING_PENALTY = 10.0f;
    private static ArrayList<Validator> validators = new ArrayList<>();

    public Validator(PublicKey publicKey, float stake) {
        this.publicKey = publicKey;
        this.stake = stake;
    }

    // Add validator to pool
    public static void addValidator(Validator validator) {
        validators.add(validator);
    }

    // Penalize validator for bad behavior
    public static void penalizeValidator(Validator validator, float penalty) {
        validator.slash(penalty);
    }

    // Slash a validator
    public void slash(float penalty) {
        stake -= penalty;
        slashedStake += penalty;
    }

    // Validator voting on block validity
    public boolean vote(Block block) {
        // Simplified: In a real system, validators check block's validity
        return new Random().nextBoolean();
    }

    // Get validator by public key
    public static Validator getValidator(String publicKey) {
        for (Validator v : validators) {
            if (v.publicKey.toString().equals(publicKey)) return v;
        }
        return null;
    }
}