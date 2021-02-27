package logic.persistence;

import exceptions.SizeReachedException;
import logic.BusinessLogic;
import models.mediaDB.Content;
import models.storage.Storage;

import java.io.*;

public class PersistenceStorage implements Persistence, Serializable {
    private BusinessLogic businessLogic;

    public PersistenceStorage(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public boolean save(PersistenceType type, Storage storage) throws IOException {
        String path;
        if (type.name().equals(PersistenceType.SAVE_JOS)) {
            path = "storage.file";
        } else if (type.name().equals(PersistenceType.SAVE_JBP)) {
            path = "storage.jbp";
        } else {
            return false;
        }
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(storage);
        if (oos != null) {
            oos.close();
        }
        if (fos != null) {
            fos.close();
        }
        return true;
//        FileOutputStream fos = new FileOutputStream(path);
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//
//        oos.writeObject(storage);
//        if (oos != null) {
//            oos.close();
//        }
//        if (fos != null) {
//            fos.close();
//        }
//        return true;
    }

    public boolean load(PersistenceType type) throws IOException, ClassNotFoundException {
        String path;
        if (type.name().equals(PersistenceType.LOAD_JOS)) {
            path = "storage.file";
        } else if (type.name().equals(PersistenceType.LOAD_JBP)) {
            path = "storage.jbp";
        } else {
            return false;
        }
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Storage tempStorage = (Storage) ois.readObject();

        for (Content k : tempStorage.getListContent()) {
            try {
                this.businessLogic.uploadContent(k);
            } catch (SizeReachedException e) {
                e.printStackTrace();
            }
        }

        if (ois != null) {
            ois.close();
        }

        if (fis != null) {
            fis.close();
        }
        return true;
    }
}
