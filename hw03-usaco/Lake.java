import java.uitl.*;
import java.io.*;

public class Lake {

	// Elevations
	private int[][] elevations;
	// Instructions
	private int[][] instructions;

	private int R, C, E, N;

	// constructor, reads in file
	public lake(String path) {
		BufferedReader br = null;
		String line = null;
		int lineNum = 0;
		String[] parts = null;
		try {
			br = new BufferedReader(new FileReader(path));
			line = br.nextLine();
			while (line != null) {
				parts = line.split(" ");
				if (lineNum == 0) {
					R = Integer.parseInt(parts[0]);
					C = Integer.parseInt(parts[1]);
					E = Integer.parseInt(parts[2]);
					N = Integer.parseInt(parts[3]);
					elevations = new int[R][C];
					instructions = new int[N][3];
				}
				if (lineNum <= R) {
					for (int i = 0 ; i < C ; i++) {
						elevations[lineNum - 1][i] = parts[i];
					}
				}

				else {
					for (int i = 0 ; i < 3 ; i++) {
						instructions[lineNum - R - 1][i] = parts[i];
					}
				}
				lineNum++;
			}
		}
	}

	public void performInstructions() {
		for (int[] i : instructions) {
			int currentMax = 0;
			for (int y = i[0] ; y < i[0] + 3 ; y++) {
				for (int x = i[1] ; x < i[1] + 3 ; x++) {
					if (elevations[y][x] > currentMax) {
						currentMax = elevations[y][x];
`					}
				}
			}
			currentMax -= i[2];
			for (int y = i[0] ; y < i[0] + 3 ; y++) {
				for (int x = i[1] ; x < i[1] + 3 ; x++) {
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
				elevations[y][x] -= E;
				if (elevations[y][x] < 0) {
					elevations[y][x] = 0;
				}
				sum += elevations[y][x];
			}
		}

		return sum * 12 * 12 * 6 * 6;
	}

	public static void Main(String[]		L
	}
}
