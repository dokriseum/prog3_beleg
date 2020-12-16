/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package view.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class Console {
    private String testInputString = null;
    private StringReader sr;
    private BufferedReader br;

    public Console() {

    }

    public Console(String testInputString) {
        this.testInputString = testInputString;
        sr = new StringReader(testInputString);
        br = new BufferedReader(sr);

    }

    public int readIntegerFromStdin(String text) throws NumberFormatException, IOException {
        int integer = 0;
        if (br != null) {
            System.out.print(text);
            integer = Integer.parseInt(this.br.readLine());
        } else {
            System.out.print(text);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            integer = Integer.parseInt(bufferedReader.readLine());
        }
        return integer;
    }

    public String readStringFromStdin(String text) throws NumberFormatException, IOException {

        String string = null;
        if (br != null) {
            System.out.print(text);
            string = this.br.readLine();
        } else {
            System.out.print(text);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            string = bufferedReader.readLine();
        }
        return string;
    }
}
