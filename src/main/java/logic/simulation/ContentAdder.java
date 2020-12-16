/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.simulation;

import exceptions.SizeReachedException;
import logic.BusinessLogic;
import models.mediaDB.Tag;
import models.mediaDB.Uploader;
import models.mediaDB.UploaderImpl;
import models.storage.MediaType;

import java.time.Duration;
import java.util.*;

public class ContentAdder implements Runnable {
    private BusinessLogic businessLogic;
    private Collection<Tag> tags;
    private Collection<MediaType> mediaTypes;
    private Collection<Uploader> uploaders;
    private Collection<String> types;
    private Collection<String> holders;
    private Map<String, List<Integer>> mapResolution;
    private Random random;

    private MediaType randomMediaType;
    private int randomSamplingRate, randomWidth, randomHeight;
    private String randomEncording, randomHolder, randomType;
    private long randomBitrate, randomAccessCount;
    private Duration randomLength;
    private Collection<Tag> randomTags;
    private Uploader randomUploader;

    public ContentAdder(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
        this.tags = new ArrayList<>();
        this.mediaTypes = new ArrayList<>();
        this.uploaders = new ArrayList<>();
        this.types = new ArrayList<>();
        this.holders = new ArrayList<>();
        this.mapResolution = new HashMap<>();
        this.random = new Random();
        this.fillCollections();
        this.fillMapResolution();
    }

    @Override
    public void run() {
        while (true) {
            this.tryUploadContent();
        }
    }

    private void tryUploadContent() {
        try {
            synchronized (this.businessLogic.getStorage()) {
                this.uploadRandomContent();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void uploadRandomContent() throws InterruptedException {
        //System.out.println("ADD " + businessLogic.getStorage());
        this.fillParametersForContent();
        try {
            businessLogic.uploadContent(randomMediaType,
                    randomSamplingRate,
                    randomWidth,
                    randomHeight,
                    randomEncording,
                    randomHolder,
                    randomBitrate,
                    randomLength,
                    randomTags,
                    randomAccessCount,
                    randomUploader,
                    (new Date()),
                    randomType);
            System.out.println("ADD: " + businessLogic.getStorage().getListContent().get(businessLogic.getStorage().getListContent().size() - 1).toString());

        } catch (SizeReachedException e) {
            System.err.println("FULL: " + this.businessLogic.getStorage().getSize() + '/' + this.businessLogic.getStorage().getCapacity());
        }
        this.businessLogic.getStorage().notify();
        this.businessLogic.getStorage().wait();
    }

    private void fillParametersForContent() {
        randomMediaType = ((List<MediaType>) mediaTypes).get(this.random.nextInt(2));


        int indexWhile = 0;
        int randomCounterIndexWhile = this.random.nextInt(this.tags.size());
        Tag randomTag;
        randomTags = new ArrayList<>();
        do {
            randomTag = ((List<Tag>) this.tags).get(random.nextInt(this.tags.size()));
            if (!(randomTags.contains(randomTag))) {
                randomTags.add(randomTag);
            }
            indexWhile++;
        } while (indexWhile <= randomCounterIndexWhile);

        /**
         * Object source,
         *                           String text,
         *                           MediaType eventMediaType,      L1      I1
         *                           int eventSamplingRate,         L10
         *                           int eventWidth,                L8      I8
         *                           int eventHeight,               L7      I7
         *                           String eventEncording,         L6      I6
         *                           String eventHolder,            L11
         *                           long eventBitrate,             L4      I4
         *                           Duration eventLength,          L5      I5
         *                           BigDecimal eventSize,          Lx      Ix
         *                           String eventAddress,           Lx      Ix
         *                           Collection<Tag> eventTags,     L3      I3
         *                           long eventAccessCount,         Lx      Ix
         *                           Uploader eventUploader,        L2      I2
         *                           Date eventUploadDate,          Lx      Ix
         *                           String eventType)                      I9
         *
         */

        int indexForMapResolution = this.random.nextInt(5);
        if (randomMediaType.equals(MediaType.InteractiveVideo)) {
            randomSamplingRate = 0;
            randomWidth = this.mapResolution.get("width").get(indexForMapResolution);
            randomHeight = this.mapResolution.get("height").get(indexForMapResolution);
            randomEncording = "ABC";
            randomHolder = null;
            randomBitrate = 14800;
            randomLength = Duration.ofSeconds(this.random.nextInt(3600));
            randomAccessCount = 0;
            randomUploader = ((List<Uploader>) this.uploaders).get(this.random.nextInt(this.uploaders.size()));
            randomType = ((List<String>) this.types).get(this.random.nextInt(this.types.size()));
        } else if (randomMediaType.equals(MediaType.LicensedAudioVideo)) {
            randomSamplingRate = this.random.nextInt(128000);
            randomWidth = this.mapResolution.get("width").get(indexForMapResolution);
            randomHeight = this.mapResolution.get("height").get(indexForMapResolution);
            randomEncording = "DEF";
            randomHolder = ((List<String>) this.holders).get(this.random.nextInt(this.holders.size()));
            randomBitrate = this.random.nextInt(14800);
            randomLength = Duration.ofSeconds(this.random.nextInt(3600));
            randomAccessCount = 0;
            randomUploader = ((List<Uploader>) this.uploaders).get(this.random.nextInt(this.uploaders.size()));
            randomType = null;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void fillCollections() {
        this.tags.add(Tag.Lifestyle);
        this.tags.add(Tag.Animal);
        this.tags.add(Tag.News);
        this.tags.add(Tag.Tutorial);

        this.mediaTypes.add(MediaType.InteractiveVideo);
        this.mediaTypes.add(MediaType.LicensedAudioVideo);

        this.holders.add("AMIGA");
        this.holders.add("Eterna");
        this.holders.add("Litera");
        this.holders.add("Nova");
        this.holders.add("Deutsche Musik AG");

        this.uploaders.add(new UploaderImpl("Wolfgang Petry"));
        this.uploaders.add(new UploaderImpl("Unheilig"));
        this.uploaders.add(new UploaderImpl("Mono Inc"));
        this.uploaders.add(new UploaderImpl("Johanna von Koczian"));
        this.uploaders.add(new UploaderImpl("Helga Hahnemann"));
        this.uploaders.add(new UploaderImpl("Karat"));
        this.uploaders.add(new UploaderImpl("Silly"));
        this.uploaders.add(new UploaderImpl("Puhdys"));
        this.uploaders.add(new UploaderImpl("Vicky Leandros"));
        this.uploaders.add(new UploaderImpl("Ute Freudenberg"));

        this.types.add("Abstimmung");
        this.types.add("Lernen und Lehren");
    }

    private void fillMapResolution() {
        List<Integer> listWidth = new ArrayList<>();
        List<Integer> listHeight = new ArrayList<>();
        listWidth.add(640);
        listWidth.add(1280);
        listWidth.add(1600);
        listWidth.add(1920);
        listWidth.add(2560);
        listWidth.add(3840);
        listHeight.add(360);
        listHeight.add(720);
        listHeight.add(900);
        listHeight.add(1080);
        listHeight.add(1440);
        listHeight.add(2160);

        this.mapResolution.put("width", listWidth);
        this.mapResolution.put("height", listHeight);
    }

    public BusinessLogic getBusinessLogic() {
        return this.businessLogic;
    }
}
