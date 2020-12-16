import logic.observer.observerPatternImpl.CounterObserver;
import logic.observer.observerPatternImpl.ObservableCounter;

public class ObserverPattern {
    /*
        sollte folgende Ausgabe produzieren:
        O1: 1->2
        O1: 2->3
        O2: 2->3
        O2: 3->4
        O2: 4->5
        O1: 3->5
     */
    public static void main(String[] args) {
        ObservableCounter counter = new ObservableCounter(0);
        counter.increment();
        CounterObserver observer1 = new CounterObserver(counter, "O1");
        counter.increment();
        new CounterObserver(counter, "O2");
        counter.increment();
        counter.removeObserver(observer1);
        counter.increment();
        counter.addObserver(observer1);
        counter.increment();
    }
}
