/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package models.storage;

import models.mediaDB.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediaStorage {
    private BigDecimal size;
    private BigDecimal count;
    private List<Content> listContent = new ArrayList<>();
    private Map<MediaType, List<Content>> mapContentByMediaType = new HashMap<>();

    public MediaStorage(long size) {
        this.size = new BigDecimal(size);
        count = new BigDecimal(0);
    }

    public MediaStorage(long size, List<Content> listContent) {
        this.size = new BigDecimal(size);
        this.listContent = listContent;
        count = new BigDecimal(0);
    }

    public MediaStorage(BigDecimal size) {
        this.size = size;
        count = new BigDecimal(0);
    }

    public MediaStorage(BigDecimal size, List<Content> listContent) {
        this.size = size;
        this.listContent = listContent;
        count = new BigDecimal(0);
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = new BigDecimal(size);
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public BigDecimal getCount() {
        return count;
    }

    public boolean addContent(Content content) throws IllegalArgumentException, IndexOutOfBoundsException {
        if (content == null) {
            return false;
        }

        MediaContent tempMediaContent = (MediaContent) content;

        BigDecimal tmpBigDecimal = count.add(tempMediaContent.getSize());

        if (this.count.compareTo(this.size) >= 0 || tmpBigDecimal.compareTo(this.size) >= 0) {
            throw new IndexOutOfBoundsException("Storage is full or not existent.");
        }

        for (Content k : listContent) {
            if (k.getAddress().equals(content.getAddress())) {
                throw new IllegalArgumentException("Address is already assigned.");
            }
        }

        MediaType contentMediaType = this.checkMediaType(content);
        List<Content> tmpContentListByType;
        if (mapContentByMediaType.get(contentMediaType) == null) {
            tmpContentListByType = new ArrayList<>();
        } else {
            tmpContentListByType = mapContentByMediaType.get(contentMediaType);
        }
        tmpContentListByType.add(content);

        this.count = count.add(tempMediaContent.getSize());
        mapContentByMediaType.put(contentMediaType, tmpContentListByType);
        listContent.add(content);
        return true;
    }

    private MediaType checkMediaType(Content content) {
        if (content instanceof LicensedAudioVideo) {
            return MediaType.LicensedAudioVideo;
        } else if (content instanceof InteractiveVideo) {
            return MediaType.InteractiveVideo;
        } else {
            throw new IllegalArgumentException("not valid media type");
        }
    }

    public boolean addContent(Uploadable contentUploadable) throws IllegalArgumentException, IndexOutOfBoundsException {
        if (contentUploadable == null) {
            return false;
        }

        MediaContent mediaContent = this.parseContent(contentUploadable);

        BigDecimal tmpBigDecimal = count.add(mediaContent.getSize());

        if (this.count.compareTo(this.size) >= 0 || tmpBigDecimal.compareTo(this.size) >= 0) {
            throw new IndexOutOfBoundsException("Storage is full or not existent.");
        }


        for (Content k : listContent) {
            if (k.getAddress().equals(mediaContent.getAddress())) {
                throw new IllegalArgumentException("Address is already assigned.");
            }
        }
        this.count = count.add(mediaContent.getSize());
        listContent.add(mediaContent);
        return true;
    }

    public boolean deleteContent(Content content) {
        if (content == null) {
            return false;
        }

        MediaContent mediaContent = (MediaContent) content;
        listContent.remove(content);
        count = count.subtract(mediaContent.getSize());
        return true;
    }

    public boolean deleteContent(Uploadable contentUploadable) {
        if (contentUploadable == null) {
            return false;
        }

        Content content = null;
        Audio audio;
        AudioVideo audioVideo;
        Video video;
        InteractiveVideo interactiveVideo;
        LicensedAudioVideo licensedAudioVideo;

        if (contentUploadable instanceof Audio) {
            audio = (Audio) contentUploadable;
            content = audio;
        }

        if (contentUploadable instanceof AudioVideo) {
            audioVideo = (AudioVideo) contentUploadable;
            content = audioVideo;
        }

        if (contentUploadable instanceof Video) {
            video = (Video) contentUploadable;
            content = video;
        }

        if (contentUploadable instanceof InteractiveVideo) {
            interactiveVideo = (InteractiveVideo) contentUploadable;
            content = interactiveVideo;
        }

        if (contentUploadable instanceof LicensedAudioVideo) {
            licensedAudioVideo = (LicensedAudioVideo) contentUploadable;
            content = licensedAudioVideo;
        }

        MediaContent mediaContent = (MediaContent) content;
        listContent.remove(content);
        count = count.subtract(mediaContent.getSize());
        return true;
    }

    public boolean deleteContent(int index) {
        BigDecimal tmpIndex = new BigDecimal(index);
        if (tmpIndex.compareTo(this.size) > 0 || index < 0) {
            return false;
        }
        Content content = listContent.get(index);
        MediaContent mediaContent = (MediaContent) content;
        listContent.remove(index);
        count = count.subtract(mediaContent.getSize());
        return true;
    }

    public void deleteContent(String address) {
        for (Content k : listContent) {
            if (k.getAddress().equals(address)) {
                this.deleteContent(k);
            }
        }
    }

    public boolean editContent(Content oldContent, Content newContent) {
        if (oldContent == null || newContent == null) {
            return false;
        }
        MediaContent oldMediaContent = (MediaContent) oldContent;
        MediaContent newMediaContent = (MediaContent) newContent;
        BigDecimal sizeOldContent = oldMediaContent.getSize();
        BigDecimal sizeNewContent = newMediaContent.getSize();
        BigDecimal tmpCount = this.count;
        tmpCount = tmpCount.subtract(sizeOldContent);
        tmpCount = tmpCount.add(sizeNewContent);

        if (tmpCount.compareTo(size) >= 0) {
            throw new IndexOutOfBoundsException();
        }

        int index = listContent.indexOf(oldContent);
        if (!(index >= 0)) {
            return false;
        }

        listContent.set(index, newContent);
        count = tmpCount;
        return true;
    }

    public boolean editContent(int index, MediaContent newContent) {
        if (newContent == null) {
            return false;
        }

        if (index < 0 || index > listContent.size()) {
            return false;
        }

        listContent.set(index, newContent);
        return true;
    }

    public boolean editContent(int index, Uploadable newContentUploadable) {
        if (newContentUploadable == null) {
            return false;
        }

        if (index < 0 || index > listContent.size()) {
            return false;
        }


        MediaContent mediaContent = null;
        Audio audio;
        AudioVideo audioVideo;
        Video video;
        InteractiveVideo interactiveVideo;
        LicensedAudioVideo licensedAudioVideo;

        if (newContentUploadable instanceof Audio) {
            audio = (Audio) newContentUploadable;
            mediaContent = audio;
        }

        if (newContentUploadable instanceof AudioVideo) {
            audioVideo = (AudioVideo) newContentUploadable;
            mediaContent = audioVideo;
        }

        if (newContentUploadable instanceof Video) {
            video = (Video) newContentUploadable;
            mediaContent = video;
        }

        if (newContentUploadable instanceof InteractiveVideo) {
            interactiveVideo = (InteractiveVideo) newContentUploadable;
            mediaContent = interactiveVideo;
        }

        if (newContentUploadable instanceof LicensedAudioVideo) {
            licensedAudioVideo = (LicensedAudioVideo) newContentUploadable;
            mediaContent = licensedAudioVideo;
        }

        listContent.set(index, mediaContent);
        return true;
    }

    public Content getContent(int index) {
        Content content = listContent.get(index);
        Audio audio;
        AudioVideo audioVideo;
        Video video;
        InteractiveVideo interactiveVideo;
        LicensedAudioVideo licensedAudioVideo;

        if (content instanceof Audio) {
            audio = (Audio) content;
            return audio;
        }

        if (content instanceof AudioVideo) {
            audioVideo = (AudioVideo) content;
            return audioVideo;
        }

        if (content instanceof Video) {
            video = (Video) content;
            return video;
        }

        if (content instanceof InteractiveVideo) {
            interactiveVideo = (InteractiveVideo) content;
            return interactiveVideo;
        }

        if (content instanceof LicensedAudioVideo) {
            licensedAudioVideo = (LicensedAudioVideo) content;
            return licensedAudioVideo;
        }

        return null;
    }

    public List<Content> getContentList() {
        return listContent;
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

    public List<Content> getContentByType(MediaType type) {
        return mapContentByMediaType.get(type);
    }

    //zaehlt dann die aufrufe, wenn eingefügt ist
    //bisher noch nicht noetig
//    public void raiseAccessCount(String address) {
//        for (Content k : listContent) {
//            if (k.getAddress().equals(address)) {
//                MediaContent mediaContent = (MediaContent) k;
//
//                if (k instanceof InteractiveVideo) {
//                    InteractiveVideo interactiveVideo = new InteractiveVideoImpl(((InteractiveVideo) k).getWidth(), ((InteractiveVideo) k).getHeight(), ((InteractiveVideo) k).getEncoding(), mediaContent.getBitrate(), mediaContent.getLength(), mediaContent.getSize(), k.getAddress(), k.getTags(), k.getAccessCount() + 1, ((InteractiveVideo) k).getUploader(), ((InteractiveVideo) k).getUploadDate(), ((InteractiveVideo) k).getType());
//                }
//
//                if (k instanceof LicensedAudioVideo) {
//                    LicensedAudioVideo licensedAudioVideo = new LicensedAudioVideoImpl(((LicensedAudioVideo) k).getSamplingRate(), ((LicensedAudioVideo) k).getWidth(), ((LicensedAudioVideo) k).getHeight(), ((LicensedAudioVideo) k).getEncoding(), ((LicensedAudioVideo) k).getHolder(), mediaContent.getBitrate(), mediaContent.getLength(), mediaContent.getSize(), k.getAddress(), k.getTags(), k.getAccessCount() + 1, ((LicensedAudioVideo) k).getUploader(), ((LicensedAudioVideo) k).getUploadDate());
//                }
//            }
//        }
//    }
}
