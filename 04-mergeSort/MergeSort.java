import java.util.*;
import java.io.*;

public class MergeSort {
	
	public static ArrayList<Integer> mergeSort(ArrayList<Integer> data) {
		if (data.size() == 1) {
			System.out.println("Result: " + data.toString());
			return data;
		}

		else {
			ArrayList<Integer> A = new ArrayList<Integer>();
			ArrayList<Integer> B = new ArrayList<Integer>();

			for (int i = 0 ; i < data.size() / 2 ; i++) {
				A.add(data.get(i));
			}
			for (int i = data.size() / 2 ; i < data.size() ; i++) {
				B.add(data.get(i));
			}

			ArrayList<Integer> AS = mergeSort(A);
			ArrayList<Integer> BS = mergeSort(B);
			//System.out.println("A: " + AS.toString());
			//System.out.println("B: " + BS.toString());
			//System.out.println("Result: " + merge(AS,BS).toString());
			return merge(AS, BS);
		}
	}

	public static ArrayList<Integer> merge(ArrayList<Integer> A, ArrayList<Integer> B) {
		ArrayList<Integer> result = new ArrayList<Integer>();

		while(A.size() != 0 && B.size() != 0) {
			if (A.get(0) > B.get(0)) {
				result.add(B.remove(0));
			}
			else {
				result.add(A.remove(0));
			}
		}

		result.addAll(A);
		result.addAll(B);
		
		return result;
	}
}
