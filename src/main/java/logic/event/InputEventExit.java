/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.event;

public class InputEventExit extends InputEvent {
    public InputEventExit(Object source, String text) {
        super(source, text);
        //System.exit(0);
    }
}
