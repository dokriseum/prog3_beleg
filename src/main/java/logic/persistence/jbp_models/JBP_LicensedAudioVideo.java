/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.persistence.jbp_models;

import models.mediaDB.LicensedAudioVideo;
import models.mediaDB.Tag;

import java.util.Collection;

public class JBP_LicensedAudioVideo extends PersistenceItem {
    private int width;
    private int height;
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

    public JBP_LicensedAudioVideo() {
    }

    public JBP_LicensedAudioVideo(LicensedAudioVideo licensed_audio_video) {
        this.sampling_rate = licensed_audio_video.getSamplingRate();
        this.encoding = licensed_audio_video.getEncoding();
        this.bitrate = licensed_audio_video.getBitrate();
        this.length = licensed_audio_video.getLength().getSeconds();
        this.size = licensed_audio_video.getSize().toPlainString();
        this.address = licensed_audio_video.getAddress();
        this.tags = licensed_audio_video.getTags();
        this.accessCount = licensed_audio_video.getAccessCount();
        this.bitrate = licensed_audio_video.getBitrate();
        this.uploader = licensed_audio_video.getUploader().getName();
        this.uploadDate = licensed_audio_video.getUploadDate().getTime();
        this.holder = licensed_audio_video.getHolder();
    }

    public JBP_LicensedAudioVideo(int width, int height, int sampling_rate, String encoding, long bitrate, long length, String size, String address, Collection<Tag> tags, long accessCount, String uploader, long uploadDate, String holder) {
        this.width = width;
        this.height = height;
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

    private String[] fillCollection(Collection<Tag> tags) {
        String[] array = new String[tags.size()];
        Object[] tags_array = tags.toArray();
        for (int i = 0; i < tags.size(); i++) {
            array[i] = ((Tag) tags_array[i]).name();
        }
        return array;
    }


    @Override
    public String toString() {
        return "JBP_LicensedAudioVideo{" +
                "width=" + width +
                ", height=" + height +
                ", sampling_rate=" + sampling_rate +
                ", encoding='" + encoding + '\'' +
                ", bitrate=" + bitrate +
                ", length=" + length +
                ", size='" + size + '\'' +
                ", address='" + address + '\'' +
                ", tags=" + tags.toString() +
                ", accessCount=" + accessCount +
                ", uploader='" + uploader + '\'' +
                ", uploadDate=" + uploadDate +
                ", holder='" + holder + '\'' +
                '}';
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
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

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
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
