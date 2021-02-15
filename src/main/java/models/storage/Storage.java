/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package models.storage;

import models.mediaDB.Content;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface Storage extends Serializable {
    Map<MediaType, List<Content>> getMapMediaTypeContent();

    Map<String, Content> getMapAddressContent();

    List<Content> getListContent();

    BigDecimal getCapacity();

    BigDecimal getSize();

    long getAccessCount();
}
