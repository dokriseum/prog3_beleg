package logic.persistence;

import models.storage.Storage;

import java.io.IOException;

public interface Persistence {
    boolean save(String path, Storage storage) throws IOException;

    Object load(String path) throws IOException, ClassNotFoundException;
}
