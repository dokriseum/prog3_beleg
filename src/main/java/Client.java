import java.io.*;
import java.net.Socket;

/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 9000);
             InputStream in = new DataInputStream(socket.getInputStream());
             OutputStream out = new DataOutputStream(socket.getOutputStream())) {
            InputStreamReader isr = new InputStreamReader(System.in);
            OutputStreamWriter osw = new OutputStreamWriter(System.out);
            InputStreamReader isrS = new InputStreamReader(in);
            OutputStreamWriter oswS = new OutputStreamWriter(out);
            BufferedReader br = new BufferedReader(isr);
            BufferedReader brS = new BufferedReader(isrS);
            BufferedWriter bw = new BufferedWriter(osw);
            BufferedWriter bwS = new BufferedWriter(oswS);

            
            //DataOutputStream dos = new DataOutputStream(out);
            //System.out.println(dos.toString());
            //DataInputStream dis = new DataInputStream(in);
            //dis.readUTF();


            //PrintWriter pw = new PrintWriter(out);
            //pw.write("done");
            //pw.flush();
            //bw.write(in.read());
            //String input = br.readLine();
            //pw.write(input);
            //bw.write(out.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        try (Socket socket = new Socket("localhost", 9000);
             InputStreamReader in = new InputStreamReader(socket.getInputStream());
             OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream())) {
            out.write('I');
            out.write(7);
            out.write(1);
            in.read();
            String r = in.toString();
            while ("L" == r) {
                System.out.println(in.read());
                out.write('S');
                in.read();
                r = in.toString();
            }
            switch (r) {
                case "E":
                    System.out.println("<end>");
                    break;
                case "F":
                    System.out.println("error: " + in.read());
                    break;
                default:
                    System.out.println("unknown response");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }
}
