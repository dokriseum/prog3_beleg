/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.event.listener;

import logic.BusinessLogic;
import logic.event.InputEvent;
import logic.event.InputEventListener;
import logic.event.InputEventPersistence;
import logic.persistence.PersistenceStorage;
import logic.persistence.PersistenceType;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class InputEventListenerPersistence implements InputEventListener {
    private BusinessLogic businessLogic;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    public InputEventListenerPersistence(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public InputEventListenerPersistence(BusinessLogic businessLogic, DataInputStream dis, DataOutputStream dos) {
        this.businessLogic = businessLogic;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event instanceof InputEventPersistence) {
            PersistenceType type = ((InputEventPersistence) event).getType();
            PersistenceStorage persist = new PersistenceStorage(businessLogic);

            try {
                switch (type) {
                    case SAVE_JBP:
                        persist.saveJBP();
                        break;
                    case SAVE_JOS:
                        persist.saveJOS();
                        break;
                    case LOAD_JBP:
                        persist.loadJBP();
                        break;
                    case LOAD_JOS:
                        persist.loadJOS();
                        break;
                    case SAVE_CONTENT:
                        persist.saveContent(((InputEventPersistence) event).getEventMediaAddress());
                        break;
                    case LOAD_CONTENT:
                        persist.loadContent(((InputEventPersistence) event).getEventMediaAddress());
                        break;
                    default:
                        System.err.println("wrong type");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
