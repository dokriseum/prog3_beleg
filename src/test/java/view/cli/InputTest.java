package view.cli;

import logic.BusinessLogic;
import logic.event.*;
import logic.event.listener.*;
import logic.observer.CheckSizePerCentObserver;
import logic.observer.CheckTagsObserver;
import logic.observer.Observer;
import models.storage.StorageContent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class InputTest {
    private StorageContent testStorageContent1;
    private BusinessLogic testBusinessLogic1;
    private Observer o1;
    private Observer o2;
    private Input testInput1;
    private InputEventHandler testHandler1;
    private InputEventListener testInputEventListenerAddMedia;
    private InputEventListener testInputEventListenerAddUploader;
    private InputEventListener testInputEventListenerDeleteMedia;
    private InputEventListener testInputEventListenerDeleteUploader;
    private InputEventListener testInputEventListenerOutput;
    private InputEventListener testInputEventListenerSwitchChoice;
    private InputEventListener testInputEventListenerExit;
    private InputEventListener testInputEventListenerShowMedia;
    private InputEventListener testInputEventListenerShowUploader;
    private InputEvent testInputEvent1;
    private InputEvent testInputEvent2;
    private InputEventListener testInputEventListener1;
    private InputEventListener testInputEventListener2;

    @BeforeEach
    void setUp() {
        testStorageContent1 = new StorageContent(new BigDecimal("543210"));
        testBusinessLogic1 = new BusinessLogic(testStorageContent1);
        Observer o1 = new CheckSizePerCentObserver(testBusinessLogic1);
        Observer o2 = new CheckTagsObserver(testBusinessLogic1);
        testInput1 = new Input(null, null);
        testHandler1 = new InputEventHandler();
        testInputEventListenerAddMedia = new InputEventListenerAddContent(testBusinessLogic1);
        testInputEventListenerAddUploader = new InputEventListenerAddUploader(testBusinessLogic1);
        testInputEventListenerDeleteMedia = new InputEventListenerDeleteContent(testBusinessLogic1);
        testInputEventListenerDeleteUploader = new InputEventListenerDeleteUploader(testBusinessLogic1);
        testInputEventListenerOutput = new InputEventListenerOutput(testBusinessLogic1);
        testInputEventListenerSwitchChoice = new InputEventListenerSwitchChoice(testBusinessLogic1);
        testInputEventListenerExit = new InputEventListenerExit(testBusinessLogic1);
        testInputEventListenerShowMedia = new InputEventListenerShowMedia(testBusinessLogic1);
        testInputEventListenerShowUploader = new InputEventListenerShowUploader(testBusinessLogic1);

        testHandler1.add(testInputEventListenerAddMedia);
        testHandler1.add(testInputEventListenerAddUploader);
        testHandler1.add(testInputEventListenerDeleteMedia);
        testHandler1.add(testInputEventListenerDeleteUploader);
        testHandler1.add(testInputEventListenerOutput);
        testHandler1.add(testInputEventListenerSwitchChoice);
        testHandler1.add(testInputEventListenerExit);
        testHandler1.add(testInputEventListenerShowMedia);
        testHandler1.add(testInputEventListenerShowUploader);
        testInput1.setHandler(testHandler1);
        testInput1.input();
    }

    @Test
    void testGoodInputEvent1() {
        //SETUP//
        testInput1.setTestString(":c");
        testInput1.input();
        testInputEvent1 = testInput1.getInputEvent();
        testInput1.setTestString("LicensedAudioVideo Produzent1 , 8000 600 DCT 1400 900 MDCT 44100 EdBangerRecords");
        testInput1.input();
        testInputEvent2 = testInput1.getInputEvent();
        //TEST//
        Assertions.assertTrue(testInputEvent1 instanceof InputEventSwitchChoice);
        Assertions.assertTrue(testInputEvent2 instanceof InputEventAddContent);
    }

    @Test
    void testGoodInputEvent2() {
        //SETUP//
        testInput1.setTestString(":r");
        testInput1.input();
        //TEST//
        Assertions.assertEquals(Choice.SHOW, testInput1.getInputChoice());
    }

    @Test
    void testBadInputEvent1() {
        //TEST//
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testInput1.inputMapping(":l");
        });
    }

    @Test
    void testBadInputEvent2() {
        //SETUP//
        testInput1.setTestString(":c");
        testInput1.input();
        //TEST//
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testInput1.inputMapping("Audio xxx");
        });
    }

    @Test
    void testBadInputEvent3() {
        //SETUP//
        testInput1.input();
        //TEST//
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testInput1.inputMapping("LicensedAudioVideo Produzent1 , 8000 600 DCT 1400 900 MDCT 44100 EdBangerRecords");
        });
        Assertions.assertEquals(null, testInput1.getInputChoice());
    }

    @AfterEach
    void tearDown() {
    }
}