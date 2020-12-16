package logic.events;

import exceptions.IllegalEventException;

import java.util.EventListener;

public interface InputEventListener extends EventListener {
    void onInputEvent(InputEvent event) throws IllegalEventException;
}
