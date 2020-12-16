package logic.eventsImpl;

import logic.events.IncrementEvent;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CounterIncrementEventSourceTest {
    /*
    funktioniert ohne eingehangenen handler
     */
    @Test
    public void counterIsIndependent() {
        CounterIncrementEventSource counter = new CounterIncrementEventSource(0);

        counter.increment();

        assertEquals(1, counter.getValue());
    }

    /*
    das event ist richtig befüllt und wird verschickt
     */
    @Test
    public void validIncrementEvent() {
        CounterIncrementEventSource counter = new CounterIncrementEventSource(0);
        IncrementEventHandler handler = mock(IncrementEventHandler.class);
        counter.setIncrementEventHandler(handler);
        ArgumentCaptor<IncrementEvent> incrementEventCaptor = ArgumentCaptor.forClass(IncrementEvent.class);

        counter.increment();

        verify(handler, times(1)).handle(incrementEventCaptor.capture());
        assertEquals(counter, incrementEventCaptor.getValue().getSource());
        assertEquals(0, incrementEventCaptor.getValue().getOldValue());
        assertEquals(1, incrementEventCaptor.getValue().getNewValue());
    }

    /*
    kein increment => kein event
     */
    @Test
    public void noFalseIncrementEvent() {
        CounterIncrementEventSource counter = new CounterIncrementEventSource(Integer.MAX_VALUE);
        IncrementEventHandler handler = mock(IncrementEventHandler.class);
        counter.setIncrementEventHandler(handler);

        counter.increment();

        verify(handler, never()).handle(any());
    }
}