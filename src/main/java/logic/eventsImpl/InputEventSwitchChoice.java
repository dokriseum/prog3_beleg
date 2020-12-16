/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.eventsImpl;

import logic.events.InputEvent;
import view.ui.cli.Choice;

public class InputEventSwitchChoice extends InputEvent {
    private Choice eventChoice;

    public InputEventSwitchChoice(Object source, String text, Choice eventChoice) {
        super(source, text);
        this.eventChoice = eventChoice;
    }

    public Choice getEventChoice() {
        return eventChoice;
    }
}
