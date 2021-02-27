/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.event.listener;

import logic.BusinessLogic;
import logic.event.InputEvent;
import logic.event.InputEventListener;
import logic.event.InputEventPersistence;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class InputEventListenerPersistence implements InputEventListener {
    private BusinessLogic businessLogic;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    public InputEventListenerPersistence(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public InputEventListenerPersistence(BusinessLogic businessLogic, DataInputStream dis, DataOutputStream dos) {
        this.businessLogic = businessLogic;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event instanceof InputEventPersistence) {

        }
    }
}
