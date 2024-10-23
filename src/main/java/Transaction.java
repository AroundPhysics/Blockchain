import java.security.*;

public class Transaction {
    public String transactionId; // hash of the transaction
    public PublicKey sender; // sender's public key
    public PublicKey recipient; // recipient's public key
    public float value; // amount of currency
    private byte[] signature; // cryptographic signature

    private static int sequence = 0; // to keep track of the transaction count

    // Constructor
    public Transaction(PublicKey from, PublicKey to, float value) {
        this.sender = from;
        this.recipient = to;
        this.value = value;
        this.transactionId = calculateHash();
    }

    // Generate the transaction's hash
    public String calculateHash() {
        sequence++;
        return applySHA256(getStringFromKey(sender) + getStringFromKey(recipient) + Float.toString(value) + sequence);
    }

    // Sign the transaction
    public void generateSignature(PrivateKey privateKey) {
        String data = getStringFromKey(sender) + getStringFromKey(recipient) + Float.toString(value);
        signature = applyECDSASig(privateKey, data);
    }

    // Verify the transaction's signature
    public boolean verifySignature() {
        String data = getStringFromKey(sender) + getStringFromKey(recipient) + Float.toString(value);
        return verifyECDSASig(sender, data, signature);
    }

    // Utility functions for hashing, signing, and verifying
    public static String applySHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] applyECDSASig(PrivateKey privateKey, String input) {
        Signature dsa;
        byte[] output = new byte[0];
        try {
            dsa = Signature.getInstance("ECDSA", "BC");
            dsa.initSign(privateKey);
            dsa.update(input.getBytes());
            byte[] realSig = dsa.sign();
            output = realSig;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) {
        try {
            Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(data.getBytes());
            return ecdsaVerify.verify(signature);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getStringFromKey(Key key) {
        if (key == null) {
            return null;
        }
        return java.util.Base64.getEncoder().encodeToString(key.getEncoded());
    }
}