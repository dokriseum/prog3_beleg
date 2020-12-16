/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package logic.simulation;

import exceptions.EmptyStorageException;
import logic.BusinessLogic;
import models.mediaDB.Content;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContentDeleter implements Runnable {
    private BusinessLogic businessLogic;
    private List<String> listContentAddressFromStorage;
    private Random random;
    private String randomAddress;
    private boolean wasNeverInvoked = true;

    public ContentDeleter(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
        this.listContentAddressFromStorage = new ArrayList<>();
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this.businessLogic.getStorage()) {
                    this.deleteContent();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteContent() throws InterruptedException {
        //System.out.println("DELETE " + businessLogic.getStorage());
        if (wasNeverInvoked) {
            businessLogic.getStorage().wait();
            wasNeverInvoked = false;
        }

        try {

            this.fillCollections();
            if ((!(this.listContentAddressFromStorage.isEmpty())) || (!(this.listContentAddressFromStorage.size() <= 0))) {
                this.randomAddress = this.listContentAddressFromStorage.get(this.random.nextInt(this.listContentAddressFromStorage.size()));
                Content tempContent = this.businessLogic.getContent(this.randomAddress);
                this.businessLogic.deleteContent(this.randomAddress);
                System.out.println("DELETE: " + tempContent.toString());
            }

        } catch (EmptyStorageException e) {
            System.err.println("Empty");
        }
        businessLogic.getStorage().notify();
        businessLogic.getStorage().wait();
    }

    private void fillCollections() throws EmptyStorageException {
        if ((this.businessLogic.getStorage().getCapacity().equals(BigDecimal.ZERO)) || (this.businessLogic.getStorage().getListContent().size() <= 0)) {
            throw new EmptyStorageException();
        }
        this.listContentAddressFromStorage = new ArrayList<>();
        for (Content k : this.businessLogic.getStorage().getListContent()) {
            this.listContentAddressFromStorage.add(k.getAddress());
        }
    }
}
