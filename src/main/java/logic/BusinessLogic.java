/*
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic;

import exceptions.SizeReachedException;
import logic.observer.Observable;
import logic.observer.Observer;
import logic.utils.MemberChanger;
import models.mediaDB.*;
import models.storage.MediaType;
import models.storage.StorageContent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

public class BusinessLogic implements Observable, Serializable {
    private StorageContent storage;
    private List<Uploader> listUploader;
    private long addressCounter;
    private List<Observer> observers;

    public BusinessLogic() {
        this.storage = new StorageContent(new BigDecimal("1"));
        this.listUploader = new ArrayList<>();
        addressCounter = 0;
        observers = new LinkedList<>();
        Content content = new MediaContentImpl(2, Duration.ofSeconds(234), BigDecimal.ZERO, null, null, 2);
        this.storage.getListContent().add(content);
    }

    public BusinessLogic(StorageContent storage) {
        if (storage == null) {
            throw new IllegalArgumentException("storage must not nullable");
        }
        this.storage = storage;
        this.listUploader = new ArrayList<>();
        this.addressCounter = 0;
        this.observers = new LinkedList<>();
    }

    public BusinessLogic(BigDecimal size) {
        this.storage = new StorageContent(size);
        this.listUploader = new ArrayList<>();
        this.addressCounter = 0;
        this.observers = new LinkedList<>();
    }

    /*
     * CRUD
     */

    public boolean uploadContent(Content content) throws SizeReachedException {
        String address = this.generateAddress(content.getAddress());
        try {
            MemberChanger.writeString(content, "address", address);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        boolean checked = this.checkIsSizeReached(content);

        if (checked) {
            throw new SizeReachedException("capacity is reached");
        }
        MediaType mediaType;

        if (content instanceof InteractiveVideo) {
            mediaType = MediaType.InteractiveVideo;
        } else if (content instanceof LicensedAudioVideo) {
            mediaType = MediaType.LicensedAudioVideo;
        } else if (content instanceof LicensedVideo) {
            mediaType = MediaType.LicensedVideo;
        } else if (content instanceof LicensedAudio) {
            mediaType = MediaType.LicensedAudio;
        } else if (content instanceof AudioVideo) {
            mediaType = MediaType.AudioVideo;
        } else if (content instanceof Video) {
            mediaType = MediaType.Video;
        } else if (content instanceof Audio) {
            mediaType = MediaType.Audio;
        } else {
            throw new IllegalArgumentException("MediaType was not found");
        }

        this.getStorage().getListContent().add(content);
        if (this.getStorage().getMapMediaTypeContent().get(mediaType) == null) {
            List<Content> tmpList = new ArrayList<>();
            tmpList.add(content);
            this.getStorage().getMapMediaTypeContent().put(mediaType, tmpList);
        } else {
            this.getStorage().getMapMediaTypeContent().get(mediaType).add(content);
        }
        this.getStorage().getMapAddressContent().put(address, content);
        this.notifyObserver();
        return true;
    }

    public boolean uploadContent(MediaType mediaType, int samplingRate, int width, int height, String encoding, String holder, long bitrate, Duration length, Collection<Tag> tags, Uploader uploader, String type) throws IllegalArgumentException, IndexOutOfBoundsException, SizeReachedException {
        Content content;

        String address = this.generateAddress(mediaType, uploader.getName(), width, height);
        BigDecimal size = this.generateSize(samplingRate, bitrate, width, height);

        switch (mediaType) {
            case InteractiveVideo:
                content = new InteractiveVideoImpl(width, height, encoding, bitrate, length, size, address, tags, this.addUploaderForContent(uploader.getName()), type);
                break;
            case LicensedAudioVideo:
                content = new LicensedAudioVideoImpl(samplingRate, width, height, encoding, holder, bitrate, length, size, address, tags, this.addUploaderForContent(uploader.getName()));
                break;
            case LicensedVideo:
                content = new LicensedVideoImpl(width, height, encoding, holder, bitrate, length, size, address, tags, this.addUploaderForContent(uploader.getName()));
                break;
            case LicensedAudio:
                content = new LicensedAudioImpl(samplingRate, encoding, holder, bitrate, length, size, address, tags, this.addUploaderForContent(uploader.getName()));
                break;
            case AudioVideo:
                content = new AudioVideoImpl(samplingRate, width, height, encoding, bitrate, length, size, address, tags, this.addUploaderForContent(uploader.getName()));
                break;
            case Video:
                content = new VideoImpl(width, height, encoding, bitrate, length, size, address, tags, this.addUploaderForContent(uploader.getName()));
                break;
            case Audio:
                content = new AudioImpl(samplingRate, encoding, bitrate, length, size, address, tags, this.addUploaderForContent(uploader.getName()));
                break;
            default:
                throw new IllegalArgumentException("MediaType was not found");
        }

        boolean checked = this.checkIsSizeReached(content);

        if (checked) {
            throw new SizeReachedException("capacity is reached");
        }

        this.getStorage().getListContent().add(content);
        if (this.getStorage().getMapMediaTypeContent().get(mediaType) == null) {
            List<Content> tmpList = new ArrayList<>();
            tmpList.add(content);
            this.getStorage().getMapMediaTypeContent().put(mediaType, tmpList);
        } else {
            this.getStorage().getMapMediaTypeContent().get(mediaType).add(content);
        }
        this.getStorage().getMapAddressContent().put(address, content);
        this.notifyObserver();
        return true;
    }

    public boolean editContent(String oldContentAddress, int samplingRate, int width, int height, String encoding, String holder, long bitrate, Duration length, Collection<Tag> tags, String uploader, String type) throws IllegalArgumentException, IndexOutOfBoundsException, SizeReachedException {
        Content oldContent = this.storage.getMapAddressContent().get(oldContentAddress);
        Content newContent;
        MediaType mediaType;
        if (oldContent instanceof LicensedAudioVideo) {
            mediaType = MediaType.LicensedAudioVideo;
        } else if (oldContent instanceof InteractiveVideo) {
            mediaType = MediaType.InteractiveVideo;
        } else {
            throw new IllegalArgumentException("Illegal media type!");
        }
        String address = this.generateAddress(mediaType, uploader, width, height);
        BigDecimal size = this.generateSize(samplingRate, bitrate, width, height);

        if (mediaType.equals(MediaType.InteractiveVideo)) {
            newContent = new InteractiveVideoImpl(width, height, encoding, bitrate, length, size, address, tags, this.addUploaderForContent(uploader), type);
        } else if (mediaType.equals(MediaType.LicensedAudioVideo)) {
            newContent = new LicensedAudioVideoImpl(samplingRate, width, height, encoding, holder, bitrate, length, size, address, tags, this.addUploaderForContent(uploader));
        } else {
            throw new IllegalArgumentException("MediaType was not found");
        }

        boolean checked = this.checkIsSizeReached(newContent);

        if (checked) {
            throw new SizeReachedException("capacity is reached");
        }
        int indexOldContent = 0;
        for (Content k : this.storage.getMapMediaTypeContent().get(mediaType)) {
            if (k.getAddress().equals(oldContent.getAddress())) {
                break;
            } else {
                indexOldContent++;
            }
        }

        this.getStorage().getListContent().set(this.storage.getListContent().indexOf(oldContent), newContent);
        this.getStorage().getMapMediaTypeContent().get(mediaType).set(indexOldContent, newContent);
        this.getStorage().getMapAddressContent().remove(oldContentAddress);//replace(address, newContent);
        this.getStorage().getMapAddressContent().put(address, newContent);//replace(address, newContent);
        this.notifyObserver();
        return true;
    }

    public boolean editFile(Uploadable oldContentUploadable, MediaContent newContent) {
        if (oldContentUploadable == null || newContent == null) {
            throw new NullPointerException("No input!");
        }

        if (storage == null) {
            throw new NullPointerException("Storage isn't available.");
        }
        MediaContent oldContent = this.parseContent(oldContentUploadable);

        int index = storage.getListContent().indexOf(oldContent);

        storage.getListContent().set(index, newContent);
        this.notifyObserver();
        return true;
    }

    public boolean deleteContent(String address) {
        if (address == null) {
            throw new NullPointerException("No input!");
        }

        if (storage == null) {
            throw new NullPointerException("Storage isn't available.");
        }
        Content oldContent = this.storage.getMapAddressContent().get(address);
        MediaType mediaType;
        if (oldContent instanceof LicensedAudioVideo) {
            mediaType = MediaType.LicensedAudioVideo;
        } else if (oldContent instanceof InteractiveVideo) {
            mediaType = MediaType.InteractiveVideo;
        } else {
            throw new IllegalArgumentException("Illegal media type!");
        }
        Uploader tempUploader = ((Uploadable) oldContent).getUploader();
        this.storage.getListContent().remove(oldContent);
        this.storage.getMapMediaTypeContent().get(mediaType).remove(oldContent);
        this.storage.getMapAddressContent().remove(address, oldContent);

        if (this.getUploadersWithContentAmount().get(tempUploader) == 0) {
            this.deleteUploader(tempUploader.getName());
        }
        this.notifyObserver();
        return true;
    }

    public boolean createUploader(String name) {
        Uploader uploader = new UploaderImpl(name);
        if (listUploader != null) {
            for (Uploader tmpUploader : listUploader) {
                if (tmpUploader.getName().equals(name)) {
                    throw new IllegalArgumentException("uploader is existent");
                }
            }
        }

        this.notifyObserver();
        listUploader.add(uploader);
        return true;
    }

    public boolean createUploader(Uploader uploader) {
        if (listUploader != null) {
            for (Uploader tmpUploader : listUploader) {
                if (tmpUploader.getName().equals(uploader.getName())) {
                    throw new IllegalArgumentException("uploader is existent");
                }
            }
        }

        this.notifyObserver();
        listUploader.add(uploader);
        return true;
    }

    public boolean deleteUploader(String name) {
        if (listUploader != null) {
            for (Uploader tmpUploader : listUploader) {
                if (tmpUploader.getName().equals(name)) {
                    listUploader.remove(tmpUploader);
                    return true;
                }
            }
        }
        this.notifyObserver();
        return false;
    }

    public boolean deleteUploader(Uploader uploader) {
        if (listUploader != null) {
            for (Uploader tmpUploader : listUploader) {
                if (tmpUploader.getName().equals(uploader.getName())) {
                    listUploader.remove(uploader);
                    return true;
                }
            }
        }

        this.notifyObserver();
        return false;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                  //
    //                                                                                                  //
    //                                     OBSERVERS                                                    //
    //                                                                                                  //
    //                                                                                                  //
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean observeAreTagsUpdated() {
        return false;
    }

    public boolean observeIsSizeMoreAsNinetyPercent() {
        return (this.getStorageActuallySizeInPercent().compareTo(new BigDecimal("90.01")) >= 0);
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyObserver() {
        for (Observer observer : this.observers) {
            observer.update();
        }
    }

    public boolean checkIsSizeReached(Content content) {
        int checked = this.getStorage().getCapacity().compareTo(this.getStorageActuallySize().add(((MediaContent) content).getSize()));
        return !(checked >= 0);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                  //
    //                                                                                                  //
    //                                      GETTERS                                                     //
    //                                                                                                  //
    //                                                                                                  //
    //////////////////////////////////////////////////////////////////////////////////////////////////////


    public List<Tag> showAvailableTags() {
        if (storage == null) {
            throw new NullPointerException("Storage isn't available.");
        }
        List<Tag> listAvailableTags = new ArrayList<>();

        for (Content k1 : storage.getListContent()) {
            if (k1.getTags() != null) {
                for (Tag k2 : k1.getTags()) {
                    if (!listAvailableTags.contains(k2)) {
                        listAvailableTags.add(k2);
                    }
                }
            }
        }

        return listAvailableTags;
    }

    public List<Tag> showNotAvailableTags() {
        if (storage == null) {
            throw new NullPointerException("Storage isn't available.");
        }
        List<Tag> listNotAvailableTags = new ArrayList<>();
        listNotAvailableTags.addAll(EnumSet.allOf(Tag.class));

        for (Content k1 : storage.getListContent()) {
            if (k1.getTags() != null) {
                for (Tag k2 : k1.getTags()) {
                    listNotAvailableTags.remove(k2);
                }
            }
        }

        return listNotAvailableTags;
    }

    public List<Uploader> getUploaders() {
        return listUploader;
    }

    public Map<Uploader, Integer> getUploadersWithContentAmount() {
        int count = 0;
        Map<Uploader, Integer> mapUploadersWithContentAmount = new HashMap<>();
        for (Uploader k1 : this.getUploaders()) {
            for (Content k2 : this.getStorage().getListContent()) {
                if (k1.equals(((Uploadable) k2).getUploader())) {
                    count++;
                }
            }
            mapUploadersWithContentAmount.put(k1, count);
            count = 0;
        }
        return mapUploadersWithContentAmount;
    }

    public Uploader getUploader(int index) {
        return listUploader.get(index);
    }

    public BigDecimal getStorageTotalSize() {
        if (storage == null) {
            throw new IllegalArgumentException("The storage isn't existent.");
        }
        return storage.getCapacity();
    }

    public BigDecimal getStorageActuallySize() {
        if (storage == null) {
            throw new IllegalArgumentException("The storage isn't existent.");
        }

        BigDecimal sizeActually = BigDecimal.ZERO;
        for (Content k : this.getStorage().getListContent()) {
            sizeActually = sizeActually.add(((MediaContent) k).getSize());
        }

        return sizeActually;
    }

    public BigDecimal getStorageActuallySizeInPercent() {
        if (storage == null) {
            throw new IllegalArgumentException("The storage isn't existent.");
        }
        return (this.getStorageActuallySize().multiply(new BigDecimal("100"))).divide(this.getStorageTotalSize(), 2, BigDecimal.ROUND_DOWN);
    }

    public List<Content> getFileByType(MediaType type) {

        this.notifyObserver();
        return storage.getMapMediaTypeContent().get(type);
    }

    public List<Content> getFiles() throws NullPointerException {
        if (storage == null) {
            throw new NullPointerException("Storage isn't available.");
        }

        this.notifyObserver();
        return storage.getListContent();
    }

    public List<Content> getFilesByType(String type) {
        this.notifyObserver();
        return storage.getMapMediaTypeContent().get(MediaType.valueOf(type));
    }

    public StorageContent getStorage() {
        return storage;
    }

    public Content getFile(int index) {
        if (storage == null) {
            throw new NullPointerException("Storage isn't available.");
        }

        this.notifyObserver();
        return storage.getListContent().get(index);
    }

    public Content getContent(String address) {
        if (address == null) {
            throw new NullPointerException("No input!");
        }

        if (storage == null) {
            throw new NullPointerException("Storage isn't available.");
        }

        for (Content k : this.storage.getListContent()) {
            if (k.getAddress().equals(address)) {
                this.notifyObserver();
                return k;
            }
        }
        throw new IllegalArgumentException("invalid address");
    }

    public List<Content> getFileByTag(Tag tag) {
        if (storage == null) {
            throw new NullPointerException("Storage isn't available.");
        }
        List<Content> listContent = new ArrayList<>();

        for (Content k : storage.getListContent()) {
            for (Tag l : k.getTags()) {
                if (l.equals(tag)) {
                    listContent.add(k);
                }
            }
        }

        this.notifyObserver();
        return listContent;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                  //
    //                                                                                                  //
    //                                  PRIVATE METHODS                                                 //
    //                                                                                                  //
    //                                                                                                  //
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    private Uploader addUploaderForContent(String name) {
        for (Uploader k : listUploader) {
            if (name.equals(k.getName())) {
                return k;
            }
        }

        Uploader uploader = new UploaderImpl(name);
        listUploader.add(uploader);
        return uploader;
    }

    private String generateAddress(String address) {
        String temp_address;
        StringBuffer sb = new StringBuffer();
        String[] temp_address_array;
        boolean isNotAvailable = false;
        for (Content k1 : this.storage.getListContent()) {
            if (k1.getAddress().equals(address)) {
                isNotAvailable = true;
            }
        }
        if (isNotAvailable) {
            temp_address_array = address.split("\\+");
            for (int i = 0; i < temp_address_array.length; i++) {
                if (i == (temp_address_array.length - 1)) {
                    sb.append("+" + addressCounter);
                } else {
                    sb.append(temp_address_array[i]);
                }
            }
            temp_address = sb.toString();
        } else {
            temp_address = address;
        }
        addressCounter++;
        return temp_address;
    }

    private String generateAddress(MediaType mediaType, String uploader, int width, int height) {
        String address = mediaType.toString() + "#" + uploader.replace(' ', '_') + "#" + width + "x" + height + "+" + addressCounter;
        addressCounter++;
        return address;
    }

    private BigDecimal generateSize(long samplingRate, long bitrate, int width, int height) {
        BigDecimal size = BigDecimal.valueOf(width + height);
        return size;
    }

    private MediaContent parseContent(Uploadable uploadable) {
        MediaContent mediaContent = null;
        Audio audio;
        AudioVideo audioVideo;
        Video video;
        InteractiveVideo interactiveVideo;
        LicensedAudioVideo licensedAudioVideo;

        if (uploadable instanceof Audio) {
            audio = (Audio) uploadable;
            mediaContent = audio;
        }

        if (uploadable instanceof AudioVideo) {
            audioVideo = (AudioVideo) uploadable;
            mediaContent = audioVideo;
        }

        if (uploadable instanceof Video) {
            video = (Video) uploadable;
            mediaContent = video;
        }

        if (uploadable instanceof InteractiveVideo) {
            interactiveVideo = (InteractiveVideo) uploadable;
            mediaContent = interactiveVideo;
        }

        if (uploadable instanceof LicensedAudioVideo) {
            licensedAudioVideo = (LicensedAudioVideo) uploadable;
            mediaContent = licensedAudioVideo;
        }

        return mediaContent;
    }
}
