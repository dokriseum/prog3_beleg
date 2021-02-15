package logic.event.listener;

import exceptions.SizeReachedException;
import logic.BusinessLogic;
import logic.event.InputEvent;
import logic.event.InputEventAddContent;
import logic.event.InputEventListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;


public class InputEventListenerAddContent implements InputEventListener {
    private BusinessLogic businessLogic;
    private java.io.DataInputStream dis = null;
    private DataOutputStream dos = null;

    public InputEventListenerAddContent(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public InputEventListenerAddContent(BusinessLogic businessLogic, DataInputStream dis, DataOutputStream dos) {
        this.businessLogic = businessLogic;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event instanceof InputEventAddContent) {
            InputEventAddContent tempInputEventAddContent = (InputEventAddContent) event;
            try {
                businessLogic.uploadContent(tempInputEventAddContent.getEventMediaType(), tempInputEventAddContent.getEventSamplingRate(), tempInputEventAddContent.getEventWidth(), tempInputEventAddContent.getEventHeight(), tempInputEventAddContent.getEventEncording(), tempInputEventAddContent.getEventHolder(), tempInputEventAddContent.getEventBitrate(), tempInputEventAddContent.getEventLength(), tempInputEventAddContent.getEventTags(), tempInputEventAddContent.getEventAccessCount(), tempInputEventAddContent.getEventUploader(), tempInputEventAddContent.getEventUploadDate(), tempInputEventAddContent.getEventType());
            } catch (SizeReachedException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}