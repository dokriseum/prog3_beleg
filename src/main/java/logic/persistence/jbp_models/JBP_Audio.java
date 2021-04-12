package logic.persistence.jbp_models;

import models.mediaDB.Audio;
import models.mediaDB.Tag;

import java.util.Collection;

public class JBP_Audio extends PersistenceItem {
    private int sampling_rate;
    private String encoding;
    private long bitrate;
    private long length;
    private String size;
    private String address;
    private Collection<Tag> tags;
    private long accessCount;
    private String uploader;
    private long uploadDate;

    public JBP_Audio() {
    }

    public JBP_Audio(Audio audio) {
        this.sampling_rate = audio.getSamplingRate();
        this.encoding = audio.getEncoding();
        this.bitrate = audio.getBitrate();
        this.length = audio.getLength().getSeconds();
        this.size = audio.getSize().toPlainString();
        this.address = audio.getAddress();
        this.tags = audio.getTags();
        this.accessCount = audio.getAccessCount();
        this.bitrate = audio.getBitrate();
        this.uploader = audio.getUploader().getName();
        this.uploadDate = audio.getUploadDate().getTime();
    }

    public JBP_Audio(int sampling_rate, String encoding, long bitrate, long length, String size, String address, Collection<Tag> tags, long accessCount, String uploader, long uploadDate) {
        this.sampling_rate = sampling_rate;
        this.encoding = encoding;
        this.bitrate = bitrate;
        this.length = length;
        this.size = size;
        this.address = address;
        this.tags = tags;
        this.accessCount = accessCount;
        this.uploader = uploader;
        this.uploadDate = uploadDate;
    }

    public int getSamplingRate() {
        return sampling_rate;
    }

    public String getEncoding() {
        return encoding;
    }

    public long getBitrate() {
        return bitrate;
    }

    public long getLength() {
        return length;
    }

    public String getSize() {
        return size;
    }

    public String getAddress() {
        return address;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public long getAccessCount() {
        return accessCount;
    }

    public String getUploader() {
        return uploader;
    }

    public long getUploadDate() {
        return uploadDate;
    }

    public void setSamplingRate(int sampling_rate) {
        this.sampling_rate = sampling_rate;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setBitrate(long bitrate) {
        this.bitrate = bitrate;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    public void setAccessCount(long accessCount) {
        this.accessCount = accessCount;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public void setUploadDate(long uploadDate) {
        this.uploadDate = uploadDate;
    }
}
