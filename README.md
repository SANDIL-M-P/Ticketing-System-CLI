# Ticketing-System-CLI
# Concurrent Ticket Booking System

## Overview

A Java-based concurrent ticket booking simulation that demonstrates multi-threaded ticket sales with vendor and customer interactions.

## Features

- Concurrent ticket sales simulation
- Configurable ticket release and retrieval rates
- Dynamic system configuration
- JSON-based configuration management
- Logging of system activities

## Prerequisites

- Java Development Kit (JDK) 11+
- Maven
- Google Gson library

## Dependencies

- java.util.Scanner
- java.util.logging.Logger
- com.google.gson.Gson

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/ticket-booking-system.git
   cd ticket-booking-system
   ```

2. Build the project:
   ```bash
   mvn clean package
   ```

## Configuration

The system uses a `config.json` file with the following parameters:
- Maximum ticket capacity
- Total tickets to sell
- Ticket release rate
- Customer retrieval rate

## Running the Application

```bash
java -jar target/ticket-booking-system.jar
```

### Main Menu Options

1. **Start System**: Begin ticket sales simulation
2. **Configure Settings**: Modify system parameters
3. **Exit**: Close the application

## System Components

- `Main`: Application entry point
- `Configuration`: Manages system settings
- `TicketPool`: Handles ticket inventory
- `Vendor`: Manages ticket release
- `Customer`: Simulates ticket purchasing
- `Ticket`: Represents individual tickets

## Example Workflow

1. Launch the application
2. Configure system settings (if needed)
3. Start the system
4. Observe ticket sales simulation
5. System automatically stops when ticket capacity is reached

## Logging

- Uses Java's logging framework
- Console logs provide real-time system status

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.
