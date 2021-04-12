/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.persistence.jbp_models;

import models.mediaDB.Licensed;
import models.mediaDB.Tag;

import java.util.Collection;

public class JBP_Licensed extends PersistenceItem {
    private String address;
    private Collection<Tag> tags;
    private long accessCount;
    private String holder;

    public JBP_Licensed() {
    }

    public JBP_Licensed(Licensed licensed) {
        this.address = licensed.getAddress();
        this.tags = licensed.getTags();
        this.accessCount = licensed.getAccessCount();
        this.holder = licensed.getHolder();
    }

    public JBP_Licensed(String address, Collection<Tag> tags, long accessCount, String holder) {
        this.address = address;
        this.tags = tags;
        this.accessCount = accessCount;
        this.holder = holder;
    }

    public String getHolder() {
        return holder;
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

    public void setHolder(String holder) {
        this.holder = holder;
    }
}
