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
import logic.eventsImpl.InputEventDeleteUploader;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class InputEventListenerDeleteUploader implements InputEventListener {
    private BusinessLogic businessLogic;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    public InputEventListenerDeleteUploader(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public InputEventListenerDeleteUploader(BusinessLogic businessLogic, DataInputStream dis, DataOutputStream dos) {
        this.businessLogic = businessLogic;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void onInputEvent(InputEvent event) throws IllegalEventException {

        if (event instanceof InputEventDeleteUploader == false) {
            throw new IllegalEventException();
        }
        businessLogic.deleteUploader(((InputEventDeleteUploader) event).getEventUploaderName());
    }
}
