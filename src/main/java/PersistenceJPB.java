import exceptions.SizeReachedException;
import logic.BusinessLogic;
import logic.persistence.PersistenceXML;
import models.mediaDB.Content;
import models.mediaDB.Tag;
import models.mediaDB.UploaderImpl;
import models.storage.MediaType;
import models.storage.StorageContent;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

public class PersistenceJPB {
    public static void main(String[] args) {

        BusinessLogic bl = new BusinessLogic(new StorageContent(new BigDecimal("123456")));
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.Lifestyle);
        tags.add(Tag.Animal);
        try {
            bl.uploadContent(
                    MediaType.LicensedAudioVideo, 1, 1, 1, "ABC", "CBS", 2, Duration.ofSeconds(231), tags, (new UploaderImpl("UPU")), "Abstimmung");
        } catch (SizeReachedException e) {
            Assertions.fail();
        }
        Content c = bl.getFile(0);
        System.out.println(c.toString());
        PersistenceXML persistenceXML = new PersistenceXML();
        persistenceXML.save(c, "c");
    }
}
