package org.example;

public class Ticket {
    private static int ticketCounter = 1; // To generate unique ticket IDs
    private final int ticketId;

    public Ticket() {
        this.ticketId = ticketCounter++;
    }

    public int getTicketId() {
        return ticketId;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId;
    }
}

