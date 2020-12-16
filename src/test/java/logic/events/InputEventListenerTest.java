package logic.events;

import exceptions.IllegalEventException;
import logic.BusinessLogic;
import logic.eventsImpl.InputEventAddUploader;
import logic.eventsImpl.listener.InputEventListenerAddUploader;
import logic.eventsImpl.listener.InputEventListenerOutput;
import models.storage.StorageContent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.math.BigDecimal;

import static org.mockito.Mockito.*;

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
        InputEventAddUploader mockInputEventAddUploader = mock(InputEventAddUploader.class);

        try {
            listener.onInputEvent(mockInputEventAddUploader);
        } catch (IllegalEventException e) {
            Assertions.fail();
        }

        verify(mockInputEventAddUploader, atLeastOnce()).getEventUploader();
        verify(mockInputEventAddUploader, never()).getSource();
    }

    @Test
    void properOutput() {
        PrintStream originalOut = System.out;
        try {
            PrintStream out = mock(PrintStream.class);
            System.setOut(out);
            InputEventAddUploader event = mock(InputEventAddUploader.class);
            when(event.getText()).thenReturn("InputEventAddUploader");
            InputEventListenerOutput listenerOutput = new InputEventListenerOutput(testBusinessLogic);


            listenerOutput.onInputEvent(event);


            verify(out).println("input=InputEventAddUploader");
        } finally {
            System.setOut(originalOut);
        }
    }

    @AfterEach
    void tearDown() {
    }
}