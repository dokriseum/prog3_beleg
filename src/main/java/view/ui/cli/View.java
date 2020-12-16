/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package view.ui.cli;

import java.io.OutputStream;
import java.io.PrintStream;

public class View {
    private PrintStream printStream;
    private OutputStream outputStream;

    public View(PrintStream printStream, OutputStream outputStream) {
        this.printStream = printStream;
        this.outputStream = outputStream;
    }

    public View() {
        outputStream = System.out;
        printStream = new PrintStream(outputStream);

    }

    public PrintStream getPrintStream() {
        return printStream;
    }

    public void output(String message) {
        printStream.append(message);
    }
}
