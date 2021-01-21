package logic.network.media;

import view.ui.cli.InputOutput;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7000);) {
            try (Socket socket = serverSocket.accept();
                 DataInputStream in = new DataInputStream(socket.getInputStream());
                 DataOutputStream out = new DataOutputStream(socket.getOutputStream());) {
                System.out.println("client: " + socket.getInetAddress() + ":" + socket.getPort());
                InputOutput io = new InputOutput(in, out);
                while (true) {
                    //out.writeInt(-in.readInt());
                    //out.writeBytes(io.output());
                    //io.input(in.readUTF());
                }
            } catch (EOFException e) {
                System.out.println("client disconnect");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}