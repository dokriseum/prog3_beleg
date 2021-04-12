/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.event;

import logic.persistence.PersistenceType;

public class InputEventPersistence extends InputEvent {
    private String eventMediaAddress;
    private PersistenceType type;

    public InputEventPersistence(Object source, String text, PersistenceType type) {
        super(source, text);
        this.eventMediaAddress = null;
        this.type = type;
    }

    public InputEventPersistence(Object source, String text, PersistenceType type, String eventMediaAddress) {
        super(source, text);
        this.eventMediaAddress = eventMediaAddress;
        this.type = type;
    }

    public String getEventMediaAddress() {
        return eventMediaAddress;
    }

    public PersistenceType getType() {
        return type;
    }
}
