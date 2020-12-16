/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package models.storage;

import java.io.Serializable;
import java.math.BigDecimal;

public interface Storage extends Serializable {
    BigDecimal getCapacity();

    long getAccessCount();
}
