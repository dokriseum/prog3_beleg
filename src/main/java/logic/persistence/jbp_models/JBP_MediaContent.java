/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.persistence.jbp_models;

import models.mediaDB.MediaContent;
import models.mediaDB.Tag;

import java.util.Collection;

public class JBP_MediaContent extends PersistenceItem {
    private String address;
    private Collection<Tag> tags;
    private long accessCount;
    private long bitrate;
    private long length;
    private String size;

    public JBP_MediaContent() {
    }

    public JBP_MediaContent(MediaContent media_content) {
        this.address = media_content.getAddress();
        this.tags = media_content.getTags();
        this.accessCount = media_content.getAccessCount();
        this.bitrate = media_content.getBitrate();
        this.length = media_content.getLength().getSeconds();
        this.size = media_content.getSize().toPlainString();
    }

    public JBP_MediaContent(long bitrate, long length, String size, String address, Collection<Tag> tags, long accessCount) {
        this.address = address;
        this.tags = tags;
        this.accessCount = accessCount;
        this.bitrate = bitrate;
        this.length = length;
        this.size = size;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    public void setAccessCount(long accessCount) {
        this.accessCount = accessCount;
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
}
