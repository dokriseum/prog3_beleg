/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package models.mediaDB;

import java.util.Collection;

public class LicensedImpl implements Licensed {
    private String address;
    private Collection<Tag> tags;
    private long accessCount;
    private String holder;

    public LicensedImpl() {
    }

    public LicensedImpl(String address, Collection<Tag> tags, long accessCount, String holder) {
        this.address = address;
        this.tags = tags;
        this.accessCount = accessCount;
        this.holder = holder;
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
    public String getHolder() {
        return holder;
    }
}
