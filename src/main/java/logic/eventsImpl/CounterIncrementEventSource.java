package logic.eventsImpl;

import logic.domainLogic.Counter;

public class CounterIncrementEventSource extends Counter {
    public CounterIncrementEventSource(int startValue) {
        super(startValue);
    }

    public void setIncrementEventHandler(IncrementEventHandler handler) {
        //TODO
    }

    @Override
    public void increment() {
        //TODO
    }
}
