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
import logic.utils.OutputSaver;

import java.io.DataInputStream;
import java.io.DataOutputStream;

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
                StringBuffer outputTemp = new StringBuffer();
                if (
                        (((InputEventShowContent) event).getEventMediaType() != null)
                                && !(event.getText().equals("InputEventShowMediaTags"))) {

                    outputTemp.append(businessLogic.getFileByType(((InputEventShowContent) event).getEventMediaType()).toString());

                } else if (event.getText().equals("InputEventShowMediaTags")) {
                    if (((InputEventShowContent) event).getEventTagShowType() == null || (((InputEventShowContent) event).getEventTagShowType().charValue() == 'i')) {
                        outputTemp.append(businessLogic.showAvailableTags().toString());
                    }
                    if (((InputEventShowContent) event).getEventTagShowType() == null || (((InputEventShowContent) event).getEventTagShowType().charValue() == 'e')) {
                        outputTemp.append(businessLogic.showNotAvailableTags().toString());
                    }
                } else {
                    outputTemp.append(businessLogic.getFiles().toString());
                }
//                    outputTemp.append(((Input) event.getSource()).outputTextForShow());
//                    outputTemp.append("\n");
//                    outputTemp.append(((Input) event.getSource()).outputTextForRequestInput());
                //outputTemp += (((Input) event.getSource()).outputTextForShow() + "\n" + ((Input) event.getSource()).outputTextForRequestInput());
                //dos.writeUTF(outputTemp.toString());
                //dos.writeUTF(((Input) event.getSource()).outputTextForShow() + ((Input) event.getSource()).outputTextForRequestInput());
                //dos.flush();
                OutputSaver.setOutput(outputTemp.toString());
                OutputSaver.setIsShowEvent(true);
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
