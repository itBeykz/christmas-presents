package bg.sofia.uni.fmi.mjt.christmas;

public class Elf extends Thread {

    private int id;
    private WorkShop workShop;
    private int giftsCrafted;

    public Elf(int id, WorkShop workshop) throws UnsupportedOperationException {
        this.id = id;
        this.workShop = workshop;
    }

    /**
     * Gets a wish from the backlog and creates the wanted gift.
     **/
    public void craftGift() throws UnsupportedOperationException {
        Gift gift = null;
        while ((gift = workShop.nextGift()) != null) {
            try {
                Thread.sleep(gift.getCraftTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            giftsCrafted++;
        }
        System.out.println("Elf #" + id + " created " + giftsCrafted + " gifts");

    }

    /**
     * Returns the total number of gifts that the given elf has crafted.
     **/
    public int getTotalGiftsCrafted() throws UnsupportedOperationException {
        return giftsCrafted;
    }

    @Override
    public void run() {
        craftGift();
    }
}
