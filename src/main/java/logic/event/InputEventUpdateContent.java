/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.event;

public class InputEventUpdateContent extends InputEvent {
    private String eventContent;

    public InputEventUpdateContent(Object source, String text, String eventContent) {
        super(source, text);
        this.eventContent = eventContent;
    }

    public String getEventContent() {
        return eventContent;
    }
}
