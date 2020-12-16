package logic.eventsImpl;

import logic.events.IncrementEvent;
import logic.events.IncrementEventListener;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IncrementEventHandlerTest {
    /*
    handler funktioniert ohne listener
     */
    @Test
    void handlerIsIndependent() {
        CounterIncrementEventSource counter = new CounterIncrementEventSource(0);
        IncrementEventHandler handler = new IncrementEventHandler();
        counter.setIncrementEventHandler(handler);

        counter.increment();

        assertEquals(1, counter.getValue());
    }

    /*
    eingehangene listener werden benachrichtigt
     */
    @Test
    void properPropagation() {
        IncrementEvent event = mock(IncrementEvent.class);
        IncrementEventHandler handler = new IncrementEventHandler();
        IncrementEventListener listener = mock(IncrementEventListener.class);

        handler.addListener(listener);
        handler.handle(event);

        verify(listener, times(1)).onIncrementEvent(any());
    }

    /*
    ausgehangene listner werden nicht benachrichtigt
     */
    @Test
    void properDeRegistration() {
        IncrementEvent event = mock(IncrementEvent.class);
        IncrementEventHandler handler = new IncrementEventHandler();
        IncrementEventListener listener = mock(IncrementEventListener.class);

        handler.addListener(listener);
        handler.removeListener(listener);
        handler.handle(event);

        verify(listener, never()).onIncrementEvent(any());
    }

    /*
    auf das event wird nicht zugegriffen
     */
    @Test
    void noInteractionWithEvent() {
        IncrementEvent event = mock(IncrementEvent.class);
        IncrementEventHandler handler = new IncrementEventHandler();
        handler.addListener(mock(IncrementEventListener.class));

        handler.handle(event);

        verifyZeroInteractions(event);
    }

    /*
    alle listener bekommen das gleiche event
     */
    @Test
    void properEventInstance() {
        IncrementEventHandler handler = new IncrementEventHandler();
        IncrementEventListener listener1 = mock(IncrementEventListener.class);
        IncrementEventListener listener2 = mock(IncrementEventListener.class);
        handler.addListener(listener1);
        handler.addListener(listener2);
        IncrementEvent event = mock(IncrementEvent.class);
        ArgumentCaptor<IncrementEvent> incrementEventCaptor1 = ArgumentCaptor.forClass(IncrementEvent.class);
        ArgumentCaptor<IncrementEvent> incrementEventCaptor2 = ArgumentCaptor.forClass(IncrementEvent.class);

        handler.handle(event);

        verify(listener1, times(1)).onIncrementEvent(incrementEventCaptor1.capture());
        verify(listener2, times(1)).onIncrementEvent(incrementEventCaptor2.capture());
        assertEquals(event, incrementEventCaptor1.getValue());
        assertEquals(event, incrementEventCaptor2.getValue());
    }
}