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
        System.out.println("New connection from: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        peers.remove(conn);
        System.out.println("Connection closed: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Message from peer: " + message);
        // Process new blocks or transactions
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("WebSocket server started.");
    }

    // Broadcast a message to all peers
    public void broadcast(String message) {
        for (WebSocket peer : peers) {
            peer.send(message);
        }
    }
}