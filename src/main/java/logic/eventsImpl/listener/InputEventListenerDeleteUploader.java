/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.eventsImpl.listener;

import exceptions.IllegalEventException;
import logic.BusinessLogic;
import logic.events.InputEvent;
import logic.events.InputEventListener;
import logic.eventsImpl.InputEventDeleteUploader;

public class InputEventListenerDeleteUploader implements InputEventListener {
    private BusinessLogic businessLogic;

    public InputEventListenerDeleteUploader(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @Override
    public void onInputEvent(InputEvent event) throws IllegalEventException {

        if (event instanceof InputEventDeleteUploader == false) {
            throw new IllegalEventException();
        }
        businessLogic.deleteUploader(((InputEventDeleteUploader) event).getEventUploaderName());
    }
}
