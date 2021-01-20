package logic.network.media.protocol;

public interface ClientProtocol {
    void init(int id, int lineNumber);

    String nextLine();
}
