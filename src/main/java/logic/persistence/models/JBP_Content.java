/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.persistence.models;

import models.mediaDB.Content;
import models.mediaDB.Tag;

import java.util.Collection;

public class JBP_Content extends PersistenceItem {
    private String address;
    private Collection<Tag> tags;
    private long accessCount;

    public JBP_Content(Content content) {
        this.address = content.getAddress();
        this.tags = content.getTags();
        this.accessCount = content.getAccessCount();
    }

    public JBP_Content(String address, Collection<Tag> tags, long accessCount) {
        this.address = address;
        this.tags = tags;
        this.accessCount = accessCount;
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
}
