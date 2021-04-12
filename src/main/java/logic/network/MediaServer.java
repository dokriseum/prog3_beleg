package logic.network;

import logic.BusinessLogic;
import logic.event.InputEventHandler;
import logic.event.InputEventListener;
import logic.event.listener.*;
import logic.observer.CheckSizePerCentObserver;
import logic.observer.CheckTagsObserver;
import logic.observer.Observer;
import models.storage.StorageContent;
import view.cli.Input;

import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;
import java.net.SocketException;

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
    private InputEventListener inputEventListenerAddMedia;
    private InputEventListener inputEventListenerAddUploader;
    private InputEventListener inputEventListenerDeleteMedia;
    private InputEventListener inputEventListenerDeleteUploader;
    private InputEventListener inputEventListenerOutput;
    private InputEventListener inputEventListenerSwitchChoice;
    private InputEventListener inputEventListenerExit;
    private InputEventListener inputEventListenerShowMedia;
    private InputEventListener inputEventListenerShowUploader;
    private InputEventListener inputEventListenerPersistence;

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
                this.init(in, out);
                System.out.println("client@" + socket.getInetAddress() + ":" + socket.getPort() + " connected");
                boolean error = this.executeSession(in, out);
                if (error != true) {
                    out.flush();
                    System.out.println("client@" + socket.getInetAddress() + ":" + socket.getPort() + " error=" + error);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SocketException e) {
            System.err.println("client closed connection");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("client@" + socket.getInetAddress() + ":" + socket.getPort() + " disconnected");
    }

    private boolean executeSession(DataInputStream in, DataOutputStream out) throws IOException {
        io.input();
        return true;
    }

    private void init(DataInputStream in, DataOutputStream out) {
        inputEventListenerAddMedia = new InputEventListenerAddContent(businessLogic);
        inputEventListenerAddUploader = new InputEventListenerAddUploader(businessLogic);
        inputEventListenerDeleteMedia = new InputEventListenerDeleteContent(businessLogic);
        inputEventListenerDeleteUploader = new InputEventListenerDeleteUploader(businessLogic);
        inputEventListenerOutput = new InputEventListenerOutput(businessLogic);
        inputEventListenerSwitchChoice = new InputEventListenerSwitchChoice(businessLogic);
        inputEventListenerExit = new InputEventListenerExit(businessLogic);
        inputEventListenerShowMedia = new InputEventListenerShowMedia(businessLogic, in, out);
        inputEventListenerShowUploader = new InputEventListenerShowUploader(businessLogic, in, out);
        inputEventListenerPersistence = new InputEventListenerPersistence(businessLogic, in, out);
        handler.add(inputEventListenerAddMedia);
        handler.add(inputEventListenerAddUploader);
        handler.add(inputEventListenerDeleteMedia);
        handler.add(inputEventListenerDeleteUploader);
        handler.add(inputEventListenerOutput);
        handler.add(inputEventListenerSwitchChoice);
        handler.add(inputEventListenerExit);
        handler.add(inputEventListenerShowMedia);
        handler.add(inputEventListenerShowUploader);
        handler.add(inputEventListenerPersistence);
        this.io.setHandler(handler);
    }
}