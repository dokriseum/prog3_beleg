package logic.eventsImpl.listener;

import exceptions.IllegalEventException;
import exceptions.SizeReachedException;
import logic.BusinessLogic;
import logic.events.InputEvent;
import logic.events.InputEventListener;
import logic.eventsImpl.InputEventAddMedia;


public class InputEventListenerAddMedia implements InputEventListener {
    private BusinessLogic businessLogic;

    public InputEventListenerAddMedia(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
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