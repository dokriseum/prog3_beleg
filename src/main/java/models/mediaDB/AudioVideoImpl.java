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

public class AudioVideoImpl implements AudioVideo {
    private int samplingRate;
    private int width;
    private int height;
    private String encoding;
    private long bitrate;
    private Duration length;
    private BigDecimal size;
    private String address;
    private Collection<Tag> tags;
    private long accessCount;
    private Uploader uploader;
    private Date uploadDate;

    public AudioVideoImpl(int samplingRate, int width, int height, String encoding, long bitrate, Duration length, BigDecimal size, String address, Collection<Tag> tags, Uploader uploader) {
        this.samplingRate = samplingRate;
        this.width = width;
        this.height = height;
        this.encoding = encoding;
        this.bitrate = bitrate;
        this.length = length;
        this.size = size;
        this.address = address;
        this.tags = tags;
        this.accessCount = 0;
        this.uploader = uploader;
        this.uploadDate = new Date();
    }

    @Override
    public int getSamplingRate() {
        return samplingRate;
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
        return encoding;
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
        return "AudioVideoImpl{" +
                "samplingRate=" + samplingRate +
                ", width=" + width +
                ", height=" + height +
                ", encording='" + encoding + '\'' +
                ", bitrate=" + bitrate +
                ", length=" + length +
                ", size=" + size +
                ", address='" + address + '\'' +
                ", tags=" + tags +
                ", accessCount=" + accessCount +
                ", uploader=" + uploader.getName() +
                ", uploadDate=" + uploadDate +
                '}';
    }
}
