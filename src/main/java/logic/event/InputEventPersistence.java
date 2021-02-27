/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.event;

public class InputEventPersistence extends InputEvent {
    private String eventMediaAddress;

    public InputEventPersistence(Object source, String text) {
        super(source, text);
        this.eventMediaAddress = null;
    }

    public InputEventPersistence(Object source, String text, String eventMediaAddress) {
        super(source, text);
        this.eventMediaAddress = eventMediaAddress;
    }

    public String getEventMediaAddress() {
        return eventMediaAddress;
    }
}
