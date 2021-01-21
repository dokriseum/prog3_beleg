import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

public class Client {
    private static String input = new String();
    private static String output = new String();

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 3004); DataInputStream in = new DataInputStream(socket.getInputStream()); DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            do {
                output = in.readUTF();
                System.out.println(output);
                if ((input.startsWith("content"))
                        || (input.startsWith("uploader"))
                        || (input.startsWith("tag"))
                ) {
                    if (input.equals("tag")) {
                        System.out.println(in.readUTF());
                    }
                    System.out.println(in.readUTF());
                }

                input = br.readLine();

                out.writeUTF(input);
            }
            while (true);

        } catch (SocketException e) {
            System.err.println("connection lost");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}