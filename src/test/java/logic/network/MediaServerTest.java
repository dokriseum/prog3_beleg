package logic.network;

import logic.BusinessLogic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.io.*;
import java.net.Socket;

import static org.mockito.Mockito.*;

class MediaServerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    private final BusinessLogic bl = new BusinessLogic();

    @Test
    public void secondLineOfFirstBook() throws IOException {
        Socket mockSocket = Mockito.mock(Socket.class);
        MediaServer server = new MediaServer(mockSocket);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(14);
        DataOutputStream dos = new DataOutputStream(bos);
//        dos.writeChar('I');
//        dos.writeInt(1);
//        dos.writeInt(2);
//        dos.writeChar('S');
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream(bos.toByteArray()));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        when(mockSocket.getOutputStream()).thenReturn(out);

        server.run();

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(out.toByteArray()));
        Assertions.assertEquals('L', dis.readChar());
        Assertions.assertEquals("bl.getLine(1, 2)", dis.readUTF());
    }

    @Test
    public void secondLineOfFirstBookClosed() throws IOException {
        Socket mockSocket = Mockito.mock(Socket.class);
        MediaServer server = new MediaServer(mockSocket);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(140);
        try (DataOutputStream dos = new DataOutputStream(bos)) {
            dos.writeChar('I');
            dos.writeInt(1);
            dos.writeInt(2);
            dos.writeChar('S');
        }
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream(bos.toByteArray()));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        when(mockSocket.getOutputStream()).thenReturn(out);

        server.run();

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(out.toByteArray()));
        Assertions.assertEquals('L', dis.readChar());
        Assertions.assertEquals("bl.getLine(1, 2)", dis.readUTF());
    }

    @Test
    public void ninthLineOfFirstBook() throws IOException {
        Socket mockSocket = Mockito.mock(Socket.class);
        MediaServer server = new MediaServer(mockSocket);
        InputStream mockInputStream = mock(InputStream.class);
        OutputStream mockOutputStream = mock(OutputStream.class);
        when(mockSocket.getInputStream()).thenReturn(mockInputStream);
        when(mockSocket.getOutputStream()).thenReturn(mockOutputStream);
        when(mockInputStream.read()).thenReturn(0).thenReturn(73)
                .thenReturn(0).thenReturn(0).thenReturn(0).thenReturn(2)
                .thenReturn(0).thenReturn(0).thenReturn(0).thenReturn(9);

        server.run();

        InOrder inOrder = inOrder(mockOutputStream);
        inOrder.verify(mockOutputStream).write((byte) 0);
        inOrder.verify(mockOutputStream).write((byte) 69);
    }
}