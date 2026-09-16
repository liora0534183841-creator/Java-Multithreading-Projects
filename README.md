# Multithreaded Matrix Multiplication

A robust Java application that performs matrix multiplication concurrently using multiple threads, demonstrating advanced concepts of Concurrency, Thread Synchronization, and custom Monitor implementation.

## 🛠️ Core Technologies
*   **Language:** Java
*   **Concepts:** Multithreading, Thread Synchronization, Custom Monitors, Concurrency Control.

## ⚙️ How It Works
*   **Dynamic Matrix Generation:** The application accepts user input for matrix dimensions (n x m and m x p) and dynamically generates the matrices with random values[cite: 6].
*   **Concurrent Execution:** A dedicated worker thread (`WorkerThread`) is spawned to independently calculate the dot product for each specific cell of the resulting matrix[cite: 6, 8].
*   **Strict Output Synchronization:** A custom `ResultMonitor` is implemented using `wait()` and `notifyAll()` mechanisms[cite: 7]. This ensures that even though threads calculate their results asynchronously, the final output is printed in strict sequential order based on thread ID[cite: 7].

## 🚀 Getting Started
1. Clone the repository: `git clone https://github.com/YourUsername/Multithreaded-Matrix-Multiplication.git`
2. Compile the Java files: `javac *.java`
3. Run the main application: `java MatrixMultiplication`
4. Follow the on-screen prompts to enter the desired matrix dimensions.
