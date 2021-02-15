/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.event;

import java.util.EventObject;

public class InputEvent extends EventObject {
    private String text;

    public InputEvent(Object source, String text) {
        super(source);
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
