import java.util.*;
import java.io.*;

public class Lake {

	// Elevations
	private int[][] elevations;
	// Instructions
	private int[][] instructions;

	private int R, C, E, N;

	// constructor, reads in file
	public Lake(String path) {
		BufferedReader br = null;
		String line = null;
		int lineNum = 0;
		String[] parts = null;
		try {
			br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) {
				parts = line.split(" ");

				if (lineNum == 0) {
					R = Integer.parseInt(parts[0]);
					C = Integer.parseInt(parts[1]);
					E = Integer.parseInt(parts[2]);
					N = Integer.parseInt(parts[3]);
					elevations = new int[R][C];
					instructions = new int[N][3];
				}

				else if (lineNum <= R) {
					for (int i = 0 ; i < C ; i++) {
						elevations[lineNum - 1][i] = Integer.parseInt(parts[i]);
					}
				}

				else {
					for (int i = 0 ; i < 3 ; i++) {
						instructions[lineNum - R - 1][i] = Integer.parseInt(parts[i]);
					}
				}

				lineNum++;
			}
		} catch (Exception e) {
			System.out.println("Oops, something went wrong!");
			e.printStackTrace();
		}
	}

	public String toString() {
		String s = "";
		for (int i = 0 ; i < R ; i++) {
			for (int j = 0 ; j < C ; j++) {
				s += elevations[i][j] + " ";
			}
			s += "\n";
		}

		return s;
	}

	public void performInstructions() {
		for (int[] i : instructions) {
			int currentMax = 0;
			for (int y = i[0] - 1 ; y < i[0] + 2 ; y++) {
				for (int x = i[1] - 1 ; x < i[1] + 2 ; x++) {
					if (elevations[y][x] > currentMax) {
						currentMax = elevations[y][x];
					}
				}
			}
			currentMax -= i[2];
			for (int y = i[0] - 1 ; y < i[0] + 2 ; y++) {
				for (int x = i[1] - 1; x < i[1] + 2 ; x++) {
					if (elevations[y][x] > currentMax) {
						elevations[y][x] = currentMax;
					}
				}
			}
		}
	}

	public int calculateLake() {
		int sum = 0;
		for (int y = 0 ; y < R ; y++) {
			for (int x = 0 ; x < C ; x++) {
				elevations[y][x] = E - elevations[y][x];
				if (elevations[y][x] < 0) {
					elevations[y][x] = 0;
				}
				sum += elevations[y][x];
			}
		}

		return sum * 12 * 12 * 6 * 6;
	}

	public static void main(String[] args) {
		Lake lk = new Lake(args[0]);
		lk.performInstructions();
		int ans = lk.calculateLake();
		System.out.println(ans);
	}
}
