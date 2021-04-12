package logic;

import exceptions.SizeReachedException;
import logic.observer.Observer;
import models.mediaDB.UploaderImpl;
import models.storage.MediaType;
import models.storage.StorageContent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

import static org.mockito.Mockito.*;

class BusinessLogicTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    void testGoodInsertData1() {
        BusinessLogic bl = new BusinessLogic(new StorageContent(new BigDecimal("123456")));
        try {
            bl.uploadContent(
                    MediaType.LicensedAudioVideo, 1, 1, 1, "ABC", "CBS", 2, Duration.ofSeconds(231), null, (new UploaderImpl("UPU")), "Abstimmung");
            Assertions.assertEquals(1, bl.getFiles().size());
        } catch (SizeReachedException e) {
            Assertions.fail();
        }
    }

    @Test
    void testGoodDeleteData1() {
        BusinessLogic bl = new BusinessLogic(new StorageContent(new BigDecimal("123456")));
        try {
            bl.uploadContent(
                    MediaType.LicensedAudioVideo, 1, 1, 1, "ABC", "CBS", 2, Duration.ofSeconds(231), null, (new UploaderImpl("UPU")), "Abstimmung");
        } catch (SizeReachedException e) {
            Assertions.fail();
        }
        String address = bl.getFile(0).getAddress();
        bl.deleteContent(address);
        Assertions.assertEquals(0, bl.getFiles().size());
    }

    @Test
    void testBadNotInsertByFullStorage1() {
        BusinessLogic bl = new BusinessLogic(new StorageContent(new BigDecimal("1")));
        Assertions.assertThrows(SizeReachedException.class, () -> {
            bl.uploadContent(
                    MediaType.LicensedAudioVideo, 1, 1, 1, "ABC", "CBS", 2, Duration.ofSeconds(231), null, (new UploaderImpl("UPU")), "Abstimmung");

        });
    }

    @Test
    void testObserverUpdate() {
        BusinessLogic mockBusinessLogic = new BusinessLogic(new StorageContent(new BigDecimal("123456")));
        Observer observer = mock(Observer.class);

        mockBusinessLogic.addObserver(observer);
        try {
            mockBusinessLogic.uploadContent(
                    MediaType.LicensedAudioVideo, 1, 1, 1, "ABC", "CBS", 2, Duration.ofSeconds(231), null, (new UploaderImpl("UPU")), "Abstimmung");
        } catch (SizeReachedException e) {
            e.printStackTrace();
        }

        verify(observer).update();
    }

    @Test
    void testObserverDeregistration() {
        BusinessLogic mockBusinessLogic = new BusinessLogic(new StorageContent(new BigDecimal("123456")));
        Observer observer = mock(Observer.class);

        mockBusinessLogic.addObserver(observer);
        mockBusinessLogic.removeObserver(observer);
        try {
            mockBusinessLogic.uploadContent(
                    MediaType.LicensedAudioVideo, 1, 1, 1, "ABC", "CBS", 2, Duration.ofSeconds(231), null, (new UploaderImpl("UPU")), "Abstimmung");
        } catch (SizeReachedException e) {
            e.printStackTrace();
        }

        verify(observer, never()).update();
    }

    @AfterEach
    void tearDown() {
    }
}