# Java Multithreading & Synchronization Projects

This repository contains advanced Java applications demonstrating concurrent programming, thread synchronization, and real-time GUI updates.

## 🛠️ Core Technologies
*   **Language:** Java
*   **Concepts:** Multithreading, Thread Synchronization, Custom Monitors, Concurrency Control.
*   **GUI:** JavaFX (for real-time thread simulation).

## 📁 Projects Overview

### 1. Multithreaded Matrix Multiplication
A robust application that performs matrix multiplication concurrently using multiple threads.
*   **Dynamic Generation:** Accepts user input for dimensions and generates matrices with random values[cite: 6].
*   **Concurrent Execution:** Spawns a dedicated `WorkerThread` to calculate the dot product for each specific cell of the resulting matrix[cite: 6, 8].
*   **Strict Output Synchronization:** Implements a custom `ResultMonitor` using `wait()` and `notifyAll()` to ensure the final output is printed in strict sequential order based on thread ID, despite asynchronous calculation[cite: 7].

### 2. Traffic Light Synchronization Simulation
A real-time graphical simulation of a four-way intersection traffic light system, built with JavaFX.
*   **Independent Threading:** Utilizes a dedicated daemon thread to control the timing and state transitions (Red, Green, Blinking) of both vehicle and pedestrian traffic lights[cite: 9, 10].
*   **Thread-Safe GUI:** Implements `Platform.runLater()` to safely push UI updates from the background simulation thread to the JavaFX Application Thread[cite: 10].
*   **Dependency Management:** Accurately simulates the dependencies between intersecting traffic signals and coordinates pedestrian crossing logic (e.g., blinking green for pedestrians when vehicle light is red)[cite: 9, 10].

## 🚀 Getting Started

**For Matrix Multiplication:**
1. Compile: `javac MatrixMultiplication.java ResultMonitor.java WorkerThread.java`
2. Run: `java MatrixMultiplication`

**For Traffic Light Simulation:**
1. Ensure JavaFX is configured in your environment.
2. Compile: `javac --module-path /path/to/javafx/lib --add-modules javafx.controls TrafficLightSim.java TrafficLight.java`
3. Run (Optional args for Green/Red duration in ms): `java --module-path /path/to/javafx/lib --add-modules javafx.controls TrafficLightSim 3000 2000`
