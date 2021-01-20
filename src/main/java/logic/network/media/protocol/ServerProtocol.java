package logic.network.media.protocol;

public interface ServerProtocol {
    int getBookId();

    int getLineNumber();

    void setLine(String line);
}
