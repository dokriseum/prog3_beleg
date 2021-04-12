package logic.network;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MediaServerTest {
    private String outputFirst;

    @BeforeEach
    void setUp() {
        StringBuffer sb = new StringBuffer();
        sb.append("###################################\n" +
                "############# M E N U #############\n" +
                "###################################\n" +
                "\n" +
                ":c change to insert mode\n" +
                ":d change to delete mode\n" +
                ":r change to display mode\n" +
                ":u change to modification mode\n" +
                ":p change to persistence mode\n" +
                ":config change to config mode\n" +
                "\n" +
                "Enter input: ");
        outputFirst = sb.toString();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void goodTestConnection() {
        try {
            ServerSocket mockServerSocket = mock(ServerSocket.class);
            Socket mockSocket = mockServerSocket.accept();
            MediaServer server = new MediaServer(mockSocket);
            when(mockServerSocket.accept()).thenReturn(mockSocket);
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void goodTestCommunicationShowOutput() throws IOException {
        Socket mockSocket = mock(Socket.class);
        MediaServer server = new MediaServer(mockSocket);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(14);
//        DataOutputStream dos = new DataOutputStream(bos);
//        dos.writeUTF(":c");
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream(bos.toByteArray()));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        when(mockSocket.getOutputStream()).thenReturn(out);

        server.run();

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(out.toByteArray()));
        Assertions.assertEquals(outputFirst, dis.readUTF());
    }
}