package logic.observer.observerPatternImpl;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

class CounterObserverTest {
    /*
    benutzt ausschließlich getValue()
     */
    @Test
    void properNotificationReaction() {
        ObservableCounter observableCounter = mock(ObservableCounter.class);
        CounterObserver observer = new CounterObserver(observableCounter, "");

        observer.update();

        verify(observableCounter, times(1)).addObserver(any());
        verify(observableCounter, atLeastOnce()).getValue();
        verifyNoMoreInteractions(observableCounter);
    }

    /*
    registriert sich bei der Initialisierung
     */
    @Test
    void properInit() {
        ObservableCounter observableCounter = mock(ObservableCounter.class);

        CounterObserver observer = new CounterObserver(observableCounter, "");

        verify(observableCounter, times(1)).addObserver(observer);
    }

    /*
    initialisiert sich richtig bei der Initialisierung
     */
    @Test
    void properConnecting() {
        ObservableCounter observableCounter = mock(ObservableCounter.class);

        new CounterObserver(observableCounter, "");

        verify(observableCounter, atLeastOnce()).getValue();
    }

    /*
    produziert richtige Ausgabe
     */
    @Test
    void properOutput() {
        PrintStream originalOut = System.out;
        try {
            PrintStream out = mock(PrintStream.class);
            System.setOut(out);
            ObservableCounter observableCounter = mock(ObservableCounter.class);
            when(observableCounter.getValue()).thenReturn(2).thenReturn(4);
            CounterObserver observer = new CounterObserver(observableCounter, "T");

            observer.update();

            verify(out).println("T: 2->4");
        } finally {
            System.setOut(originalOut);
        }
    }

    /*
    keine Änderung => keine Ausgabe
     */
    @Test
    void properStateManagementOnlyChanges() {
        PrintStream originalOut = System.out;
        try {
            PrintStream out = mock(PrintStream.class);
            System.setOut(out);
            ObservableCounter observableCounter = mock(ObservableCounter.class);
            when(observableCounter.getValue()).thenReturn(5);
            CounterObserver observer = new CounterObserver(observableCounter, "T");

            observer.update();

            verifyZeroInteractions(out);
        } finally {
            System.setOut(originalOut);
        }
    }
}