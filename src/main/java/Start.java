import logic.BusinessLogic;
import logic.events.InputEventListener;
import logic.eventsImpl.InputEventHandler;
import logic.eventsImpl.listener.*;
import logic.observer.CheckSizePerCentObserver;
import logic.observer.CheckTagsObserver;
import logic.observer.Observer;
import models.storage.StorageContent;
import view.ui.cli.Input;

import java.math.BigDecimal;

/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

public class Start {
    public static void main(String[] args) {
        StorageContent storageContent = new StorageContent(new BigDecimal("655078"));
        BusinessLogic businessLogic = new BusinessLogic(storageContent);
        Observer o1 = new CheckSizePerCentObserver(businessLogic);
        Observer o2 = new CheckTagsObserver(businessLogic);
        Input input = new Input();
        InputEventHandler handler = new InputEventHandler();
        InputEventListener inputEventListenerAddMedia = new InputEventListenerAddMedia(businessLogic);
        InputEventListener inputEventListenerAddUploader = new InputEventListenerAddUploader(businessLogic);
        InputEventListener inputEventListenerDeleteMedia = new InputEventListenerDeleteMedia(businessLogic);
        InputEventListener inputEventListenerDeleteUploader = new InputEventListenerDeleteUploader(businessLogic);
        InputEventListener inputEventListenerOutput = new InputEventListenerOutput(businessLogic);
        InputEventListener inputEventListenerSwitchChoice = new InputEventListenerSwitchChoice(businessLogic);
        InputEventListener inputEventListenerExit = new InputEventListenerExit(businessLogic);
        InputEventListener inputEventListenerShowMedia = new InputEventListenerShowMedia(businessLogic);
        InputEventListener inputEventListenerShowUploader = new InputEventListenerShowUploader(businessLogic);

        handler.add(inputEventListenerAddMedia);
        handler.add(inputEventListenerAddUploader);
        handler.add(inputEventListenerDeleteMedia);
        handler.add(inputEventListenerDeleteUploader);
        handler.add(inputEventListenerOutput);
        handler.add(inputEventListenerSwitchChoice);
        handler.add(inputEventListenerExit);
        handler.add(inputEventListenerShowMedia);
        handler.add(inputEventListenerShowUploader);
        input.setHandler(handler);
        input.input();
        /**
         BusinessLogic start = new BusinessLogic();
         start.createStorage(1024);
         BigDecimal sizeForMediaStorage = new BigDecimal("1024");
         start.createUploader("AMICA");
         Uploader uploader1 = start.getUploader(0);
         Date date1 = new Date();
         Collection<Tag> tags1 = new ArrayList<>();
         tags1.add(Tag.Tutorial);
         MediaContent audio1 = new AudioImpl(0, "123", 4096, Duration.ofSeconds(360), new BigDecimal(786), "127.0.0.1", tags1, 24, uploader1, date1);

         System.out.println("\n#####\n");

         System.out.println(start.getStorageSize());
         System.out.println(start.getStorageCount());

         System.out.println("\n#####\n");

         start.uploadFile(audio1);
         System.out.println(start.getFile(0));
         System.out.println(start.getStorageCount());

         System.out.println("\n#####\n");

         MediaContent audio2 = new AudioImpl(0, "321", 4096, Duration.ofSeconds(360), new BigDecimal(786), "127.0.1", tags1, 24, uploader1, date1);
         System.out.println(start.getFiles());
         System.out.println(start.getStorageCount());
         start.uploadFile(audio2);
         System.out.println(start.getFiles());
         System.out.println(start.getStorageCount());

         System.out.println("\n#####\n");

         MediaContent audio3 = new AudioImpl(0, "321", 4096, Duration.ofSeconds(360), new BigDecimal(128), "127.0.1.1", tags1, 24, uploader1, date1);
         start.uploadFile(audio3);
         System.out.println(start.getFiles());
         System.out.println(start.getStorageCount());

         System.out.println("\n#####\n");

         start.deleteFile(audio1);
         System.out.println(start.getFiles());
         System.out.println(start.getStorageCount());

         System.out.println("\n#####\n");
         start.editFile(audio3, audio2);
         System.out.println(start.getFiles());
         System.out.println(start.getStorageCount());
         */
    }
}
