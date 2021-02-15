package logic.event;

import models.mediaDB.Tag;
import models.mediaDB.Uploader;
import models.storage.MediaType;

import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class InputEventAddContent extends InputEvent {
    private MediaType eventMediaType;
    private int eventSamplingRate;
    private int eventWidth;
    private int eventHeight;
    private String eventEncording;
    private String eventHolder;
    private long eventBitrate;
    private Duration eventLength;
    private Collection<Tag> eventTags;
    private long eventAccessCount = 0;
    private Uploader eventUploader;
    private Date eventUploadDate;
    private String eventType;

    public InputEventAddContent(Object source, String text, MediaType eventMediaType, int eventSamplingRate, int eventWidth, int eventHeight, String eventEncording, String eventHolder, long eventBitrate, Duration eventLength, Collection<Tag> eventTags, Uploader eventUploader, Date eventUploadDate, String eventType) {
        super(source, text);
        this.eventMediaType = eventMediaType;
        this.eventSamplingRate = eventSamplingRate;
        this.eventWidth = eventWidth;
        this.eventHeight = eventHeight;
        this.eventEncording = eventEncording;
        this.eventHolder = eventHolder;
        this.eventBitrate = eventBitrate;
        this.eventLength = eventLength;
        this.eventTags = eventTags;
        this.eventUploader = eventUploader;
        this.eventUploadDate = eventUploadDate;
        this.eventType = eventType;
    }

    public MediaType getEventMediaType() {
        return eventMediaType;
    }

    public int getEventSamplingRate() {
        return eventSamplingRate;
    }

    public int getEventWidth() {
        return eventWidth;
    }

    public int getEventHeight() {
        return eventHeight;
    }

    public String getEventEncording() {
        return eventEncording;
    }

    public String getEventHolder() {
        return eventHolder;
    }

    public long getEventBitrate() {
        return eventBitrate;
    }

    public Duration getEventLength() {
        return eventLength;
    }

    public Collection<Tag> getEventTags() {
        return eventTags;
    }

    public long getEventAccessCount() {
        return eventAccessCount;
    }

    public Uploader getEventUploader() {
        return eventUploader;
    }

    public Date getEventUploadDate() {
        return eventUploadDate;
    }

    public String getEventType() {
        return eventType;
    }
}