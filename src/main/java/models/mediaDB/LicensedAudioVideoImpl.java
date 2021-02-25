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

public class LicensedAudioVideoImpl implements LicensedAudioVideo {
    private int samplingRate;
    private int width;
    private int height;
    private String encoding;
    private String holder;
    private long bitrate;
    private Duration length;
    private BigDecimal size;
    private String address;
    private Collection<Tag> tags;
    private long accessCount = 0;
    private Uploader uploader;
    private Date uploadDate;

    public LicensedAudioVideoImpl(int samplingRate, int width, int height, String encoding, String holder, long bitrate, Duration length, BigDecimal size, String address, Collection<Tag> tags, Uploader uploader) {
        this.samplingRate = samplingRate;
        this.width = width;
        this.height = height;
        this.encoding = encoding;
        this.holder = holder;
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
    public String getHolder() {
        return holder;
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
        return "LicensedAudioVideo{" +
                "\n\tsamplingRate=" + samplingRate +
                ", \n\twidth=" + width +
                ", \n\theight=" + height +
                ", \n\tencording='" + encoding + '\'' +
                ", \n\tholder='" + holder + '\'' +
                ", \n\tbitrate=" + bitrate +
                ", \n\tlength=" + this.getDurationAsString() +
                ", \n\tsize=" + size +
                ", \n\taddress='" + address + '\'' +
                ", \n\ttags=" + tags +
                ", \n\taccessCount=" + accessCount +
                ", \n\tuploader=" + uploader.getName() +
                ", \n\tuploadDate=" + uploadDate +
                "\n}\n";
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
