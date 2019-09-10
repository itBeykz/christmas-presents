package bg.sofia.uni.fmi.mjt.christmas;

import java.util.LinkedList;
import java.util.Queue;

public class WorkShop {

    private final int NUMBER_OF_ELVES = 20;
    private Queue<Gift> elvesBackLog = new LinkedList<>();
    private Elf[] elves;
    private boolean isChristmasTime = false;
    private int wishes;

    public WorkShop() throws UnsupportedOperationException {
        startWork();
    }

    /**
     * Adds a gift to the elves' backlog.
     **/
    public synchronized void postWish(Gift gift) throws UnsupportedOperationException {
        elvesBackLog.add(gift);
        wishes++;
        this.notify();
    }

    /**
     * Returns the next gift from the elves' backlog that has to be manufactured.
     **/
    public synchronized Gift nextGift() throws UnsupportedOperationException {
        while (!isChristmasTime && elvesBackLog.isEmpty())
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        if (isChristmasTime || elvesBackLog.isEmpty())
            return null;
        else
            return elvesBackLog.poll();
    }

    /**
     * Returns an array of the elves working in Santa's workshop.
     **/
    public Elf[] getElves() throws UnsupportedOperationException {
        return this.elves;
    }

    /**
     * Returns the total number of wishes sent to Santa's workshop by the kids.
     **/
    public int getWishCount() throws UnsupportedOperationException {
        return this.wishes;
    }

    private void startWork() {
        this.elves = new Elf[NUMBER_OF_ELVES];

        for (int i = 0; i < NUMBER_OF_ELVES; i++) {
            elves[i] = new Elf(i, this);
            elves[i].start();
        }
    }

    public void itsChristmasTime(){
        isChristmasTime=true;
        this.notifyAll();
    }
}

