/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package models.mediaDB;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class VideoImpl implements Video {
    private int width;
    private int height;
    private String encording;
    private long bitrate;
    private Duration length;
    private BigDecimal size;
    private String address;
    private Collection<Tag> tags;
    private long accessCount = 0;
    private Uploader uploader;
    private Date uploadDate;

    public VideoImpl(int width, int height, String encording, long bitrate, Duration length, BigDecimal size, String address, Collection<Tag> tags, long accessCount, Uploader uploader, Date uploadDate) {
        this.width = width;
        this.height = height;
        this.encording = encording;
        this.bitrate = bitrate;
        this.length = length;
        this.size = size;
        this.address = address;
        this.tags = tags;
        this.accessCount = accessCount;
        this.uploader = uploader;
        this.uploadDate = uploadDate;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getEncoding() {
        return encording;
    }

    @Override
    public long getBitrate() {
        return bitrate;
    }

    @Override
    public Duration getLength() {
        return length;
    }

    @Override
    public BigDecimal getSize() {
        return size;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public Collection<Tag> getTags() {
        return tags;
    }

    @Override
    public long getAccessCount() {
        return accessCount;
    }

    @Override
    public Uploader getUploader() {
        return uploader;
    }

    @Override
    public Date getUploadDate() {
        return uploadDate;
    }

    @Override
    public String toString() {
        return "VideoImpl{" +
                "width=" + width +
                ", height=" + height +
                ", encording='" + encording + '\'' +
                ", bitrate=" + bitrate +
                ", length=" + this.getDurationAsString() +
                ", size=" + size +
                ", address='" + address + '\'' +
                ", tags=" + tags +
                ", accessCount=" + accessCount +
                ", uploader=" + uploader.getName() +
                ", uploadDate=" + uploadDate +
                '}';
    }

    private String getDurationAsString() {
        StringBuilder duration = new StringBuilder();

        if (length.toHours() < 10) {
            duration.append(0);
        }
        duration.append(length.toHours());
        duration.append(":");

        if ((length.toMinutes() % 24) < 10) {
            duration.append(0);
        }
        duration.append(length.toMinutes());
        duration.append(":");
        if ((length.getSeconds() % 60) < 10) {
            duration.append(0);
        }
        duration.append(length.getSeconds() % 60);

        return duration.toString();
    }
}
