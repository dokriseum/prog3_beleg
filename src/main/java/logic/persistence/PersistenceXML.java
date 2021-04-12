package logic.persistence;

import logic.persistence.jbp_models.*;
import models.mediaDB.*;

import java.awt.*;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class PersistenceXML {
    public void save(Content content, String address) {
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

        File file = new File("storage.xml");
        file.delete();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.setExceptionListener(new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {
                    e.printStackTrace();
                }
            });
            System.out.println(item.toString());
            encoder.writeObject(item);
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
    }

    public Content load(Content content, String address) {
        try {
            FileInputStream fis = new FileInputStream("Content.xml");
            XMLDecoder decoder = new XMLDecoder(fis);
            decoder.setExceptionListener(new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {

                }
            });
            //if wie bei save() und dann decoder.readObject(); mit cast zu gefifter Objektart

            decoder.close();
            //Content loadedContent = (Content) convertSerializableToContent(JBP_Content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
