/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.event;

public class InputEventDeleteMedia extends InputEvent {
    private String eventMediaAddress;

    public InputEventDeleteMedia(Object source, String text, String eventStorageLocation) {
        super(source, text);
        this.eventMediaAddress = eventStorageLocation;
    }

    public String getEventMediaAddress() {
        return eventMediaAddress;
    }
}
