package org.example;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final String CONFIG_FILE = "config.json";
    private static Configuration config;
    private static TicketPool ticketPool;
    private static Thread vendorThread;
    private static Thread customerThread1;
    private static Thread customerThread2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Load configuration or prompt for input if not found
            config = Configuration.loadConfig(CONFIG_FILE);
            if (config == null) {
                logger.info("Configuration input completed.");
                config = Configuration.promptForConfig(); // Prompt user for input
                Configuration.saveConfig(config, CONFIG_FILE);  // Save to file
                logger.info("Configuration saved to " + CONFIG_FILE);
            }

            // Initialize TicketPool with the configuration
            ticketPool = new TicketPool(config.getMaxTicketCapacity(), config.getTotalTickets());

            // Main menu loop
            String userInput;
            do {
                System.out.println("\n--- Main Menu ---");
                System.out.println("1. Start System");
                System.out.println("2. Configure Settings");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                userInput = scanner.nextLine();

                switch (userInput) {
                    case "1":
                        startSystem();
                        break;
                    case "2":
                        configureSettings(scanner);
                        break;
                    case "3":
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (!userInput.equals("3"));
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void startSystem() {
        try {
            // Reset the ticket pool before starting
            ticketPool.reset();

            logger.info("Starting VendorThread...");
            // Start vendor and customer threads
            vendorThread = new Thread(new Vendor(ticketPool, config.getTicketReleaseRate(), config.getMaxTicketCapacity()));

            logger.info("Starting CustomerThread1...");
            customerThread1 = new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate(), config.getMaxTicketCapacity()));

            logger.info("Starting CustomerThread2...");
            customerThread2 = new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate(), config.getMaxTicketCapacity()));

            vendorThread.start();
            customerThread1.start();
            customerThread2.start();

            vendorThread.join();
            logger.info("VendorThread has finished.");

            customerThread1.join();
            logger.info("CustomerThread1 has finished.");

            customerThread2.join();
            logger.info("CustomerThread2 has finished.");

            logger.info("Ticket booking process completed.");
        } catch (InterruptedException e) {
            System.err.println("Thread interruption error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error while starting the system: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void configureSettings(Scanner scanner) {
        try {
            // Re-prompt for new configuration values and save them
            config = Configuration.promptForConfig();
            Configuration.saveConfig(config, CONFIG_FILE);

            // Re-initialize the TicketPool with updated settings
            ticketPool = new TicketPool(config.getMaxTicketCapacity(), config.getTotalTickets());
            System.out.println("Configuration saved.");
        } catch (Exception e) {
            System.err.println("Error while configuring settings: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

