package bg.sofia.uni.fmi.mjt.christmas;

import java.util.Random;

public class Kid implements Runnable {

    private WorkShop workShop;
    private static Random random = new Random();
    private static final int TAKES_TIME = 1000;

    public Kid(WorkShop workShop) {
        this.workShop = workShop;

    }

    /**
     * Sends a wish for the given gift to Santa's workshop.
     **/
    public void makeWish(Gift gift) throws UnsupportedOperationException{
        gift = Gift.getGift();
        workShop.postWish(gift);

    }

    @Override
    public void run() throws UnsupportedOperationException{
        try {
            Thread.sleep(random.nextInt(TAKES_TIME));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        makeWish(Gift.getGift());
    }
}
