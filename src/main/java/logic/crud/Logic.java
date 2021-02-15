package logic.crud;

import exceptions.ContentNotFoundException;
import models.mediaDB.Content;
import models.mediaDB.Uploadable;

import java.util.List;

public interface Logic {
    boolean uploadContent(Content content);

    boolean uploadContent(Uploadable content);

    Content readContent(String address) throws ContentNotFoundException;

    List<Content> readContents();
}
