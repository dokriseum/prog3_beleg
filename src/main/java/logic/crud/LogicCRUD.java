package logic.crud;

import exceptions.ContentNotFoundException;
import models.mediaDB.Content;
import models.mediaDB.Uploadable;
import models.storage.Storage;
import models.storage.StorageContent;

import java.math.BigDecimal;
import java.util.List;

public class LogicCRUD implements Logic {
    private Storage storage;

    public LogicCRUD(Storage storage) {
        this.storage = storage;
    }

    public LogicCRUD(BigDecimal size) {
        this.storage = new StorageContent(size);
    }

    @Override
    public boolean uploadContent(Content content) {
        this.storage.getListContent().add(content);
        return true;
    }

    @Override
    public boolean uploadContent(Uploadable content) {
        this.storage.getListContent().add((Content) content);
        return true;
    }

    @Override
    public Content readContent(String address) throws ContentNotFoundException {
        for (Content k : this.storage.getListContent()) {
            if (k.getAddress().equals(address)) {
                return k;
            }
        }
        throw new ContentNotFoundException();
    }

    @Override
    public List<Content> readContents() {
        return this.storage.getListContent();
    }
}
