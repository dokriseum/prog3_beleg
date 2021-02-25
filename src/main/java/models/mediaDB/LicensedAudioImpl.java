package models.mediaDB;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class LicensedAudioImpl implements LicensedAudio {
    private int samplingRate;
    private String encoding;
    private String holder;
    private long bitrate;
    private Duration length;
    private BigDecimal size;
    private String address;
    private Collection<Tag> tags;
    private long accessCount;
    private Uploader uploader;
    private Date uploadDate;

    public LicensedAudioImpl(int samplingRate, String encoding, String holder, long bitrate, Duration length, BigDecimal size, String address, Collection<Tag> tags, Uploader uploader) {
        this.samplingRate = samplingRate;
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
}
