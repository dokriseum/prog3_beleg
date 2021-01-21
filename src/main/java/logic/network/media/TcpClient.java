package logic.network.media;

import java.io.*;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 9000);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            bw.write(in.readUTF());
            out.writeBytes(br.readLine());
            /*
            int cnt = 0;
            while (cnt < 10) {
                System.out.println("send: " + cnt);
                out.writeInt(cnt++);
                System.out.println("received: " + in.readInt());
            }
             */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}