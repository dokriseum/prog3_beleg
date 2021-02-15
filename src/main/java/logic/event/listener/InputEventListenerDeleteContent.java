/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.event.listener;

import logic.BusinessLogic;
import logic.event.InputEvent;
import logic.event.InputEventDeleteMedia;
import logic.event.InputEventListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class InputEventListenerDeleteContent implements InputEventListener {
    private BusinessLogic businessLogic;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    public InputEventListenerDeleteContent(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public InputEventListenerDeleteContent(BusinessLogic businessLogic, DataInputStream dis, DataOutputStream dos) {
        this.businessLogic = businessLogic;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event instanceof InputEventDeleteMedia) {
            businessLogic.deleteContent(((InputEventDeleteMedia) event).getEventMediaAddress());
        }
    }
}
