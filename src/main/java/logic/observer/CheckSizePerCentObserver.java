/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.observer;

import logic.BusinessLogic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class CheckSizePerCentObserver implements Observer {
    private BusinessLogic businessLogic;
    private boolean oldIsSizeMoreAsNinetyPerCent;

    public CheckSizePerCentObserver(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
        this.businessLogic.addObserver(this);
        this.oldIsSizeMoreAsNinetyPerCent = this.businessLogic.observeIsSizeMoreAsNinetyPercent();
    }

    public boolean isSizeMoreAsNinetyPerCent() {
        this.oldIsSizeMoreAsNinetyPerCent = this.businessLogic.observeIsSizeMoreAsNinetyPercent();
        return this.oldIsSizeMoreAsNinetyPerCent;
    }

    @Override
    public void update() {
        if (this.isSizeMoreAsNinetyPerCent()) {
            OutputStreamWriter osr = new OutputStreamWriter(System.out);
            BufferedWriter bw = new BufferedWriter(osr);
            //System.out.println(this.toString());
            try {
                bw.write(this.toString());
                bw.flush();
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "WARNING\n\tactually size: " + this.businessLogic.getStorageActuallySizeInPercent();
    }
}
