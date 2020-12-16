package logic.events;

import java.util.EventListener;

public interface IncrementEventListener extends EventListener {
    void onIncrementEvent(IncrementEvent incrementEvent);
}
