/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.eventsImpl.listener;

import logic.BusinessLogic;
import logic.events.InputEvent;
import logic.events.InputEventListener;

public class InputEventListenerOutput implements InputEventListener {
    private BusinessLogic businessLogic;

    public InputEventListenerOutput(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null) {
            System.out.println("input=" + event.getText());
        }
    }
}
