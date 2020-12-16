/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.eventsImpl;

import logic.events.InputEvent;
import models.mediaDB.Uploader;

public class InputEventShowUploader extends InputEvent {
    private Uploader eventUploader;

    public InputEventShowUploader(Object source, String text) {
        super(source, text);
    }

    public Uploader getEventUploader() {
        return eventUploader;
    }
}
