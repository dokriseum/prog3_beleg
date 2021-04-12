package logic.persistence;

import exceptions.SizeReachedException;
import logic.BusinessLogic;
import models.mediaDB.UploaderImpl;
import models.storage.MediaType;
import models.storage.StorageContent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;

class PersistenceTest {

    @Test
    void testGood_JOS_SaveAndLoadStorage() {
        BusinessLogic bl1 = new BusinessLogic(new StorageContent(new BigDecimal("123456")));
        BusinessLogic bl2 = new BusinessLogic(new StorageContent(new BigDecimal("123456")));
        PersistenceStorage persist1 = new PersistenceStorage(bl1);
        PersistenceStorage persist2 = new PersistenceStorage(bl2);

        try {
            bl1.uploadContent(
                    MediaType.LicensedAudioVideo, 1, 1, 1, "ABC", "CBS", 2, Duration.ofSeconds(231), null, (new UploaderImpl("UPU")), "Abstimmung");
        } catch (SizeReachedException e) {
            Assertions.fail();
        }
        try {
            persist1.saveJOS();
            if (!bl2.getStorage().getListContent().isEmpty()) {
                Assertions.fail();
            }
            persist2.loadJOS();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertFalse(bl2.getStorage().getListContent().isEmpty());
        Assertions.assertEquals(1, bl2.getStorage().getListContent().size());
    }

    @Test
    void testGood_SaveAndLoadContent() {
        BusinessLogic bl1 = new BusinessLogic(new StorageContent(new BigDecimal("123456")));
        BusinessLogic bl2 = new BusinessLogic(new StorageContent(new BigDecimal("123456")));
        PersistenceStorage persist1 = new PersistenceStorage(bl1);
        PersistenceStorage persist2 = new PersistenceStorage(bl2);
        String address;
        try {
            bl1.uploadContent(
                    MediaType.LicensedAudioVideo, 1, 1, 1, "ABC", "CBS", 2, Duration.ofSeconds(231), null, (new UploaderImpl("UPU")), "Abstimmung");
        } catch (SizeReachedException e) {
            Assertions.fail();
        }
        address = bl1.getFile(0).getAddress();
        try {
            persist1.saveContent(address);
            if (!bl2.getStorage().getListContent().isEmpty()) {
                Assertions.fail();
            }
            persist2.loadContent(address);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(address, bl2.getFile(0).getAddress());
    }

}