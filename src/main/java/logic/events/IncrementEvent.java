package logic.events;

import java.util.EventObject;

public class IncrementEvent extends EventObject {
    private int oldValue, newValue;

    public IncrementEvent(Object source, int oldValue, int newValue) {
        super(source);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public int getOldValue() {
        return this.oldValue;
    }

    public int getNewValue() {
        return this.newValue;
    }
}
