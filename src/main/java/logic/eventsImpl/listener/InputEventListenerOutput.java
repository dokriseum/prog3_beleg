/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.eventsImpl.listener;

import logic.BusinessLogic;
import logic.events.InputEvent;
import logic.events.InputEventListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class InputEventListenerOutput implements InputEventListener {
    private BusinessLogic businessLogic;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    public InputEventListenerOutput(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public InputEventListenerOutput(BusinessLogic businessLogic, DataInputStream dis, DataOutputStream dos) {
        this.businessLogic = businessLogic;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null) {
            System.out.println("input=" + event.getText());
        }
    }
}
