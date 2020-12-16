package logic.observer;

import logic.BusinessLogic;
import models.storage.StorageContent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

class CheckSizePerCentObserverTest {
    private StorageContent testStorageContent;
    private BusinessLogic testBusinessLogic;
    private Observer testObserver;

    @BeforeEach
    void initObjects() {
        testStorageContent = new StorageContent(new BigDecimal("55078"));
        testBusinessLogic = new BusinessLogic(testStorageContent);
        testObserver = new CheckSizePerCentObserver(testBusinessLogic);
    }

    @Test
    void testGoodObserverUpdateMessage1() {
        BusinessLogic mockBusinessLogic = mock(BusinessLogic.class);
        Observer observer = new CheckSizePerCentObserver(mockBusinessLogic);

        observer.update();

        verify(mockBusinessLogic, times(1)).addObserver(any());
        verify(mockBusinessLogic, atLeastOnce()).observeIsSizeMoreAsNinetyPercent();
        verifyNoMoreInteractions(mockBusinessLogic);
    }

    @Test
    void testConstructor() {
        //SETUP//
        //TEST//
        try {
            Observer ov = new CheckSizePerCentObserver(testBusinessLogic);
        } catch (Exception e) {
            Assertions.fail("Exception during test : " + e.getMessage());
        }
    }

}