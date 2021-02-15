/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.event;

import models.storage.MediaType;

public class InputEventShowContent extends InputEvent {
    private MediaType eventMediaType;
    private Character eventTagShowType;

    public InputEventShowContent(Object source, String text) {
        super(source, text);
        this.eventMediaType = null;
        this.eventTagShowType = null;
    }

    public InputEventShowContent(Object source, String text, MediaType mediaType) {
        super(source, text);
        this.eventMediaType = mediaType;
        this.eventTagShowType = null;
    }

    public InputEventShowContent(Object source, String text, Character tagShowType) {
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
