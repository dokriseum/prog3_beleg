package logic.persistence;

import models.storage.Storage;

import java.io.IOException;

public interface Persistence {
    boolean save(PersistenceType type, Storage storage) throws IOException;

    boolean load(PersistenceType type) throws IOException, ClassNotFoundException;
}
