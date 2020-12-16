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
import logic.eventsImpl.InputEventExit;

public class InputEventListenerExit implements InputEventListener {
    private BusinessLogic businessLogic;

    public InputEventListenerExit(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @Override
    public void onInputEvent(InputEvent event) throws IllegalEventException {
        if (event instanceof InputEventExit == false) {
            throw new IllegalEventException();
        } else {
            System.exit(0);
        }
        /**
         if (null != event.getText() && event.getText().equals("exit")){
         System.exit(0);
         }
         */
    }
}
