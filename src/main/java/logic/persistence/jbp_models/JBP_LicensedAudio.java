/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.persistence.jbp_models;

import models.mediaDB.LicensedAudio;
import models.mediaDB.Tag;

import java.util.Collection;

public class JBP_LicensedAudio extends PersistenceItem {
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
    private String holder;

    @Override
    public String toString() {
        return "JBP_LicensedAudio{}";
    }

    public JBP_LicensedAudio(LicensedAudio licensed_audio) {
        this.sampling_rate = licensed_audio.getSamplingRate();
        this.encoding = licensed_audio.getEncoding();
        this.bitrate = licensed_audio.getBitrate();
        this.length = licensed_audio.getLength().getSeconds();
        this.size = licensed_audio.getSize().toPlainString();
        this.address = licensed_audio.getAddress();
        this.tags = licensed_audio.getTags();
        this.accessCount = licensed_audio.getAccessCount();
        this.bitrate = licensed_audio.getBitrate();
        this.uploader = licensed_audio.getUploader().getName();
        this.uploadDate = licensed_audio.getUploadDate().getTime();
        this.holder = licensed_audio.getHolder();
    }

    public JBP_LicensedAudio(int sampling_rate, String encoding, long bitrate, long length, String size, String address, Collection<Tag> tags, long accessCount, String uploader, long uploadDate, String holder) {
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
        this.holder = holder;
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

    public String getHolder() {
        return holder;
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

    public void setHolder(String holder) {
        this.holder = holder;
    }
}
