package org.example;

import java.util.logging.Logger;

public class Customer implements Runnable {
    private static final Logger logger = Logger.getLogger(Customer.class.getName());
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;
    private static int customerCounter = 1;

    public Customer(TicketPool ticketPool, int customerRetrievalRate, int maxTicketCapacity) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        try {
            while (ticketPool.canReleaseMoreTickets() || ticketPool.getAvailableTickets() > 0) {
                if (ticketPool.getAvailableTickets() > 0) {
                    ticketPool.retrieveTickets();
                }
                Thread.sleep(customerRetrievalRate);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            logger.info("Customer has stopped buying tickets.");
        }
    }
}

