package models.mediaDB;

import java.io.Serializable;
import java.util.Collection;

public interface Content extends Serializable {
    String getAddress();

    Collection<Tag> getTags();

    long getAccessCount();
}
