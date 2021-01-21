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
import logic.eventsImpl.InputEventShowMedia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class InputEventListenerShowMedia implements InputEventListener {
    private BusinessLogic businessLogic;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    public InputEventListenerShowMedia(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public InputEventListenerShowMedia(BusinessLogic businessLogic, DataInputStream dis, DataOutputStream dos) {
        this.businessLogic = businessLogic;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void onInputEvent(InputEvent event) throws IllegalEventException {
        if (event instanceof InputEventShowMedia == false) {
            throw new IllegalEventException();
        }

        if ((dos != null) && (dis != null)) {
            try {
                if ((((InputEventShowMedia) event).getEventMediaType() != null) && !(event.getText().equals("InputEventShowMediaTags"))) {
                    dos.writeUTF(businessLogic.getFileByType(((InputEventShowMedia) event).getEventMediaType()).toString());
                } else if (event.getText().equals("InputEventShowMediaTags")) {
                    if (((InputEventShowMedia) event).getEventTagShowType() == null || (((InputEventShowMedia) event).getEventTagShowType().charValue() == 'i')) {
                        dos.writeUTF(businessLogic.showAvailableTags().toString());
                    }
                    if (((InputEventShowMedia) event).getEventTagShowType() == null || (((InputEventShowMedia) event).getEventTagShowType().charValue() == 'e')) {
                        dos.writeUTF(businessLogic.showNotAvailableTags().toString());
                    }
                } else {
                    dos.writeUTF(businessLogic.getFiles().toString());
                }
                dos.flush();
            } catch (IOException e) {
                System.err.println(e.getStackTrace());
            }
        } else {
            if ((((InputEventShowMedia) event).getEventMediaType() != null) && !(event.getText().equals("InputEventShowMediaTags"))) {
                System.out.println(businessLogic.getFileByType(((InputEventShowMedia) event).getEventMediaType()));
            } else if (event.getText().equals("InputEventShowMediaTags")) {
                if (((InputEventShowMedia) event).getEventTagShowType() == null || (((InputEventShowMedia) event).getEventTagShowType().charValue() == 'i')) {
                    System.out.println(businessLogic.showAvailableTags());
                }
                if (((InputEventShowMedia) event).getEventTagShowType() == null || (((InputEventShowMedia) event).getEventTagShowType().charValue() == 'e')) {
                    System.out.println(businessLogic.showNotAvailableTags());
                }
            } else {
                System.out.println(businessLogic.getFiles());
            }
        }
    }
}
