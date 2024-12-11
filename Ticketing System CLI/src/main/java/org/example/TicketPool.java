package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class TicketPool {
    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());
    private final List<Ticket> tickets;
    private final int maxTicketCapacity;
    private int totalTicketsSold = 0;
    private int totalTicketsReleased = 0;

    public TicketPool(int maxTicketCapacity, int initialTickets) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.tickets = new LinkedList<>();
    }

    public synchronized void releaseTickets() {
        int numTickets = Math.min(5, maxTicketCapacity - totalTicketsReleased);

        if (numTickets > 0) {
            for (int i = 0; i < numTickets; i++) {
                tickets.add(new Ticket());
            }
            totalTicketsReleased += numTickets;

            logger.info("Vendor released " + numTickets + " tickets. Available tickets: " + tickets.size());
            logger.info("Total tickets released so far: " + totalTicketsReleased + " out of " + maxTicketCapacity);

            if (totalTicketsReleased >= maxTicketCapacity) {
                logger.info("Maximum ticket capacity reached. Vendor stops selling tickets.");
            }
        }
    }

    public synchronized Ticket retrieveTickets() {
        if (!tickets.isEmpty()) {
            Ticket ticket = tickets.remove(0);
            totalTicketsSold++;

            logger.info("Customer bought 1 tickets. Available tickets: " + tickets.size());

            return ticket;
        }
        return null;
    }

    public synchronized int getAvailableTickets() {
        return tickets.size();
    }

    public synchronized boolean canReleaseMoreTickets() {
        return totalTicketsReleased < maxTicketCapacity;
    }

    public synchronized int getTotalTicketsReleased() {
        return totalTicketsReleased;
    }

    public synchronized void reset() {
        tickets.clear();
        totalTicketsSold = 0;
        totalTicketsReleased = 0;
    }
}


