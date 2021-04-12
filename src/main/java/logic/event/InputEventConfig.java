package logic.event;

public class InputEventConfig extends InputEvent {
    private String name;
    private boolean activate;
    private boolean show;

    public InputEventConfig(Object source, String text, String name, boolean activate, boolean show) {
        super(source, text);
        this.name = name;
        this.activate = activate;
        this.show = show;
    }

    public String getName() {
        return name;
    }

    public boolean isActivate() {
        return activate;
    }

    public boolean isShow() {
        return show;
    }
}