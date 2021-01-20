package logic.network.media;

import logic.BusinessLogic;
import logic.events.InputEventListener;
import logic.eventsImpl.InputEventHandler;
import logic.eventsImpl.listener.*;
import logic.observer.CheckSizePerCentObserver;
import logic.observer.CheckTagsObserver;
import logic.observer.Observer;
import models.storage.StorageContent;
import view.ui.cli.InputOutput;
import view.ui.cli.View;

import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;

public class MediaServer implements Runnable {
    private Socket socket;
    private BusinessLogic bl;
    private View view;
    private InputOutput io;
    private StorageContent storageContent = new StorageContent(new BigDecimal("655078"));
    private BusinessLogic businessLogic = new BusinessLogic(storageContent);
    private Observer o1 = new CheckSizePerCentObserver(businessLogic);
    private Observer o2 = new CheckTagsObserver(businessLogic);
    private InputEventHandler handler = new InputEventHandler();
    private InputEventListener inputEventListenerAddMedia = new InputEventListenerAddMedia(businessLogic);
    private InputEventListener inputEventListenerAddUploader = new InputEventListenerAddUploader(businessLogic);
    private InputEventListener inputEventListenerDeleteMedia = new InputEventListenerDeleteMedia(businessLogic);
    private InputEventListener inputEventListenerDeleteUploader = new InputEventListenerDeleteUploader(businessLogic);
    private InputEventListener inputEventListenerOutput = new InputEventListenerOutput(businessLogic);
    private InputEventListener inputEventListenerSwitchChoice = new InputEventListenerSwitchChoice(businessLogic);
    private InputEventListener inputEventListenerExit = new InputEventListenerExit(businessLogic);
    private InputEventListener inputEventListenerShowMedia = new InputEventListenerShowMedia(businessLogic);
    private InputEventListener inputEventListenerShowUploader = new InputEventListenerShowUploader(businessLogic);


    public MediaServer(Socket socket, BusinessLogic bl) throws IOException {
        this.socket = socket;
        this.bl = bl;
        this.view = new View();
        this.io = new InputOutput(socket.getInputStream(), socket.getOutputStream());
        this.init();
    }

    @Override
    public void run() {
        try (OutputStream out = new DataOutputStream(socket.getOutputStream())) {
            try (InputStream in = new DataInputStream(socket.getInputStream())) {
                System.out.println("client@" + socket.getInetAddress() + ":" + socket.getPort() + " connected");
                String error = this.executeSession(in, out);
                if (null != error) {
                    out.write('F');
                    (new OutputStreamWriter(out)).write(error);
                    System.out.println("client@" + socket.getInetAddress() + ":" + socket.getPort() + " error=" + error);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("client@" + socket.getInetAddress() + ":" + socket.getPort() + " disconnected");
    }

    private String executeSession(InputStream in, OutputStream out) throws IOException {
        this.io.input();
        /*
        String command = in.readUTF();
        if (command.equals("I")) {
            return "unknown command: " + command;
        }
        int bookId = in.readInt();
        */
        //if(!logic.network.library.bookExists(bookId)) return "unknown book: "+bookId;
        /*
        int lineNumber = in.readInt();
        if (!this.bl.) return "unknown book: " + bookId;
        do {
            if (!this.bl.lineExists(bookId, lineNumber)) {
                out.writeChar('E');
                return null;
            }
            out.writeChar('L');
            out.writeUTF(this.bl.getLine(bookId, lineNumber++));
            char response = in.readChar();
            switch (response) {
                case 'N':
                    break;
                case 'S':
                    return null;
                default:
                    return "unknown response: " + response;
            }
        } while (true);
         */
        return null;
    }

    private boolean init() {
        handler.add(inputEventListenerAddMedia);
        handler.add(inputEventListenerAddUploader);
        handler.add(inputEventListenerDeleteMedia);
        handler.add(inputEventListenerDeleteUploader);
        handler.add(inputEventListenerOutput);
        handler.add(inputEventListenerSwitchChoice);
        handler.add(inputEventListenerExit);
        handler.add(inputEventListenerShowMedia);
        handler.add(inputEventListenerShowUploader);
        io.setHandler(handler);
        return true;
    }
}
