/*
 * Name: Liora Levi
 * ID: 326037488
 * Date: 13.01.2026
 */

import java.util.Random;
import java.util.Scanner;

public class MatrixMultiplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		System.out.println("Enter dimensions for Matrix A (n x m):");
		System.out.print("n = ");
		int n = scanner.nextInt();
		System.out.print("m = ");
		int m = scanner.nextInt();

		System.out.println("Enter dimension p for Matrix B (m x p):");
		System.out.print("p = ");
		int p = scanner.nextInt();

		// 2. Initialize matrices
		int[][] A = new int[n][m];
		int[][] B = new int[m][p];

		// Fill and print A
		System.out.println("\nMatrix A:");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				A[i][j] = random.nextInt(11); // 0 to 10
				System.out.print(A[i][j] + "\t");
			}
			System.out.println();
		}

		// Fill and print B
		System.out.println("\nMatrix B:");
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < p; j++) {
				B[i][j] = random.nextInt(11); // 0 to 10
				System.out.print(B[i][j] + "\t");
			}
			System.out.println();
		}

		System.out.println("\nCalculating Result (Please wait)...\n");

		// The monitor ensures threads print in the correct sequential order
		ResultMonitor monitor = new ResultMonitor();

		int threadId = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < p; j++) {
				// Each thread calculates cell [i][j] of the result
				WorkerThread worker = new WorkerThread(threadId, i, j, m, A, B, monitor);
				worker.start();
				threadId++;
			}
		}

		scanner.close();
	}
}