/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package models.storage;

import models.mediaDB.Uploader;

import java.util.ArrayList;
import java.util.List;

public class UploaderStorage {
    private List<Uploader> listUploader = new ArrayList<>();

    public UploaderStorage() {
    }

    public boolean addUploader(Uploader uploader) {
        if (uploader == null) {
            throw new NullPointerException();
        }

        boolean existent = searchDoubleName(uploader.getName());
        if (existent == true) {
            throw new IllegalArgumentException("uploader is existent");
        }

        listUploader.add(uploader);
        return true;
    }

    public boolean deleteUploader(Uploader uploader) {
        if (uploader == null) {
            throw new NullPointerException();
        }
        listUploader.remove(uploader);
        return true;
    }

    public boolean deleteUploader(int index) throws NullPointerException {
        if (index < 0) {
            throw new NullPointerException();
        }
        listUploader.remove(index);
        return true;
    }

    public boolean editUploader(Uploader oldUploader, Uploader newUploader) {
        if (oldUploader == null || newUploader == null) {
            return false;
        }
        int index = listUploader.indexOf(oldUploader);
        if (index < 0) {
            return false;
        }

        listUploader.set(index, newUploader);
        return true;
    }

    public boolean editUploader(int index, Uploader newUploader) {
        if (newUploader == null) {
            return false;
        }

        if (index < 0 || index > listUploader.size()) {
            return false;
        }

        listUploader.set(index, newUploader);
        return true;
    }

    public Uploader getUploader(int index) {
        return listUploader.get(index);
    }

    public List<Uploader> getUploaderList() {
        return listUploader;
    }

    //prueft, ob name schon vorhanden ist
    private boolean searchDoubleName(String name) {
        boolean existent = false;
        for (Uploader uploader : listUploader) {
            if (uploader.getName().equalsIgnoreCase(name)) {
                existent = true;
            }
        }
        return existent;
    }
}
