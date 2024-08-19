# IOReactorDemo

## Overview

This repository demonstrates the functionalities of an I/O Reactor using Java NIO. The I/O Reactor pattern is a powerful design pattern that efficiently handles multiple I/O operations without blocking the main thread. It's widely used in high-performance applications, such as network servers, where scalability and responsiveness are critical.

## Features

- **Non-Blocking I/O:** Utilizes Java NIO's non-blocking capabilities to manage multiple channels simultaneously.
- **Reactor Pattern Implementation:** Demonstrates how the Reactor Pattern can be used to handle multiple concurrent events efficiently.
- **Event Handling:** Showcases how different types of events (e.g., reading, writing, accepting connections) are processed using Event Handlers.
- **Scalability:** Designed to handle a large number of simultaneous connections with minimal resource usage.

## Components

### 1. Event Demultiplexer (Selector)
- Acts as the traffic cop, monitoring multiple channels for events and directing them to the appropriate handler.

### 2. Reactor
- The decision-maker that dispatches events to the correct handler based on the type of event.

### 3. Event Handlers
- The problem solvers that perform specific tasks, such as reading data, writing responses, or accepting new connections.

### 4. Initiation Dispatcher
- The organizer that registers event sources with the Selector and pairs them with the correct Event Handlers.

### 5. Handles/Channels
- The pipelines through which data flows, representing the actual I/O resources (e.g., sockets).

## Getting Started

### Prerequisites

- **Java JDK 8 or higher**: Ensure that you have JDK 8 or a more recent version installed on your machine.
- **Maven**: The project is built using Maven, so you'll need Maven installed to compile and run the application.

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/dilshanfardil/IOReactorDemo.git
   cd IOReactorDemo
2. Run the ReactorManager First
3. Then Run the Client Application Second

### Example Usage
Once the application is running, it will simulate multiple I/O events, such as incoming connections or data transfers, and demonstrate how the Reactor Pattern processes these events efficiently.

### Understanding the Code

- **Reactor.java**: The core class that implements the Reactor Pattern. It sets up the Selector, registers channels, and dispatches events to the appropriate handlers.
- **EventHandler.java**: Defines the interface for event handlers, with implementations for handling different types of I/O events.
- **Server.java**: A simple server example that uses the Reactor to accept and process client connections.
- **Client.java**: A client simulation that connects to the server and sends data, triggering the Reactor's event handling.