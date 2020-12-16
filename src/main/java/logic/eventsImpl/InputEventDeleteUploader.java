/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.eventsImpl;

import logic.events.InputEvent;

public class InputEventDeleteUploader extends InputEvent {
    private String eventUploaderName;

    public InputEventDeleteUploader(Object source, String text, String eventUploaderName) {
        super(source, text);
        this.eventUploaderName = eventUploaderName;
    }

    public String getEventUploaderName() {
        return eventUploaderName;
    }
}
