import logic.BusinessLogic;
import logic.persistence.PersistenceBusinessLogic;
import models.mediaDB.Content;

public class PersistenceMain2 {
    public static void main(String[] args) {
        try {
            System.out.println("###");
            PersistenceBusinessLogic pbl = new PersistenceBusinessLogic();
            BusinessLogic bl2 = pbl.load("daten.daten");
            for (Content k : bl2.getStorage().getListContent()) {
                System.out.println(k.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
