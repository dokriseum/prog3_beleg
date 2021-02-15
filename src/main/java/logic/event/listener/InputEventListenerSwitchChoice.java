/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.event.listener;

import logic.BusinessLogic;
import logic.event.InputEvent;
import logic.event.InputEventListener;
import logic.event.InputEventSwitchChoice;
import view.cli.Input;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class InputEventListenerSwitchChoice implements InputEventListener {
    private BusinessLogic businessLogic;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    public InputEventListenerSwitchChoice(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public InputEventListenerSwitchChoice(BusinessLogic businessLogic, DataInputStream dis, DataOutputStream dos) {
        this.businessLogic = businessLogic;
        this.businessLogic = businessLogic;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event instanceof InputEventSwitchChoice) {
            ((Input) event.getSource()).setInputChoice(((InputEventSwitchChoice) event).getEventChoice());
        }
    }
}
