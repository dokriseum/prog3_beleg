package logic.events;

import logic.BusinessLogic;
import logic.event.InputEventAddUploader;
import logic.event.listener.InputEventListenerAddUploader;
import logic.event.listener.InputEventListenerOutput;
import models.storage.StorageContent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.PrintStream;
import java.math.BigDecimal;


class InputEventListenerTest {
    private StorageContent testStorageContent;
    private BusinessLogic testBusinessLogic;

    @BeforeEach
    void setUp() {
        testStorageContent = new StorageContent(new BigDecimal("55078"));
        testBusinessLogic = new BusinessLogic(testStorageContent);
    }

    @Test
    public void testGoodInputEventListenerAddUploader1() {
        InputEventListenerAddUploader listener = new InputEventListenerAddUploader(testBusinessLogic);
        InputEventAddUploader mockInputEventAddUploader = Mockito.mock(InputEventAddUploader.class);
        listener.onInputEvent(mockInputEventAddUploader);
        Mockito.verify(mockInputEventAddUploader, Mockito.atLeastOnce()).getEventUploader();
        Mockito.verify(mockInputEventAddUploader, Mockito.never()).getSource();
    }

    @Test
    void properOutput() {
        PrintStream originalOut = System.out;
        try {
            PrintStream out = Mockito.mock(PrintStream.class);
            System.setOut(out);
            InputEventAddUploader event = Mockito.mock(InputEventAddUploader.class);
            Mockito.when(event.getText()).thenReturn("InputEventAddUploader");
            InputEventListenerOutput listenerOutput = new InputEventListenerOutput(testBusinessLogic);


            listenerOutput.onInputEvent(event);


            Mockito.verify(out).println("input=InputEventAddUploader");
        } finally {
            System.setOut(originalOut);
        }
    }

    @AfterEach
    void tearDown() {
    }
}