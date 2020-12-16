package logic.persistence;

import logic.BusinessLogic;
import models.storage.Storage;
import models.storage.StorageContent;

import java.io.*;

public class Persistence {
    private BusinessLogic businessLogic;

    public Persistence(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public boolean save(String path, Storage storage) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(storage);
        oos.flush();
        if (oos != null) {
            oos.close();
        }
        if (fos != null) {
            fos.close();
        }
        return true;
    }

    public Object load(String path) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Storage tempStorage = null;
        if ((ois.readObject() instanceof StorageContent) || (ois.readObject() instanceof Storage)) {
            tempStorage = (StorageContent) ois.readObject();
        }

        if (ois != null) {
            ois.close();
        }

        if (fis != null) {
            fis.close();
        }

        return tempStorage;
    }
}
