import logic.BusinessLogic;
import logic.network.media.MediaServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9000);) {
            while (true) {
                Socket socket = serverSocket.accept();
                MediaServer s = new MediaServer(socket, new BusinessLogic());
                System.out.println("new client@" + socket.getInetAddress() + ":" + socket.getPort());
                new Thread(s).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
