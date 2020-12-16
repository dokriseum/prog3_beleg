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

public class InputEventListenerShowMedia implements InputEventListener {
    private BusinessLogic businessLogic;

    public InputEventListenerShowMedia(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @Override
    public void onInputEvent(InputEvent event) throws IllegalEventException {

        if (event instanceof InputEventShowMedia == false) {
            throw new IllegalEventException();
        }

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
