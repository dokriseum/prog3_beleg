package logic.persistence;

import exceptions.SizeReachedException;
import logic.BusinessLogic;
import logic.event.InputEventPersistence;
import logic.event.listener.InputEventListenerPersistence;
import models.mediaDB.UploaderImpl;
import models.storage.MediaType;
import models.storage.StorageContent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

class PersistenceTest {

    @Test
    void testGoodSaveStorage() {
        BusinessLogic bl1 = new BusinessLogic(new StorageContent(new BigDecimal("123456")));
        BusinessLogic bl2 = new BusinessLogic(new StorageContent(new BigDecimal("123456")));
        InputEventListenerPersistence listener1 = new InputEventListenerPersistence(bl1);
        listener1.onInputEvent(new InputEventPersistence(this, "InputEventPersistence", PersistenceType.SAVE_JOS));

        try {
            bl1.uploadContent(
                    MediaType.LicensedAudioVideo, 1, 1, 1, "ABC", "CBS", 2, Duration.ofSeconds(231), null, (new UploaderImpl("UPU")), "Abstimmung");
        } catch (SizeReachedException e) {
            Assertions.fail();
        }
        InputEventListenerPersistence listener2 = new InputEventListenerPersistence(bl2);
        listener2.onInputEvent(new InputEventPersistence(this, "InputEventPersistence", PersistenceType.LOAD_JOS));
        //Assertions.assertFalse(bl2.getStorage().getListContent().isEmpty());
        System.out.println(bl2.getFiles());
    }

}