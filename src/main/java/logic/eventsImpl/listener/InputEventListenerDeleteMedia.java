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
import logic.eventsImpl.InputEventDeleteMedia;

public class InputEventListenerDeleteMedia implements InputEventListener {
    private BusinessLogic businessLogic;

    public InputEventListenerDeleteMedia(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @Override
    public void onInputEvent(InputEvent event) throws IllegalEventException {

        if (event instanceof InputEventDeleteMedia == false) {
            throw new IllegalEventException();
        }

        businessLogic.deleteContent(((InputEventDeleteMedia) event).getEventMediaAddress());
    }
}
