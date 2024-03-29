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
            boolean isNotFinished = true;
            String outputString, inputString = "";
            while (true) {

                System.out.println(in.readUTF());
                inputString = br.readLine();
                out.writeUTF(inputString);
            }
        } catch (SocketException e) {
            System.err.println("connection lost");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}