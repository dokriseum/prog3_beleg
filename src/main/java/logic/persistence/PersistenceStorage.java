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

    @Override
    public boolean saveJOS() throws IOException {
        String path = ".storage/storage.file";
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.businessLogic.getStorage());
        if (oos != null) {
            oos.close();
        }
        if (fos != null) {
            fos.close();
        }
        return true;
    }

    @Override
    public boolean loadJOS() throws IOException, ClassNotFoundException {
        String path = ".storage/storage.file";
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

    @Override
    public boolean saveJBP() throws IOException {
        String path = ".storage/storage.file";
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.businessLogic.getStorage());
        if (oos != null) {
            oos.close();
        }
        if (fos != null) {
            fos.close();
        }
        return true;
    }

    @Override
    public boolean loadJBP() throws IOException, ClassNotFoundException {
        String path = ".storage/storage.file";
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

    @Override
    public boolean saveContent(String address) throws IOException {
        String path = ".storage/content/" + address + ".file";
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Content content = this.businessLogic.getContent(address);
        oos.writeObject(content);
        if (oos != null) {
            oos.close();
        }
        if (fos != null) {
            fos.close();
        }
        return true;
    }

    @Override
    public boolean loadContent(String address) throws IOException, ClassNotFoundException {
        String path = ".storage/content/" + address + ".file";
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Content c = (Content) ois.readObject();

        try {
            this.businessLogic.uploadContent(c);
        } catch (SizeReachedException e) {
            e.printStackTrace();
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
