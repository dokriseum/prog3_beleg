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
import logic.eventsImpl.InputEventShowUploader;

public class InputEventListenerShowUploader implements InputEventListener {
    private BusinessLogic businessLogic;

    public InputEventListenerShowUploader(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @Override
    public void onInputEvent(InputEvent event) throws IllegalEventException {

        if (event instanceof InputEventShowUploader == false) {
            throw new IllegalEventException();
        }
        System.out.println(businessLogic.getUploadersWithContentAmount());
    }
}
