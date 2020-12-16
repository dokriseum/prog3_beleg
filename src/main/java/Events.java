import logic.events.IncrementEventListener;
import logic.eventsImpl.CounterIncrementEventSource;
import logic.eventsImpl.IncrementEventHandler;
import logic.eventsImpl.listener.CounterIncrementEventListener;

public class Events {
    /*
        sollte folgende Ausgabe produzieren:
        L1: 2->3
        L1: 3->4
        L2: 3->4
        L2: 4->5
        L2: 5->6
        L1: 5->6
    */
    public static void main(String[] args) {
        CounterIncrementEventSource counter = new CounterIncrementEventSource(0);
        counter.increment();
        IncrementEventHandler handler = new IncrementEventHandler();
        counter.setIncrementEventHandler(handler);
        counter.increment();
        IncrementEventListener listener1 = new CounterIncrementEventListener("L1");
        handler.addListener(listener1);
        counter.increment();
        IncrementEventListener listener2 = new CounterIncrementEventListener("L2");
        handler.addListener(listener2);
        counter.increment();
        handler.removeListener(listener1);
        counter.increment();
        handler.addListener(listener1);
        counter.increment();
    }
}
