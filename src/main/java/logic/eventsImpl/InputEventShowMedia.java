/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.eventsImpl;

import logic.events.InputEvent;
import models.storage.MediaType;

public class InputEventShowMedia extends InputEvent {
    private MediaType eventMediaType;
    private Character eventTagShowType;

    public InputEventShowMedia(Object source, String text) {
        super(source, text);
        this.eventMediaType = null;
        this.eventTagShowType = null;
    }

    public InputEventShowMedia(Object source, String text, MediaType mediaType) {
        super(source, text);
        this.eventMediaType = mediaType;
        this.eventTagShowType = null;
    }

    public InputEventShowMedia(Object source, String text, Character tagShowType) {
        super(source, text);
        this.eventMediaType = null;
        this.eventTagShowType = tagShowType;
    }

    public MediaType getEventMediaType() {
        return eventMediaType;
    }

    public Character getEventTagShowType() {
        return eventTagShowType;
    }
}
