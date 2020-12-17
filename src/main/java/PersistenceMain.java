import logic.BusinessLogic;
import logic.dummies.DummyContentAdder;
import logic.persistence.PersistenceBusinessLogic;
import models.mediaDB.Content;
import models.storage.StorageContent;

import java.math.BigDecimal;

public class PersistenceMain {
    public static void main(String[] args) {
        try {
            System.out.println("######################################################################");
            System.out.println("######################################################################");
            System.out.println("####################        Output Stream         ####################");
            System.out.println("######################################################################");
            System.out.println("######################################################################");
            System.out.println();
            BusinessLogic bl = new BusinessLogic(new StorageContent(BigDecimal.valueOf(Long.parseLong("9876"))));
            PersistenceBusinessLogic pbl = new PersistenceBusinessLogic(bl);
            DummyContentAdder dummy = new DummyContentAdder(bl);
            Boolean run = true;
            BigDecimal size = BigDecimal.ZERO;

            do {
                dummy.addDummy();
                if (size.equals(bl.getStorageActuallySize())) {
                    run = false;
                } else {
                    size = bl.getStorageActuallySize();
                }
            }
            while (run);

            for (Content k : bl.getStorage().getListContent()) {
                System.out.println(k.toString());
            }
            System.out.println("######################################################################");
            System.out.println("######################################################################");
            System.out.println("####################         Input Stream         ####################");
            System.out.println("######################################################################");
            System.out.println("######################################################################");
            System.out.println();

            pbl.save("daten.file");
            //bl.saveStorage("storage001.txt");

            //BusinessLogic bl2 = new BusinessLogic();
            //bl.loadStorage("storage001.txt");
            BusinessLogic bl2 = pbl.load("daten.file");
            for (Content k : bl2.getStorage().getListContent()) {
                System.out.println(k.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
