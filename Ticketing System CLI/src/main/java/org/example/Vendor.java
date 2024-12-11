package org.example;

import java.util.logging.Logger;

public class Vendor implements Runnable {
    private static final Logger logger = Logger.getLogger(Vendor.class.getName());
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final int maxTicketCapacity;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate, int maxTicketCapacity) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    @Override
    public void run() {
        try {
            while (ticketPool.canReleaseMoreTickets()) {
                ticketPool.releaseTickets();
                logger.info("Vendor waiting for the next ticket release. Current release rate: " + ticketReleaseRate + " ms.");

                Thread.sleep(ticketReleaseRate);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            logger.info("Vendor has stopped selling tickets.");
        }
    }
}
