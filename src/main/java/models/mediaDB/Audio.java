package models.mediaDB;

public interface Audio extends MediaContent, Uploadable {
    int getSamplingRate();

    String getEncoding();
}
