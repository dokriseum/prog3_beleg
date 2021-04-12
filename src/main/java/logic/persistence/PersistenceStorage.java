package logic.persistence;

import exceptions.SizeReachedException;
import logic.BusinessLogic;
import logic.persistence.jbp_models.*;
import models.mediaDB.*;
import models.storage.Storage;

import java.awt.*;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;

public class PersistenceStorage implements Persistence, Serializable {
    private BusinessLogic businessLogic;

    public PersistenceStorage(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @Override
    public boolean saveJOS() throws IOException {
        String path = ".storage/storage.file";
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.businessLogic.getStorage());
        if (oos != null) {
            oos.close();
        }
        if (fos != null) {
            fos.close();
        }
        return true;
    }

    @Override
    public boolean loadJOS() throws IOException, ClassNotFoundException {
        String path = ".storage/storage.file";
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Storage tempStorage = (Storage) ois.readObject();

        for (Content k : tempStorage.getListContent()) {
            try {
                this.businessLogic.uploadContent(k);
            } catch (SizeReachedException e) {
                e.printStackTrace();
            }
        }

        if (ois != null) {
            ois.close();
        }

        if (fis != null) {
            fis.close();
        }
        return true;
    }

    @Override
    public boolean saveJBP() throws IOException {
        File file = new File(".storage/storage.xml");
        file.delete();
        String path = ".storage/storage.xml";
        FileOutputStream fos = new FileOutputStream(path);
        XMLEncoder encoder = new XMLEncoder(fos);
        JBP_Storage jpbStorage = new JBP_Storage();

        for (Content content : this.businessLogic.getStorage().getListContent()) {
            PersistenceItem item;

            if (content instanceof InteractiveVideo) {
                item = new JBP_InteractiveVideo((InteractiveVideo) content);
            } else if (content instanceof LicensedAudioVideo) {
                item = new JBP_LicensedAudioVideo((LicensedAudioVideo) content);
            } else if (content instanceof LicensedVideo) {
                item = new JBP_LicensedVideo((LicensedVideo) content);
            } else if (content instanceof LicensedAudio) {
                item = new JBP_LicensedAudio((LicensedAudio) content);
            } else if (content instanceof AudioVideo) {
                item = new JBP_AudioVideo((AudioVideo) content);
            } else if (content instanceof Video) {
                item = new JBP_Video((Video) content);
            } else if (content instanceof Audio) {
                item = new JBP_Audio((Audio) content);
            } else {
                throw new IllegalArgumentException("wrong data");
            }
            jpbStorage.getItems().add(item);
        }

        try {
            encoder.setExceptionListener(new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {
                    e.printStackTrace();
                }
            });
            System.out.println(jpbStorage.toString());
            encoder.writeObject(jpbStorage);
            encoder.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean loadJBP() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(".storage/storage.xml");
            XMLDecoder decoder = new XMLDecoder(fis);
            decoder.setExceptionListener(new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {

                }
            });
            JBP_Storage jbpStorage = (JBP_Storage) decoder.readObject();
            decoder.close();
            fis.close();

            Content c;

