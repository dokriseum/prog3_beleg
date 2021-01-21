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

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class InputEventListenerExit implements InputEventListener {
    private BusinessLogic businessLogic;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    public InputEventListenerExit(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public InputEventListenerExit(BusinessLogic businessLogic, DataInputStream dis, DataOutputStream dos) {
        this.businessLogic = businessLogic;
        this.dis = dis;
        this.dos = dos;
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
