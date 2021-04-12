/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.persistence.jbp_models;

import models.mediaDB.InteractiveVideo;
import models.mediaDB.Tag;

import java.util.Collection;

public class JBP_InteractiveVideo extends PersistenceItem {
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
    private String type;

    public JBP_InteractiveVideo() {
    }

    public JBP_InteractiveVideo(InteractiveVideo interactive_video) {
        this.width = interactive_video.getWidth();
        this.height = interactive_video.getHeight();
        this.encoding = interactive_video.getEncoding();
        this.bitrate = interactive_video.getBitrate();
        this.length = interactive_video.getLength().getSeconds();
        this.size = interactive_video.getSize().toPlainString();
        this.address = interactive_video.getAddress();
        this.tags = interactive_video.getTags();
        this.accessCount = interactive_video.getAccessCount();
        this.bitrate = interactive_video.getBitrate();
        this.uploader = interactive_video.getUploader().getName();
        this.uploadDate = interactive_video.getUploadDate().getTime();
        this.type = interactive_video.getType();
    }

    public JBP_InteractiveVideo(int width, int height, String encoding, long bitrate, long length, String size, String address, Collection<Tag> tags, long accessCount, String uploader, long uploadDate, String type) {
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
        this.type = type;
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

    public String getType() {
        return type;
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

    public void setType(String type) {
        this.type = type;
    }
}
