/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.persistence.jbp_models;

import models.mediaDB.Tag;
import models.mediaDB.Video;

import java.util.Collection;

public class JBP_Video extends PersistenceItem {
    private int width;
    private int height;
    private String encoding;
    private long bitrate;
    private long length;
    private String size;
    private String address;
    private Collection<Tag> tags;
    private long accessCount;
    private String uploader;
    private long uploadDate;

    public JBP_Video() {
    }

    public JBP_Video(Video video) {
        this.width = video.getWidth();
        this.height = video.getHeight();
        this.encoding = video.getEncoding();
        this.bitrate = video.getBitrate();
        this.length = video.getLength().getSeconds();
        this.size = video.getSize().toPlainString();
        this.address = video.getAddress();
        this.tags = video.getTags();
        this.accessCount = video.getAccessCount();
        this.bitrate = video.getBitrate();
        this.uploader = video.getUploader().getName();
        this.uploadDate = video.getUploadDate().getTime();
    }

    public JBP_Video(int width, int height, String encoding, long bitrate, long length, String size, String address, Collection<Tag> tags, long accessCount, String uploader, long uploadDate) {
        this.width = width;
        this.height = height;
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

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
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
