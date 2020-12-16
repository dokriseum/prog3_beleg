import view.debugger.Counter;

public class AppToDebug {
    public static void main(String[] args) {
        Counter c = new Counter();
        c.count(16700000f, 17000000f);
    }
}
