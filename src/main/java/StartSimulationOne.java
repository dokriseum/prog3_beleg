import logic.BusinessLogic;
import logic.observer.Observer;
import logic.observer.simulation.CheckSimulationObserver;
import logic.simulation.ContentAdder;
import logic.simulation.ContentDeleter;
import models.storage.StorageContent;

import java.io.OutputStreamWriter;
import java.math.BigDecimal;

/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

public class StartSimulationOne {
    private static BusinessLogic businessLogic = new BusinessLogic(new StorageContent(new BigDecimal("5432")));

    private static ContentAdder objectContentAdder = new ContentAdder(businessLogic);
    private static ContentDeleter objectContentDeleter = new ContentDeleter(businessLogic);

    private static Observer checkSimulationObserver;

    private static Thread threadAdder = new Thread(objectContentAdder);
    private static Thread threadDeleter = new Thread(objectContentDeleter);

    public static void main(String[] args) {
        checkSimulationObserver = new CheckSimulationObserver(businessLogic, new OutputStreamWriter(System.out));
        threadDeleter.start();
        threadAdder.start();

    }
}
