package array;

public class ArraySearchUtil {
    
	/** Do nothing constructor */
	public ArraySearchUtil() {
		// Do nothing
	}
	
	/** Find maximum value in array: O(n) */
	public static <E extends Comparable<? super E>> E findArrayMax(E[] data) {
		int n = data.length;
		E currentMax = data[0];
		for (int j = 1; j < n; j++) {
			if (data[j].compareTo(currentMax) > 0) {
				currentMax = data[j];
			}
		}
		return currentMax;
	}
	
	/** Find minimum value in array O(n) */
	public static <E extends Comparable<? super E>> E findArrayMin(E[] data) {
		int n = data.length;
		E currentMin = data[0];
		for (int j = 1; j < n; j++) {
			if (data[j].compareTo(currentMin) < 0) {
				currentMin = data[j];
			}
		}
		return currentMin;
	}
	
	/** Determine whether or not a number of arrays are disjoint 
	 *  O(n^2) since the third loop is optional
	 *  @param three arrays of integers to test
	 *  @return boolean : indicates whether or not disjointness exists */ 
	public static boolean areDisjoint(int[] groupA, int[] groupB, int[] groupC) {
		for (int a : groupA) {
			for (int b : groupB) {
				if (a == b) {              // a potential common value is found
					for (int c : groupC) {
						if (a == c) {
							return false;  // we have a common value
						}
					}
				}
			}
		}
		return true; // the sets are three way disjoint if we reach here
	}
	
	
	/** Test driver */
	public static void main(String args[]) {
		Double[] dArray = { 0.0, 1.1, 5.5, 4.4, -1.1};
		int[] set1 = { 1, 3, 5, 7, 9 };
		int[] set2 = { 0, 2, 4, 6, 8 };
		int[] set3 = { 10, 11, 12, 13 };
		
		System.out.println("The maximum value is " + findArrayMax(dArray));
		System.out.println("The minimum value is " + findArrayMin(dArray));
		System.out.println("These three arrays are disjoint: " + areDisjoint(set1, set2, set3));
	}
}
