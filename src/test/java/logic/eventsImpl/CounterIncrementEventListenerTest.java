package logic.eventsImpl;

import logic.events.IncrementEvent;
import logic.eventsImpl.listener.CounterIncrementEventListener;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

class CounterIncrementEventListenerTest {
    /*
    die Werte werden aus dem event gelesen und auf die source wird nicht zugegriffen
     */
    @Test
    public void properNotificationReaction() {
        CounterIncrementEventListener listener = new CounterIncrementEventListener("");
        IncrementEvent incrementEvent = mock(IncrementEvent.class);

        listener.onIncrementEvent(incrementEvent);

        verify(incrementEvent, atLeastOnce()).getOldValue();
        verify(incrementEvent, atLeastOnce()).getNewValue();
        verify(incrementEvent, never()).getSource();
    }

    /*
    das event wird behandelt
     */
    @Test
    void properOutput() {
        PrintStream originalOut = System.out;
        try {
            PrintStream out = mock(PrintStream.class);
            System.setOut(out);
            IncrementEvent event = mock(IncrementEvent.class);
            when(event.getOldValue()).thenReturn(3);
            when(event.getNewValue()).thenReturn(2);
            CounterIncrementEventListener listener = new CounterIncrementEventListener("T");

            listener.onIncrementEvent(event);

            verify(out).println("T: 3->2");
        } finally {
            System.setOut(originalOut);
        }
    }

}