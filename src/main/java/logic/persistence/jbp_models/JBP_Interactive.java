/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.persistence.jbp_models;

import models.mediaDB.Interactive;
import models.mediaDB.Tag;

import java.util.Collection;

public class JBP_Interactive extends PersistenceItem {
    private String address;
    private Collection<Tag> tags;
    private long accessCount;
    private String type;

    public JBP_Interactive(Interactive interactive) {
        this.address = interactive.getAddress();
        this.tags = interactive.getTags();
        this.accessCount = interactive.getAccessCount();
        this.type = interactive.getType();
    }

    public JBP_Interactive(String address, Collection<Tag> tags, long accessCount, String type) {
        this.address = address;
        this.tags = tags;
        this.accessCount = accessCount;
        this.type = type;
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

    public String getType() {
        return type;
    }
}
