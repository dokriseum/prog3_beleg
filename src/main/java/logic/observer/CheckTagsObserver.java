/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.observer;

import logic.BusinessLogic;

public class CheckTagsObserver implements Observer {
    private BusinessLogic businessLogic;
    private boolean oldIsSizeMoreAsNinetyPerCent;

    public CheckTagsObserver(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
        this.businessLogic.addObserver(this);
        this.oldIsSizeMoreAsNinetyPerCent = this.businessLogic.observeAreTagsUpdated();
    }

    public boolean isSizeMoreAsNinetyPerCent() {
        this.oldIsSizeMoreAsNinetyPerCent = this.businessLogic.observeAreTagsUpdated();
        return this.oldIsSizeMoreAsNinetyPerCent;
    }

    @Override
    public void update() {
        if (this.isSizeMoreAsNinetyPerCent()) {
            System.out.println("Update\n\tactually size: " + this.businessLogic.getStorageActuallySizeInPercent());
        }
    }
}
