import java.io.*;
import java.net.Socket;

/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

public class Client {
    private static String command;

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 3004); DataInputStream in = new DataInputStream(socket.getInputStream()); DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            do {
                System.out.println(in.readUTF());
                String input = br.readLine();
                out.writeUTF(input);
            } while (true);

        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }
}