package logic.domainLogic;

public abstract class Counter {
    private int value;

    public Counter(int startValue) {
        this.value = startValue;
    }

    public final int getValue() {
        return this.value;
    }

    public void increment() {
        if (Integer.MAX_VALUE == value) return;
        this.value++;
    }
}
