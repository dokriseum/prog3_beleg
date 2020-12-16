/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.observer.simulation;

import logic.BusinessLogic;
import logic.observer.Observer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;

public class CheckSimulationObserver implements Observer {
    private BusinessLogic businessLogic;
    private boolean oldCheckStorageForSimulation;
    private OutputStreamWriter osw;
    private BigDecimal sizeStorage;

    public CheckSimulationObserver(BusinessLogic businessLogic, OutputStreamWriter osw) {
        this.businessLogic = businessLogic;
        this.osw = osw;
        this.businessLogic.addObserver(this);
        this.oldCheckStorageForSimulation = false;
        this.sizeStorage = businessLogic.getStorage().getSize();
    }

    public synchronized boolean isStorageEdited() {
        BigDecimal tempSize = this.businessLogic.getStorage().getSize();
        if (this.sizeStorage.compareTo(tempSize) != 0) {
            this.oldCheckStorageForSimulation = false;
        } else {
            this.oldCheckStorageForSimulation = true;
        }
        return this.oldCheckStorageForSimulation;
    }

    @Override
    public void update() {
        if (this.isStorageEdited()) {
            try {
                this.print(new BufferedWriter(this.osw));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void print(BufferedWriter bw) throws IOException {
        bw.write(this.printCheckIfAddOrDel());
        bw.flush();
        bw.newLine();

        // bw.close();
    }

    private synchronized String printCheckIfAddOrDel() {
        if (this.businessLogic.getStorage().getSize().compareTo(this.sizeStorage) > 0) {
            return "A content was added.";
        } else if (this.businessLogic.getStorage().getSize().compareTo(this.sizeStorage) < 0) {
            return "A content was deleted.";
        } else {
            return "";
        }
    }
}
