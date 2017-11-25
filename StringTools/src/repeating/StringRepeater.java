package repeating;

/**
 * This class contains methods useful for causing strings to repeat
 * in some way
 * @author HumanD1ffM4chin3
 *
 */
public class StringRepeater {
    
	/** Do nothing constructor */
	public StringRepeater() {
    	// do nothing
    }
	
	/** The repeater method takes a character and repeats it for a
	 *  user defined number of times. 
	 *  O(n^2) because strings are immutable and new ones must be created.
	 *  @param c : a character to be repeated
	 *  @param n : the number of times to repeat said character
	 *  @return answer : a string of the character repeated many times. */
	public static String repeater(char c, int n) {
		String answer = "";
		for (int j = 0; j < n; j++) {
			answer += c;
		}
		return answer;
	}
	
	/** This overloaded version of the repeater method takes a String
	 *  and repeats it for a user defined number of times. 
	 *  O(n^2) because strings are immutable and new ones must be created.
	 *  @param s : a string to be repeated
	 *  @param n : the number of times to repeat said string
	 *  @return answer : a news string of the given string repeated many times.*/
	public static String repeater(String s, int n) {
		String answer = "";
		for (int j = 0; j < n; j++) {
			answer += s;
		}
		return answer;
	}
	
	/** Test method */
	public static void main(String args[]) {
		System.out.println(repeater( (char) 0x61, 5));
		System.out.println(repeater("A test string ", 5));
	}
}
