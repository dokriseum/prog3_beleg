/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package models.mediaDB;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class MediaContentImpl implements MediaContent {
    private long bitrate;
    private Duration length;
    private BigDecimal size;
    private String address;
    private Collection<Tag> tags;
    private long accessCount;

    public MediaContentImpl() {
    }

    public MediaContentImpl(long bitrate, Duration length, BigDecimal size, String address, Collection<Tag> tags, long accessCount) {
        this.bitrate = bitrate;
        this.length = length;
        this.size = size;
        this.address = address;
        this.tags = tags;
        this.accessCount = accessCount;
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
    public String toString() {
        return "MediaContentImpl{" +
                "bitrate=" + bitrate +
                ", length=" + this.getDurationAsString() +
                ", size=" + size +
                ", address='" + address + '\'' +
                ", tags=" + tags +
                ", accessCount=" + accessCount +
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
