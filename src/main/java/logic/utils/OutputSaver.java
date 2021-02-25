package logic.utils;

public class OutputSaver {
    private static String output;
    private static boolean isShowEvent = false;

    public static String getOutput() {
        return output;
    }

    public static void setOutput(String output) {
        OutputSaver.output = output;
    }

    public static boolean isIsShowEvent() {
        return isShowEvent;
    }

    public static void setIsShowEvent(boolean isShowEvent) {
        OutputSaver.isShowEvent = isShowEvent;
    }
}
