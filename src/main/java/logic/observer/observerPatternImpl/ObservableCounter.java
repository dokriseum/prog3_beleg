package logic.observer.observerPatternImpl;

import logic.domainLogic.Counter;
import logic.observer.observerPattern.Observable;
import logic.observer.observerPattern.Observer;

public class ObservableCounter extends Counter implements Observable {
    public ObservableCounter(int startValue) {
        super(startValue);
    }

    @Override
    public boolean addObserver(Observer observer) {
        //TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeObserver(Observer observer) {
        //TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void notifyObservers() {
        //TODO
    }

    @Override
    public void increment() {
        //TODO
    }
}
