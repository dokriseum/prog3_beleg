package logic.persistence;

import java.io.IOException;

public interface Persistence {

    boolean saveJOS() throws IOException;

    boolean loadJOS() throws IOException, ClassNotFoundException;

    boolean saveJBP() throws IOException;

    boolean loadJBP() throws IOException, ClassNotFoundException;

    boolean saveContent(String address) throws IOException;

    boolean loadContent(String address) throws IOException, ClassNotFoundException;
}
