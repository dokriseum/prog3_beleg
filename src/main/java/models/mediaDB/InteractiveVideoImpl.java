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

public class InteractiveVideoImpl implements InteractiveVideo {
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
    private String type;

    public InteractiveVideoImpl(int width, int height, String encording, long bitrate, Duration length, BigDecimal size, String address, Collection<Tag> tags, long accessCount, Uploader uploader, Date uploadDate, String type) {
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
        this.type = type;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
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
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "InteractiveVideo{" +
                "\n\twidth=" + width +
                ", \n\theight=" + height +
                ", \n\tencording='" + encording + '\'' +
                ", \n\tbitrate=" + bitrate +
                ", \n\tlength=" + length +
                ", \n\tsize=" + size +
                ", \n\taddress='" + address + '\'' +
                ", \n\ttags=" + tags +
                ", \n\taccessCount=" + accessCount +
                ", \n\tuploader=" + uploader.getName() +
                ", \n\tuploadDate=" + uploadDate +
                ", \n\ttype='" + type + '\'' +
                "\n}\n";
    }
}
