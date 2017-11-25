package sorts;

/** SortUtil contains all of the useful sorts that I have written for myself
 * @author HumanD1ffM4chin3
 * @since 12/23/2016
 * @version 1.0 */
public class SortUtil {
    
	/** Do nothing constructor */
	public SortUtil() { }
	
	/** Insertion sort shifts items and then inserts new entries
	 * in their proper places. Runs in O(n^2). 
	 * This method works for objects only. */
	public static <E extends Comparable<? super E>> E[] insertionSort(E[] data) {
		int n = data.length;
    	for (int k = 1; k < n; k++) {         // Begin with second char
    		E cur = data[k];               
    		int j = k;                        // Begin looking for correct insertion
    		while(j > 0 &&  data[j-1].compareTo(cur) > 0) {
    			data[j] = data[j-1];          // Slide data[j-1] to the right
    			j--;
    		}
    		data[j] = cur;                    // Insert in the newly vacated place
    	}
    	return data;
	}
	
	/** Insertion sort for chars because there isn't an object version. 
	 *  This is an overloaded method. Add others as needed. */
	public static char[] insertionSort(char[] data) {
		int n = data.length;
    	for (int k = 1; k < n; k++) {         // Begin with second char
    		char cur = data[k];               
    		int j = k;                        // Begin looking for correct insertion
    		while(j > 0 &&  data[j-1] > cur) {
    			data[j] = data[j-1];          // Slide data[j-1] to the right
    			j--;
    		}
    		data[j] = cur;                    // Insert in the newly vacated place
    	}
    	return data;
	}
	
	/** Test driver for the class */
	public static void main(String args[]) {
		char[] chars = "asdfjklwenrm".toCharArray();
		System.out.println(SortUtil.insertionSort(chars));
		
		Integer[] ints = { 1, 3, 5, 2, 4 };
		SortUtil.insertionSort(ints);
		for (int i = 0; i < ints.length; i++) {
		    System.out.println(ints[i]);
		}
	}
}
