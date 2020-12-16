/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.observer;

public interface Observable {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObserver();
}
