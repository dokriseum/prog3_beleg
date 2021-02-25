package logic.event.listener;

import logic.BusinessLogic;
import logic.event.InputEvent;
import logic.event.InputEventListener;
import logic.event.InputEventUpdateContent;
import logic.utils.MemberChanger;

import java.io.DataInputStream;
import java.io.DataOutputStream;


public class InputEventListenerUpdateContent implements InputEventListener {
    private BusinessLogic businessLogic;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    public InputEventListenerUpdateContent(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public InputEventListenerUpdateContent(BusinessLogic businessLogic, DataInputStream dis, DataOutputStream dos) {
        this.businessLogic = businessLogic;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event instanceof InputEventUpdateContent) {
            InputEventUpdateContent tempInputEventUpdateContent = (InputEventUpdateContent) event;
            try {
                MemberChanger.writeLong(this.businessLogic.getContent(tempInputEventUpdateContent.getEventContent()), "accessCount", this.businessLogic.getContent(tempInputEventUpdateContent.getEventContent()).getAccessCount() + 1);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}