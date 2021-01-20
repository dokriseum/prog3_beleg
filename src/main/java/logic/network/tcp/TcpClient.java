package logic.network.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 7000);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());) {
            int cnt = 0;
            while (cnt < 10) {
                System.out.println("send: " + cnt);
                out.writeInt(cnt++);
                System.out.println("received: " + in.readInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}