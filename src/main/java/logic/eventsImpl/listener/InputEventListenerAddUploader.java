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
import logic.eventsImpl.InputEventAddUploader;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class InputEventListenerAddUploader implements InputEventListener {
    private BusinessLogic businessLogic;
    private java.io.DataInputStream dis = null;
    private DataOutputStream dos = null;

    public InputEventListenerAddUploader(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public InputEventListenerAddUploader(BusinessLogic businessLogic, DataInputStream dis, DataOutputStream dos) {
        this.businessLogic = businessLogic;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void onInputEvent(InputEvent event) throws IllegalEventException {
        if (event instanceof InputEventAddUploader == false) {
            throw new IllegalEventException();
        }
        try {
            businessLogic.createUploader(((InputEventAddUploader) event).getEventUploader());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
