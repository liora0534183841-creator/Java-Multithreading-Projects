/*
 * Name: Liora Levi
 * ID: 326037488
 * Date: 13.01.2026
 */

/**
 * Thread class that performs the vector multiplication for a specific cell.
 */
public class WorkerThread extends Thread {
	private final int id;
	private final int row;
	private final int col;
	private final int commonDim; // 'm' dimension
	private final int[][] matrixA;
	private final int[][] matrixB;
	private final ResultMonitor monitor;

	public WorkerThread(int id, int row, int col, int commonDim, int[][] matrixA, int[][] matrixB, ResultMonitor monitor) {
		this.id = id;
		this.row = row;
		this.col = col;
		this.commonDim = commonDim;
		this.matrixA = matrixA;
		this.matrixB = matrixB;
		this.monitor = monitor;
	}

	@Override
	public void run() {
		int sum = 0;
		// Calculate dot product of row from A and column from B
		for (int k = 0; k < commonDim; k++) {
			sum += matrixA[row][k] * matrixB[k][col];
		}

		// Send result to monitor to handle synchronized printing
		monitor.printResult(id, sum, row, col);
	}
}