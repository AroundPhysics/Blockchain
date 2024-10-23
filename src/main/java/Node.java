import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Node {
    private int port;
    private ServerSocket serverSocket;
    private ArrayList<Socket> peers = new ArrayList<>();

    // Constructor
    public Node(int port) {
        this.port = port;
        startServer();
    }

    // Start the server to listen for incoming connections
    public void startServer() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                System.out.println("Node started at port: " + port);
                while (true) {
                    Socket peer = serverSocket.accept();
                    peers.add(peer);
                    handlePeer(peer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Handle incoming connections and messages from peers
    public void handlePeer(Socket peer) {
        new Thread(() -> {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(peer.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("Received from peer: " + line);
                    // Here, you can process new blocks or transactions from peers
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Connect to another peer
    public void connectToPeer(String host, int port) {
        try {
            Socket peer = new Socket(host, port);
            peers.add(peer);
            System.out.println("Connected to peer: " + host + ":" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Send a message to all connected peers
    public void broadcastMessage(String message) {
        for (Socket peer : peers) {
            try (PrintWriter out = new PrintWriter(peer.getOutputStream(), true)) {
                out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
