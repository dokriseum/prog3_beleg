package logic.persistence.jbp_models;

import java.util.ArrayList;
import java.util.List;

public class JBP_Storage {
    private List<PersistenceItem> items;

    public JBP_Storage() {
        this.items = new ArrayList<>();
    }

    public List<PersistenceItem> getItems() {
        return items;
    }

    public void setItems(List<PersistenceItem> items) {
        this.items = items;
    }
}
