/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package models.mediaDB;

import java.util.Collection;

public class ContentImpl implements Content {
    private String address;
    private Collection<Tag> tags;
    private long accessCount;

    public ContentImpl() {
    }

    public ContentImpl(String address, Collection<Tag> tags, long accessCount) {
        this.address = address;
        this.tags = tags;
        this.accessCount = accessCount;
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
}