            for (PersistenceItem k : jbpStorage.getItems()) {
                if (k instanceof JBP_InteractiveVideo) {
                    c = new InteractiveVideoImpl(((JBP_InteractiveVideo) k).getWidth(), ((JBP_InteractiveVideo) k).getHeight(), ((JBP_InteractiveVideo) k).getEncoding(), ((JBP_InteractiveVideo) k).getBitrate(), Duration.ofSeconds(((JBP_InteractiveVideo) k).getLength()), new BigDecimal(((JBP_InteractiveVideo) k).getSize()), ((JBP_InteractiveVideo) k).getAddress(), ((JBP_InteractiveVideo) k).getTags(), new UploaderImpl(((JBP_InteractiveVideo) k).getUploader()), ((JBP_InteractiveVideo) k).getType());
                } else if (k instanceof JBP_LicensedAudioVideo) {
                    c = new LicensedAudioVideoImpl(((JBP_LicensedAudioVideo) k).getSamplingRate(), ((JBP_LicensedAudioVideo) k).getWidth(), ((JBP_LicensedAudioVideo) k).getHeight(), ((JBP_LicensedAudioVideo) k).getEncoding(), ((JBP_LicensedAudioVideo) k).getHolder(), ((JBP_LicensedAudioVideo) k).getBitrate(), Duration.ofSeconds(((JBP_LicensedAudioVideo) k).getLength()), new BigDecimal(((JBP_LicensedAudioVideo) k).getSize()), ((JBP_LicensedAudioVideo) k).getAddress(), ((JBP_LicensedAudioVideo) k).getTags(), new UploaderImpl(((JBP_LicensedAudioVideo) k).getUploader()));
                } else if (k instanceof JBP_LicensedVideo) {
                    c = new LicensedVideoImpl(((JBP_LicensedVideo) k).getWidth(), ((JBP_LicensedVideo) k).getHeight(), ((JBP_LicensedVideo) k).getEncoding(), ((JBP_LicensedVideo) k).getHolder(), ((JBP_LicensedVideo) k).getBitrate(), Duration.ofSeconds(((JBP_LicensedVideo) k).getLength()), new BigDecimal(((JBP_LicensedVideo) k).getSize()), ((JBP_LicensedVideo) k).getAddress(), ((JBP_LicensedVideo) k).getTags(), new UploaderImpl(((JBP_LicensedVideo) k).getUploader()));
                } else if (k instanceof JBP_LicensedAudio) {
                    c = new LicensedAudioImpl(((JBP_LicensedAudio) k).getSamplingRate(), ((JBP_LicensedAudio) k).getEncoding(), ((JBP_LicensedAudio) k).getHolder(), ((JBP_LicensedAudio) k).getBitrate(), Duration.ofSeconds(((JBP_LicensedAudio) k).getLength()), new BigDecimal(((JBP_LicensedAudio) k).getSize()), ((JBP_LicensedAudio) k).getAddress(), ((JBP_LicensedAudio) k).getTags(), new UploaderImpl(((JBP_LicensedAudio) k).getUploader()));
                } else if (k instanceof JBP_AudioVideo) {
                    c = new AudioVideoImpl(((JBP_AudioVideo) k).getSamplingRate(), ((JBP_AudioVideo) k).getWidth(), ((JBP_AudioVideo) k).getHeight(), ((JBP_AudioVideo) k).getEncoding(), ((JBP_AudioVideo) k).getBitrate(), Duration.ofSeconds(((JBP_AudioVideo) k).getLength()), new BigDecimal(((JBP_AudioVideo) k).getSize()), ((JBP_AudioVideo) k).getAddress(), ((JBP_AudioVideo) k).getTags(), new UploaderImpl(((JBP_AudioVideo) k).getUploader()));
                } else if (k instanceof JBP_Video) {
                    c = new VideoImpl(((JBP_Video) k).getWidth(), ((JBP_Video) k).getHeight(), ((JBP_Video) k).getEncoding(), ((JBP_Video) k).getBitrate(), Duration.ofSeconds(((JBP_Video) k).getLength()), new BigDecimal(((JBP_Video) k).getSize()), ((JBP_Video) k).getAddress(), ((JBP_Video) k).getTags(), new UploaderImpl(((JBP_Video) k).getUploader()));
                } else if (k instanceof JBP_Audio) {
                    c = new AudioImpl(((JBP_Audio) k).getSamplingRate(), ((JBP_Audio) k).getEncoding(), ((JBP_Audio) k).getBitrate(), Duration.ofSeconds(((JBP_Audio) k).getLength()), new BigDecimal(((JBP_Audio) k).getSize()), ((JBP_Audio) k).getAddress(), ((JBP_Audio) k).getTags(), new UploaderImpl(((JBP_Audio) k).getUploader()));
                } else {
                    throw new IllegalArgumentException("MediaType was not found");
                }
                this.businessLogic.uploadContent(c);
            }
        } catch (FileNotFoundException | SizeReachedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean saveContent(String address) throws IOException {
        String path = ".storage/content/" + address + ".file";
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Content content = this.businessLogic.getContent(address);
        oos.writeObject(content);
        if (oos != null) {
            oos.close();
        }
        if (fos != null) {
            fos.close();
        }
        return true;
    }

    @Override
    public boolean loadContent(String address) throws IOException, ClassNotFoundException {
        String path = ".storage/content/" + address + ".file";
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Content c = (Content) ois.readObject();

        try {
            this.businessLogic.uploadContent(c);
        } catch (SizeReachedException e) {
            e.printStackTrace();
        }

        if (ois != null) {
            ois.close();
        }

        if (fis != null) {
            fis.close();
        }
        return true;
    }
}
