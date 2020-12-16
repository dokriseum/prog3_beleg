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
import logic.eventsImpl.InputEventSwitchChoice;
import view.ui.cli.Input;

public class InputEventListenerSwitchChoice implements InputEventListener {
    private BusinessLogic businessLogic;

    public InputEventListenerSwitchChoice(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @Override
    public void onInputEvent(InputEvent event) throws IllegalEventException {
        if (event instanceof InputEventSwitchChoice == false) {
            throw new IllegalEventException();
        }
        ((Input) event.getSource()).setInputChoice(((InputEventSwitchChoice) event).getEventChoice());
    }
}
