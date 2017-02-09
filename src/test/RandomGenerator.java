package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates pseudo random values
 * 
 * @author Mikael Holmbom
 * @since 2016-02-21
 * @version 1.0
 * 
 */
public class RandomGenerator {
	
	private static Random _rand = new Random();
	
	public RandomGenerator(){

		
	}
	/**
	 *generate list of unique random numbers with natural min and max limits
	 * @see #number(int, int)
	 * @param amount amount of numbers to generate
	 * @return list of unique numbers
	 */
	public static List<Integer> uniqueNumbers(int amount){
		return uniqueNumbers(amount, (Integer.MIN_VALUE), Integer.MAX_VALUE);
	}
	/**
	 *generate list of unique random numbers
	 * @see #number(int, int)
	 * @param amount amount of numbers to generate
	 * @param minVal min value
	 * @param maxVal max value
	 * @return list of unique numbers
	 */
	public static List<Integer> uniqueNumbers(int amount, int minVal, int maxVal){
		if (new Long(maxVal) - (minVal) < amount) throw new IllegalArgumentException(
				"not possible to produce more unique numbers"
				+ " than unique numbers in min-max interval."
				+ " intervalsize: "+(new Long(maxVal) - minVal) + ", amount: "+amount);
		
		List<Integer> uniqueVals = new ArrayList<Integer>(amount);
		while (uniqueVals.size() < amount){
			int val = number(minVal, maxVal);
			// minimize generating interval
			if (val == maxVal) maxVal--;
			if (val == minVal) minVal++;
			
			if (!uniqueVals.contains(val)){
				uniqueVals.add(val);
			}
		}
		return uniqueVals;
	}
	/**
	 * generate random number with natural min and max values
	 * @see #number(int, int)
	 * @return random number
	 */
	public static int number(){
		return number(Integer.MIN_VALUE, Integer.MAX_VALUE );
	
	}
	/**
	 * generate random positive number between 1 and Integer.MAX_VALUE
	 * @return random positive number
	 */
	public static int positiveNumber(){
		return number(1, Integer.MAX_VALUE);
	}
	/**
	 * generate random negative number between Integer.MIN_VALUE and -1
	 * @return
	 */
	public static int negativeNumber(){
		return number(Integer.MIN_VALUE, -1);
	}
	/**
	 * generate random non-positive number between Integer.MIN_VALUE and 0
	 * @return random non-positive number
	 */
	public static int nonPositiveNumber(){
		return number(Integer.MIN_VALUE, 0);
	}
	/**
	 * generate random non-negative number between 0 and Integer.MAX_VALUE
	 * @return random non-negative number
	 */
	public static int nonNegativeNumber(){
		return number(0, Integer.MAX_VALUE);
	}
	/**
	 * generate random number
	 * @param minVal min value
	 * @param maxVal max value
	 * @throws IllegalArgumentException if {@code minVal} &gt; {@code maxVal}
	 * @return random number
	 */
	public static int number(int minVal, int maxVal){
		if(maxVal < minVal)throw new IllegalArgumentException("min value must be less than or equal to max value");
		
		int max = 0;
		Long l = new Long(maxVal)-minVal;
		if(l > Integer.MAX_VALUE)
			max = Integer.MAX_VALUE;
		else
			max = maxVal - minVal;
		
		return _rand.nextInt(Math.abs(max)) + minVal; 
	}
	/**
	 * generate random boolean value
	 * @return random boolean value
	 */
	public static boolean booleanValue(){
		return _rand.nextBoolean();
	}
	/**
	 * get all alphanumerics: a-zA-Z0-9
	 * @return all alphanumeric
	 */
	private static String getAlphaNumeric(){
		StringBuilder sb = new StringBuilder();
		for(char ch = 'A'; ch <= 'Z'; ch++)
			sb.append(ch);
		for(char ch = 'a'; ch <= 'z'; ch++)
			sb.append(ch);
		for(char ch = '0'; ch <= '9'; ch++)
			sb.append(ch);
		
		return sb.toString();
	}
	/**
	 * generate random alphanumeric character a-zA-Z0-9
	 * @see RandomGenerator#getAlphaNumeric()
	 * @return random character
	 */
	public static String character(){
		String alphaNumeric = getAlphaNumeric();
		int idx = _rand.nextInt(alphaNumeric.length());
		
		return alphaNumeric.substring(idx, idx+1);
	}
	/**
	 * generate random string of length <code>length</code>
	 * @param length length of random string
	 * @return random string of length <code>length</code>
	 */
	public static String string(int length){
		if (length < 1) throw new IllegalArgumentException("invalid: length must be positive number");
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < length; i++){
			sb.append(character());
		}
		return sb.toString();
	}
	/**
	 * generate random string of random length between 0 and 1 000
	 * @return random string
	 */
	public static String string(){
		return string(_rand.nextInt(1_000)+1);
		
	}

}
