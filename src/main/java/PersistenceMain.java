import logic.BusinessLogic;
import logic.dummies.DummyContentAdder;
import models.storage.StorageContent;

import java.math.BigDecimal;

public class PersistenceMain {
    public static void main(String[] args) {
        BusinessLogic bl = new BusinessLogic(new StorageContent(BigDecimal.valueOf(Long.parseLong("1234"))));
        DummyContentAdder dummy = new DummyContentAdder(bl);
        Boolean run = true;
        BigDecimal size = bl.getStorageActuallySize();
        do {
            if (size.compareTo(bl.getStorageActuallySize()) > 0) {
                System.out.println("Before: " + size);
                dummy.addDummy();
                size = bl.getStorageActuallySize();
                System.out.println("After: " + size);
            } else {
                run = false;
            }
        } while (run);
    }
}
