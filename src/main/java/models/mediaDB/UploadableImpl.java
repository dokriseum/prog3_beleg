/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package models.mediaDB;

import java.util.Date;

public class UploadableImpl implements Uploadable {
    @Override
    public Uploader getUploader() {
        return null;
    }

    @Override
    public Date getUploadDate() {
        return null;
    }
}
