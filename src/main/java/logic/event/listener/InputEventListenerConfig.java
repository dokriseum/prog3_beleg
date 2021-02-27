package logic.event.listener;

import logic.BusinessLogic;
import logic.event.InputEvent;
import logic.event.InputEventConfig;
import logic.event.InputEventHandler;
import logic.event.InputEventListener;
import logic.observer.CheckSizePerCentObserver;
import logic.observer.CheckTagsObserver;
import logic.observer.Observer;
import logic.utils.OutputSaver;
import view.cli.Input;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;


public class InputEventListenerConfig implements InputEventListener {
    private BusinessLogic businessLogic;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private List<Observer> lObservers;
    private List<InputEventListener> lListeners;
    private InputEventHandler handler = new InputEventHandler();
    private Input input;

    public InputEventListenerConfig(BusinessLogic businessLogic, Input input) {
        this.businessLogic = businessLogic;
        this.input = input;
        this.dis = null;
        this.dos = null;

        this.lObservers = new ArrayList<>();
        this.lListeners = new ArrayList<>();

        this.lObservers.add(new CheckSizePerCentObserver(this.businessLogic));
        this.lObservers.add(new CheckTagsObserver(this.businessLogic));

        this.lListeners.add(new InputEventListenerAddContent(this.businessLogic));
        this.lListeners.add(new InputEventListenerAddUploader(this.businessLogic));
        this.lListeners.add(new InputEventListenerShowMedia(this.businessLogic));
        this.lListeners.add(new InputEventListenerShowUploader(this.businessLogic));
        this.lListeners.add(new InputEventListenerDeleteContent(this.businessLogic));
        this.lListeners.add(new InputEventListenerDeleteUploader(this.businessLogic));
        this.lListeners.add(new InputEventListenerUpdateContent(this.businessLogic));
        this.lListeners.add(new InputEventListenerPersistence(this.businessLogic));
        this.lListeners.add(this);

        this.lListeners.add(new InputEventListenerOutput(this.businessLogic));
        this.lListeners.add(new InputEventListenerSwitchChoice(this.businessLogic));
        this.lListeners.add(new InputEventListenerExit(this.businessLogic));

        this.input.setHandler(this.handler);
    }

    public InputEventListenerConfig(BusinessLogic businessLogic, DataInputStream dis, DataOutputStream dos) {
        this.businessLogic = businessLogic;
        this.dis = dis;
        this.dos = dos;
        this.lObservers = new ArrayList<>();
        this.lObservers.add(new CheckSizePerCentObserver(this.businessLogic));
        this.lObservers.add(new CheckTagsObserver(this.businessLogic));
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event instanceof InputEventConfig) {
            StringBuffer sb = new StringBuffer();
            // if show observers or listeners
            if (((InputEventConfig) event).isShow()) {
                switch (((InputEventConfig) event).getName().charAt(0)) {
                    case 'o':
                        sb.append("available observers:\n");
                        for (Observer k : this.lObservers) {
                            sb.append("\t" + k.getClass().getName() + "\n");
                        }
                    case 'l':
                        sb.append("available listeners:\n");
                        for (InputEventListener k : this.lListeners) {
                            sb.append("\t" + k.getClass().getName() + "\n");
                        }
                }
            } else {
                if (((InputEventConfig) event).isActivate()) {
                    for (Observer k : this.lObservers) {
                        if (k.getClass().getName().equals(((InputEventConfig) event).getName())) {
                            this.businessLogic.addObserver(k);
                            sb.append(sb.append(k.getClass().getName()) + " was added.");
                        }
                    }
                    for (InputEventListener k : this.lListeners) {
                        if (k.getClass().getName().equals(((InputEventConfig) event).getName())) {
                            this.handler.add(k);
                            sb.append(sb.append(k.getClass().getName()) + " was added.");
                        }
                    }
                } else {
                    for (Observer k : this.lObservers) {
                        if (k.getClass().getName().equals(((InputEventConfig) event).getName())) {
                            this.businessLogic.removeObserver(k);
                            sb.append(sb.append(k.getClass().getName()) + " was removed.");
                        }
                    }
                    for (InputEventListener k : this.lListeners) {
                        if (k.getClass().getName().equals(((InputEventConfig) event).getName())) {
                            this.handler.remove(k);
                            sb.append(sb.append(k.getClass().getName()) + " was removed.");
                        }
                    }
                }
            }
            if ((this.dis != null) && (this.dos != null)) {
                OutputSaver.setOutput(sb.toString());
            } else {
                System.out.println(sb.toString());
            }
        }
    }

    private void addAll() {
        for (Observer k : this.lObservers) {
            this.businessLogic.addObserver(k);
        }
        for (InputEventListener k : this.lListeners) {
            if (!k.getClass().getName().equals(this.getClass().getName())) {
                this.handler.add(k);
            }
        }
    }

    private void removeAll() {
        for (Observer k : this.lObservers) {
            this.businessLogic.removeObserver(k);
        }
        for (InputEventListener k : this.lListeners) {
            if (!k.getClass().getName().equals(this.getClass().getName())) {
                this.handler.remove(k);
            }
        }
    }
}