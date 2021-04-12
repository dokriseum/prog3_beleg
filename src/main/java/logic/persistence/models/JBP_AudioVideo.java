/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.persistence.models;

import models.mediaDB.AudioVideo;
import models.mediaDB.Tag;

import java.util.Collection;

public class JBP_AudioVideo extends PersistenceItem {
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

    public JBP_AudioVideo(AudioVideo audio_video) {
        this.sampling_rate = audio_video.getSamplingRate();
        this.encoding = audio_video.getEncoding();
        this.bitrate = audio_video.getBitrate();
        this.length = audio_video.getLength().getSeconds();
        this.size = audio_video.getSize().toPlainString();
        this.address = audio_video.getAddress();
        this.tags = audio_video.getTags();
        this.accessCount = audio_video.getAccessCount();
        this.bitrate = audio_video.getBitrate();
        this.uploader = audio_video.getUploader().getName();
        this.uploadDate = audio_video.getUploadDate().getTime();
    }

    public JBP_AudioVideo(int width, int height, int sampling_rate, String encoding, long bitrate, long length, String size, String address, Collection<Tag> tags, long accessCount, String uploader, long uploadDate) {
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

    public long getAccessCount() {
        return accessCount;
    }

    public String getUploader() {
        return uploader;
    }

    public long getUploadDate() {
        return uploadDate;
    }
}
