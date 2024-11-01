import java.net.InetSocketAddress;
import java.net.URI;

public class Main {
    public static void main(String[] args) {
        // Start WebSocket server
        BlockchainWebSocketServer server = new BlockchainWebSocketServer(new InetSocketAddress(8887));
        server.start();
        System.out.println("Blockchain WebSocket server started at port 8887.");

        // Connect client to server
        try {
            BlockchainWebSocketClient client = new BlockchainWebSocketClient(new URI("ws://localhost:8887"));
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize blockchain
        Blockchain.blockchain.add(new Block("0", "Genesis Block"));

        System.out.println("Mining block 1...");
        Blockchain.addBlock(new Block(Blockchain.blockchain.get(Blockchain.blockchain.size() - 1).hash, "Block 1 Data"));

        System.out.println("Mining block 2...");
        Blockchain.addBlock(new Block(Blockchain.blockchain.get(Blockchain.blockchain.size() - 1).hash, "Block 2 Data"));

        System.out.println("Blockchain valid: " + Blockchain.isChainValid());
    }
}