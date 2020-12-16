package logic.observer.observerPatternImpl;


import logic.observer.observerPattern.Observer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ObservableCounterTest {
    /*
    funktioniert ohne logic.observer
     */
    @Test
    public void counterIsIndependent() {
        ObservableCounter observableCounter = new ObservableCounter(0);

        observableCounter.increment();

        assertEquals(1, observableCounter.getValue());
    }

    /*
    benachrichtigt logic.observer
     */
    @Test
    void properNotification() {
        ObservableCounter observableCounter = new ObservableCounter(0);
        Observer observer = mock(Observer.class);

        observableCounter.addObserver(observer);
        observableCounter.increment();

        verify(observer).update();
    }

    /*
    ausgehangene logic.observer werden nicht benachrichtigt
     */
    @Test
    void properDeRegistration() {
        ObservableCounter observableCounter = new ObservableCounter(0);
        Observer observer = mock(Observer.class);

        observableCounter.addObserver(observer);
        observableCounter.removeObserver(observer);
        observableCounter.increment();

        verify(observer, never()).update();
    }
}