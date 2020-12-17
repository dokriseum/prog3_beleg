package logic.persistence;

import logic.BusinessLogic;

import java.io.*;

public class PersistenceBusinessLogic {
    private BusinessLogic businessLogic;

    public PersistenceBusinessLogic() {
        this.businessLogic = new BusinessLogic();
    }

    public PersistenceBusinessLogic(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public boolean save(String path) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(this.businessLogic);
        if (oos != null) {
            oos.close();
        }
        if (fos != null) {
            fos.close();
        }
        return true;
    }

    public BusinessLogic load(String path) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.businessLogic = (BusinessLogic) ois.readObject();

        if (ois != null) {
            ois.close();
        }

        if (fis != null) {
            fis.close();
        }

        return this.businessLogic;
    }
}
