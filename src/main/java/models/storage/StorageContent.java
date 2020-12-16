/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package models.storage;

import exceptions.SizeReachedException;
import models.mediaDB.Content;
import models.mediaDB.MediaContent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageContent implements Storage, Serializable {
    private BigDecimal capacity;
    private List<Content> listContent;
    private long accessCount;
    private Map<MediaType, List<Content>> mapMediaTypeContent;
    private Map<String, Content> mapAddressContent;
    private BigDecimal size;


    public StorageContent(BigDecimal capacity) {
        this.capacity = capacity;
        this.listContent = new ArrayList<>();
        this.accessCount = 0;
        this.mapMediaTypeContent = new HashMap<>();
        this.mapAddressContent = new HashMap<>();
        this.size = BigDecimal.ZERO;
    }

    public boolean checkCapacityReached() throws SizeReachedException {
        BigDecimal tmpStorageSize = new BigDecimal(0);

        for (Content k : this.getListContent()) {
            tmpStorageSize.add(((MediaContent) k).getSize());
        }

        if (tmpStorageSize.compareTo(this.capacity) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public Map<MediaType, List<Content>> getMapMediaTypeContent() {
        return mapMediaTypeContent;
    }

    public Map<String, Content> getMapAddressContent() {
        return mapAddressContent;
    }

    public List<Content> getListContent() {
        return listContent;
    }

    @Override
    public BigDecimal getCapacity() {
        return this.capacity;
    }

    public BigDecimal getSize() {
        this.size = BigDecimal.ZERO;
        for (Content k : this.getListContent()) {
            size = size.add(((MediaContent) k).getSize());
        }
        return this.size;
    }

    @Override
    public long getAccessCount() {
        return accessCount;
    }

    public boolean checkIsSizeReached(Content content) {
        int checked = this.getSize().compareTo(this.getSize().add(((MediaContent) content).getSize()));
        if (checked >= 0) {
            return false;
        } else {
            return true;
        }
    }
}
