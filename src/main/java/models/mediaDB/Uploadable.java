package models.mediaDB;

import java.io.Serializable;
import java.util.Date;

public interface Uploadable extends Serializable {
    Uploader getUploader();

    Date getUploadDate();
}
