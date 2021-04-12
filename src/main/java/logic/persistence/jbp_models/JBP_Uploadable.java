/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.persistence.jbp_models;

import models.mediaDB.Uploadable;

public class JBP_Uploadable extends PersistenceItem {
    private String uploader;
    private long upload_date;

    public JBP_Uploadable() {
    }

    public JBP_Uploadable(Uploadable u) {
        this.uploader = u.getUploader().getName();
        this.upload_date = u.getUploadDate().getTime();
    }

    public JBP_Uploadable(String uploader, long upload_date) {
        this.uploader = uploader;
        this.upload_date = upload_date;
    }

    public String getUploader() {
        return this.uploader;
    }

    public long getUploadDate() {
        return this.upload_date;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public void setUploadDate(long upload_date) {
        this.upload_date = upload_date;
    }
}
