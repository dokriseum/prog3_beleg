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
import logic.eventsImpl.InputEventShowUploader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class InputEventListenerShowUploader implements InputEventListener {
    private BusinessLogic businessLogic;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    public InputEventListenerShowUploader(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public InputEventListenerShowUploader(BusinessLogic businessLogic, DataInputStream dis, DataOutputStream dos) {
        this.businessLogic = businessLogic;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void onInputEvent(InputEvent event) throws IllegalEventException {
        if (event instanceof InputEventShowUploader == false) {
            throw new IllegalEventException();
        }
        if ((dos != null) && (dis != null)) {
            try {
                dos.writeUTF(businessLogic.getUploadersWithContentAmount().toString());
                dos.flush();
            } catch (IOException e) {
                System.err.println(e.getStackTrace());
            }
        } else {
            System.out.println(businessLogic.getUploadersWithContentAmount());
        }
    }
}
