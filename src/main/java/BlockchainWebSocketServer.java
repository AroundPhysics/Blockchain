import org.java_websocket.server.WebSocketServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

public class BlockchainWebSocketServer extends WebSocketServer {
    private Set<WebSocket> peers = new HashSet<>();

    public BlockchainWebSocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        peers.add(conn);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        peers.remove(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        // Handle new block or transaction message
        Blockchain.addBlock(deserializeBlock(message));
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("WebSocket Server started.");
    }

    public void broadcast(String message) {
        for (WebSocket peer : peers) {
            peer.send(message);
        }
    }

    // Simulated deserialization
    private Block deserializeBlock(String message) {
        // Convert message to Block object
        return new Block("", "");
    }
}