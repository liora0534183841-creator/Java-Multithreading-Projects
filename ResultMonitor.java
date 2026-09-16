/*
 * Name: Liora Levi
 * ID: 326037488
 * Date: 13.01.2026
 */

/**
 * Monitor class to synchronize printing order.
 * Ensures thread k prints only after thread k-1 has finished printing.
 */
public class ResultMonitor {
	private int currentTurn;

	public ResultMonitor() {
		this.currentTurn = 0;
	}

	public synchronized void printResult(int threadId, int result, int row, int col) {
		// Wait until it is this thread's turn to print
		while (threadId != currentTurn) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Critical section: Print the result
		System.out.println("Result[" + row + "," + col + "] = " + result);

		// Update turn and notify all waiting threads
		currentTurn++;
		notifyAll();
	}
}