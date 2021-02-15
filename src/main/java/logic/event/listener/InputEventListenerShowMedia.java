/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.event.listener;

import logic.BusinessLogic;
import logic.event.InputEvent;
import logic.event.InputEventListener;
import logic.event.InputEventShowContent;

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
    public void onInputEvent(InputEvent event) {
        if (event instanceof InputEventShowContent) {
            if ((dos != null) && (dis != null)) {
                try {
                    if ((((InputEventShowContent) event).getEventMediaType() != null) && !(event.getText().equals("InputEventShowMediaTags"))) {
                        dos.writeUTF(businessLogic.getFileByType(((InputEventShowContent) event).getEventMediaType()).toString());
                    } else if (event.getText().equals("InputEventShowMediaTags")) {
                        if (((InputEventShowContent) event).getEventTagShowType() == null || (((InputEventShowContent) event).getEventTagShowType().charValue() == 'i')) {
                            dos.writeUTF(businessLogic.showAvailableTags().toString());
                        }
                        if (((InputEventShowContent) event).getEventTagShowType() == null || (((InputEventShowContent) event).getEventTagShowType().charValue() == 'e')) {
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
                if ((((InputEventShowContent) event).getEventMediaType() != null) && !(event.getText().equals("InputEventShowMediaTags"))) {
                    System.out.println(businessLogic.getFileByType(((InputEventShowContent) event).getEventMediaType()));
                } else if (event.getText().equals("InputEventShowMediaTags")) {
                    if (((InputEventShowContent) event).getEventTagShowType() == null || (((InputEventShowContent) event).getEventTagShowType().charValue() == 'i')) {
                        System.out.println(businessLogic.showAvailableTags());
                    }
                    if (((InputEventShowContent) event).getEventTagShowType() == null || (((InputEventShowContent) event).getEventTagShowType().charValue() == 'e')) {
                        System.out.println(businessLogic.showNotAvailableTags());
                    }
                } else {
                    System.out.println(businessLogic.getFiles());
                }
            }
        }
    }
}
