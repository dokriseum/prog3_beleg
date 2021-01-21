package logic.eventsImpl.listener;

import exceptions.IllegalEventException;
import exceptions.SizeReachedException;
import logic.BusinessLogic;
import logic.events.InputEvent;
import logic.events.InputEventListener;
import logic.eventsImpl.InputEventAddMedia;

import java.io.DataInputStream;
import java.io.DataOutputStream;


public class InputEventListenerAddMedia implements InputEventListener {
    private BusinessLogic businessLogic;
    private java.io.DataInputStream dis = null;
    private DataOutputStream dos = null;

    public InputEventListenerAddMedia(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public InputEventListenerAddMedia(BusinessLogic businessLogic, DataInputStream dis, DataOutputStream dos) {
        this.businessLogic = businessLogic;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void onInputEvent(InputEvent event) throws IllegalEventException {
        if (event instanceof InputEventAddMedia == false) {
            throw new IllegalEventException();
        }
        InputEventAddMedia tempInputEventAddMedia = (InputEventAddMedia) event;
        try {
            businessLogic.uploadContent(tempInputEventAddMedia.getEventMediaType(), tempInputEventAddMedia.getEventSamplingRate(), tempInputEventAddMedia.getEventWidth(), tempInputEventAddMedia.getEventHeight(), tempInputEventAddMedia.getEventEncording(), tempInputEventAddMedia.getEventHolder(), tempInputEventAddMedia.getEventBitrate(), tempInputEventAddMedia.getEventLength(), tempInputEventAddMedia.getEventTags(), tempInputEventAddMedia.getEventAccessCount(), tempInputEventAddMedia.getEventUploader(), tempInputEventAddMedia.getEventUploadDate(), tempInputEventAddMedia.getEventType());
        } catch (SizeReachedException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}