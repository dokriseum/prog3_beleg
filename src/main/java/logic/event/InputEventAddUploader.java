/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.event;

import models.mediaDB.Uploader;

public class InputEventAddUploader extends InputEvent {
    private Uploader eventUploader;

    public InputEventAddUploader(Object source, String text, Uploader eventCustomer) {
        super(source, text);
        this.eventUploader = eventCustomer;
    }

    public Uploader getEventUploader() {
        return eventUploader;
    }
}
