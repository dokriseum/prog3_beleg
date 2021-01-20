package logic.network.media.protocol;

public class Protocol implements ClientProtocol, ServerProtocol {
    @Override
    public void init(int id, int lineNumber) {

    }

    @Override
    public String nextLine() {
        return null;
    }

    @Override
    public int getBookId() {
        return 0;
    }

    @Override
    public int getLineNumber() {
        return 0;
    }

    @Override
    public void setLine(String line) {

    }
}
