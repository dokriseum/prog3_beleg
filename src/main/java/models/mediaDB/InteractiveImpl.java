/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package models.mediaDB;

import java.util.Collection;

public class InteractiveImpl implements Interactive {
    private String address;
    private Collection<Tag> tags;
    private long accessCount;
    private String type;

    public InteractiveImpl() {
    }

    public InteractiveImpl(String address, Collection<Tag> tags, long accessCount, String type) {
        this.address = address;
        this.tags = tags;
        this.accessCount = accessCount;
        this.type = type;
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
    public String getType() {
        return type;
    }
}
