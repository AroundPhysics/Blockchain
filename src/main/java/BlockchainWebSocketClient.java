import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;

public class BlockchainWebSocketClient extends WebSocketClient {

    public BlockchainWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected to peer.");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Message from peer: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected from peer.");
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }
}