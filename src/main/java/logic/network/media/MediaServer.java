package logic.network.media;

import logic.BusinessLogic;
import logic.events.InputEventListener;
import logic.eventsImpl.InputEventHandler;
import logic.eventsImpl.listener.*;
import logic.observer.CheckSizePerCentObserver;
import logic.observer.CheckTagsObserver;
import logic.observer.Observer;
import models.storage.StorageContent;
import view.ui.cli.Input;

import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;

public class MediaServer implements Runnable {
    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private Input io;
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

    public MediaServer(Socket socket) throws IOException {
        this.socket = socket;
        this.br = new BufferedReader(new InputStreamReader(System.in));
        this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    @Override
    public void run() {
        try (DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
            try (DataInputStream in = new DataInputStream(socket.getInputStream())) {
                this.io = new Input(in, out);
                this.init();
                System.out.println("client@" + socket.getInetAddress() + ":" + socket.getPort() + " connected");
                boolean error = this.executeSession(in, out);
                if (error != true) {
                    out.flush();
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

    private boolean executeSession(DataInputStream in, DataOutputStream out) throws IOException {
        io.io();
//        char command = in.readChar();
//        if ('I' != command) return "unknown command: " + command;
//        int bookId = in.readInt();
//        //if(!logic.network.library.bookExists(bookId)) return "unknown book: "+bookId;
//        int lineNumber = in.readInt();
//        if (!library.bookExists(bookId)) return "unknown book: " + bookId;
//        do {
//            if (!library.lineExists(bookId, lineNumber)) {
//                out.writeChar('E');
//                return null;
//            }
//            out.writeChar('L');
//            out.writeUTF(this.library.getLine(bookId, lineNumber++));
//            char response = in.readChar();
//            switch (response) {
//                case 'N':
//                    break;
//                case 'S':
//                    return null;
//                default:
//                    return "unknown response: " + response;
//            }
//        } while (true);
        return true;
    }

    private void init() {
        handler.add(inputEventListenerAddMedia);
        handler.add(inputEventListenerAddUploader);
        handler.add(inputEventListenerDeleteMedia);
        handler.add(inputEventListenerDeleteUploader);
        handler.add(inputEventListenerOutput);
        handler.add(inputEventListenerSwitchChoice);
        handler.add(inputEventListenerExit);
        handler.add(inputEventListenerShowMedia);
        handler.add(inputEventListenerShowUploader);
        this.io.setHandler(handler);
    }
}