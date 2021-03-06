import java.util.*;
import java.io.*;

public class Driver {

	public static void main(String[] args) {
		/*
		ArrayList<Integer> toBeSorted = new ArrayList<Integer>();
		Random randgen = new Random();
		for (int i = 0 ; i < Integer.parseInt(args[0]) ; i++) {
			toBeSorted.add(randgen.nextInt(Integer.parseInt(args[1])));
		}

		System.out.println(toBeSorted.toString());
		
		long timeI = System.currentTimeMillis();
		ArrayList<Integer> sorted = MergeSort.mergeSort(toBeSorted);
		long timeE = System.currentTimeMillis();
		
		System.out.println(sorted.toString());
		System.out.println(timeE - timeI);
		*/

		int[] toBeSorted = new int[Integer.parseInt(args[0])];
        Random randgen = new Random();
        for (int i = 0 ; i < Integer.parseInt(args[0]) ; i++) {
            toBeSorted[i] = randgen.nextInt(Integer.parseInt(args[1]));
        }

		System.out.println(Arrays.toString(toBeSorted));
		
		long timeI = System.currentTimeMillis();
		int[] result = MergeSort.mergeSort(toBeSorted);
		long timeE = System.currentTimeMillis();

        System.out.println(Arrays.toString(result));
	}
}
