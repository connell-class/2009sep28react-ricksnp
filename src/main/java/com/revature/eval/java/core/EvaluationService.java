package com.revature.eval.java.core;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;



/**
 * 
 * @author Nathan Ricks
 *
 */

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		
		String myPhrase = phrase;
		String myAcronym = "";
		
		myAcronym += myPhrase.toUpperCase().charAt(0); //grab first letter
		
		for (int i = 1; i <= myPhrase.length() - 1; i++) { //while there is a next letter
			if (myPhrase.charAt(i-1) == ' ' || myPhrase.charAt(i-1) == '-') {
				myAcronym += myPhrase.toUpperCase().charAt(i); //grabbing the letter after a space or dash and capitalizing it
			}
		}
		
		return myAcronym;
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			
			if((getSideOne() == getSideTwo()) && (getSideOne() == getSideThree()) ) { //logic for equilateral
				return true;
			}
			return false;
		}

		public boolean isIsosceles() {
			
			if ((getSideOne() == getSideTwo()) || (getSideOne() == getSideThree()) || (getSideTwo() == getSideThree())) { //logic for isosceles
				return true;
			}
			return false;
		}

		public boolean isScalene() {
			
			if ((getSideOne() != getSideTwo()) || (getSideOne() != getSideThree()) || (getSideTwo() != getSideThree())) { //logic for scalene
				return true;
			}
			return false;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		int result = 0;
		for (int i = 0; i < string.length(); i++) { //while there is another number
			result += scrabbleValue(string.charAt(i)); //add each scrabble letter value to result
		}
		
		return result;
	}
	
	public int scrabbleValue(char letter) {
		switch (Character.toLowerCase(letter)) { //turn to lowercase to avoid dealing with capital letters
        case 'a':
        case 'e':
        case 'i':
        case 'o':
        case 'u':
        case 'l':
        case 'n':
        case 'r':
        case 's':
        case 't':
            return 1; //if the letter is any of these ^ , the value gets sent back as 1.  Same for the rest of these cases
        case 'd':
        case 'g':
            return 2;
        case 'b':
        case 'c':
        case 'm':
        case 'p':
            return 3;
        case 'f':
        case 'h':
        case 'v':
        case 'w':
        case 'y':
            return 4;
        case 'k':
            return 5;
        case 'j':
        case 'x':
            return 8;
        case 'q':
        case 'z':
            return 10;
        default:
            return -1;
    }
}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		
		String number = string.replaceAll("\\D", ""); //remove any non-digit characters
		
		if (number.length() > 11) {
			throw new IllegalArgumentException("Number too big!"); //after removing characters, if the length is greater than 11, quit
		}
		
		if (number.length() == 11 && number.startsWith("1")) {
			number.replaceFirst("1",""); //remove the country code (only works for NANP - countries)
		}
		
		if (number.length() < 10) {
			throw new IllegalArgumentException("Number too small, or had non-digit characters in it."); //invalid number
		}
		
		return number;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		// TODO Write an implementation for this method declaration
		
		String words = string.replaceAll("\n", ""); //remove newline characters for convenience
		String[] arrOfWords = new String[] {}; //hold our words
		
		if (words.contains(",")) {
			arrOfWords = words.split(","); //let's put all words in our array, splitting on a comma or space
		}
		
		else {
			arrOfWords = words.split(" ");
		}
		
		Map<String, Integer> wordCount = new HashMap<String, Integer>(); //we can store the word with its number of occurrences in a map
		
		for (String word: arrOfWords) { //for every word in our array
			if (!wordCount.containsKey(word)) {//if the word is not in the map (with its num of occurrences)
				wordCount.put(word, 1); //mark it down as occurring once
			}
			
			else {
				int numberOfOccurrences = wordCount.get(word); //update existing words with their new number of occurrences
				wordCount.put(word, numberOfOccurrences + 1);
			}
		}
		return wordCount; //the words with their respective number of occurrences
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			int first = 0; //the very left of the list
			int last = sortedList.size() - 1; //the very right of the list
			int mid = 0;
			int index = -1; //just in case the number isn't found
			
			while (first <= last) { //keep going until we find our element
				
				mid = (first + last) / 2; //calculate middle of list
				
				//this works great because we are dealing with objects, even when we are passed a list of Integers, we can use .equals for comparison
				// for example "6".equals("6") == true // also works like this for Integer lists; -> Integer a = new Integer(6); Integer b = new Integer(6); a.equals(b) == true
				if (t.equals(sortedList.get(mid))) {
					index = sortedList.indexOf(t); //if the middle is our number, return its index
					return index;
				}
				
				else if (sortedList.indexOf(t) < mid) { //cut the right side (in theory)
					last = mid - 1;
				}
				
				else  { //cut the left side (in theory)
					first = mid + 1;
				}
			}
			return index; //the index of our desired element
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		
		String result = "";
		String[] arrOfWords = new String[] {}; //store the word or words in an array
		
		arrOfWords = string.split(" "); //split on a space
		
		if (arrOfWords.length > 1) { //we have more than 1 word to deal with
			
			for (String w : arrOfWords) {//for every word in the array
				if (w.startsWith("a") || w.startsWith("e") || w.startsWith("i") || w.startsWith("o") || w.startsWith("u")) {
					result += (w + "ay "); //notice the space after
				}
				
				else if (w.startsWith("qu")) {
					result += w.substring(2) + w.substring(0,2) + "ay "; //special case if the word begins with qu
				}
				
				else {
					int i;
					for (i = 0; i < w.length(); i++) {
						char ch = w.charAt(i);
						if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'u') {
							break;
						}
					}
					
					 result += w.substring(i) + w.substring(0,i) + "ay ";//deal with consonant at beginning, until we find vowel
				}
			
			}
				
			
		}
		
		else { //the array only has 1 word (this was probably inefficient)
			
			for (String w : arrOfWords) {
				if (w.startsWith("a") || w.startsWith("e") || w.startsWith("i") || w.startsWith("o") || w.startsWith("u")) {
					result += (w + "ay");
				}
				
				else if (w.startsWith("qu")) {
					result += w.substring(2) + w.substring(0,1) + "ay"; 
				}
				
				else {
					int i;
					for (i = 0; i < w.length(); i++) {
						char ch = w.charAt(i);
						if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
							break;
						}
					}
					
					 result += w.substring(i) + w.substring(0,i) + "ay";
				}
			
			}
			
		}
		
		result = result.trim(); //remove the space on the end
		return result ;

	}
	
	

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		
		int temp;
		int remainder;
		int times = 0;
		int sum = 0;
		
		temp = input;
		
		while (temp != 0) { //keep diving by 10 until it equals 0, this gets the exponent for each digit
			times += 1;
			temp /= 10;
		}
		
		temp = input;
		while (temp > 0) {
			remainder = temp % 10;
			sum += Math.pow(remainder, times); //for ex, if input = 153, remainder is set to 3, and sum = sum + 3^3
			temp /= 10; //now 153 would be 15, then 1, then 0
		}
		
		if(sum == input) {// sum of all the digits to that power = our original number
			return true;
		}
        
        return false;
    }

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		
		List <Long> primes = new ArrayList<>(); //make a list of the primes
		long number = l;
		
		for (long i = 2; i <= number; i++) {
			if (number % i == 0) { //starting at 2, if our number is divisible by i, add that to the primes
				primes.add(i);
				number = number / i;
				i--;
				
			}
		}
		
		return primes;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			
			String result = "";
			 
	        for (int i = 0; i < string.length(); i++) {
	            if (Character.isUpperCase(string.charAt(i))) {
	                char ch = (char) (((int) string.charAt(i) + //uppercase letters
	                        key - 65) % 26 + 65);
	                		result += ch;
	            }
	            
	            else if ((string.charAt(i) <= 'A') ){ //comparing ascii values
	            	
	            	char ch = string.charAt(i); //ignore spaces, numbers, punctuation (per test cases)
	            	result += ch;
	            }
	            
	            else {
	                char ch = (char) (((int) string.charAt(i) +
	                        key - 97) % 26 + 97); //standard conversion
	                result += ch;
	            }
	        }
	        return result;
			
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int it) {
		int nth = it; 

	    int num = 1;
	    int count = 0;
	    int i;

	    
	    if (it == 0) {
	    	throw new IllegalArgumentException("Can't enter 0!"); 
	    }
	    
	    while (count < nth){
	    	
	      num += 1;
	      for (i = 2; i <= num; i++){
	        if (num % i == 0) {
	          break;
	        }

	      }

	      if ( i == num){  //if prime
	        count = count+1;
	      }

	}
	    return num; //nth prime
}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {
		

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			String plain = string.replaceAll("\\W", ""); //replace non-word characters
			
			String result = "";
			
			for(char c : plain.toLowerCase().toCharArray()) { //converted string to char array in one step and made the characters lowercase
				if (Character.isLetter(c)) {
					result += (char) ('a' + ('z' - c)); //not using ascii number representation
				}
				else {
				result += c;
				}
			}
			
			return splitIntoBlocks(result).trim(); //no white space on end, and split the encoded text into blocks of 5
		
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			
			String plain = string.replaceAll("\\W", "");
			
			String result = "";
			
			for(char c : plain.toLowerCase().toCharArray()) { //converted string to char array in one step and made the characters lowercase
				if (Character.isLetter(c)) {
					result += (char) ('z' + ('a' - c)); //not using ascii number representation
				}
				else {
				result += c;
				}
			}
			
			return result;
		}
		
		private static String splitIntoBlocks(String phrase) { //take in the string, split it into blocks of 5, until it can't
			String out = "";
	        for(int i = 0; i < phrase.length(); i += 5)
	        {
	            if(i + 5 <= phrase.length())
	            {
	                out += (phrase.substring(i, i + 5) + " ");
	            }
	            else
	            {
	                out += (phrase.substring(i) + " ");
	            }
	        }
	        return out;
			
	}
		
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		
		
		if (string == null) {
			return false;
		}
		
		String isbn = string.replaceAll("-", ""); //remove hyphens
		
		if (isbn.length() != 10) {
			return false;
		}
		
		int total = 0;
		for (int i = 0; i < 9; i++) {
			int num = isbn.charAt(i) - '0';
			if (0 > num || 9 < num) { //compute sum of first 9 digits
				return false;
			}
			else {
				total += (num * (10 - i));
			}
		}
		
		char end = isbn.charAt(9);
		if(end != 'X' && (end < '0' || end > '9')) { //make sure last digit is either X or 0-9
			return false;
		}
		
		if (end == 'X') {
			total += 10; //add 10 to total if X is last value
		}
		else {
			total += (end - '0'); //otherwise, just add its value
		}
		
		return (total % 11 == 0); //true or false
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		
		String alph = "abcdefghijklmnopqrstuvwxyz"; //we want to use every letter at least once, here are the letters
		char[] alphabet = alph.toCharArray();
		String tester = string.toLowerCase(); //only want to deal with lowercase letters
		boolean ispan = true;
		
		for (char c: alphabet) {
			if (tester.indexOf(c) > -1) {  //for each character in the alphabet, if it is in the string, keep going
				continue;
			}
			
			else {
				ispan = false;  //otherwise, the letter is not in the string, so it is not used at least once
			}
		}
		return ispan;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		
		int day = 0;
		int month = 0;
		int year = 0; //instance variables for a full date
		int hour = 0;
		int minute = 0;
		int second = 0;
		
		
		//using temporals, setting each provided field in given, to it's respective value
		if (given.isSupported(ChronoField.YEAR)) {
			year = given.get(ChronoField.YEAR);
		}
	           
	    if (given.isSupported(ChronoField.MONTH_OF_YEAR)) {
	    	month = given.get(ChronoField.MONTH_OF_YEAR);
	    }
	    if (given.isSupported(ChronoField.DAY_OF_MONTH)) {
	    	day = given.get(ChronoField.DAY_OF_MONTH);
	    }
	    if (given.isSupported(ChronoField.HOUR_OF_DAY)) {
	        hour = given.get(ChronoField.HOUR_OF_DAY);
	    }
	    if (given.isSupported(ChronoField.MINUTE_OF_HOUR)) {
	    	minute = given.get(ChronoField.MINUTE_OF_HOUR);
	    }
	    if (given.isSupported(ChronoField.SECOND_OF_MINUTE)) {
	    	second  = given.get(ChronoField.SECOND_OF_MINUTE);
	    }

	    given = LocalDateTime.of(year, month, day, hour, minute, second).plusSeconds(1000000000); //take that date and add a gigasecond
		return given;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		
		int sum = 0;

        for (int j = 0; j < i; j++) { //make sure we stay below the given number
            Boolean isMultiple = false;

            for (int numInSet : set) { //for every number in the set
                if (j % numInSet == 0) { //if you divide the current index by the number in the set and the remainder is 0
                	isMultiple = true; //the number is a multiple of that number (3 is a multiple of 3, so is 6)
                }
            }

            if (isMultiple) {
                sum += j; //if that number is a multiple, add it to the sum
            }
        }

        return sum; //sum of multiples
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		
		int sum = 0;
        boolean isSecond = false;
        String luhn = string.replaceAll(" ", ""); //remove all spaces to make it easier
        
        
        for (int i = luhn.length() - 1; i >= 0; i--) //start at the end and work towards the left
        {	
        	int n;
            try{
               n = Integer.parseInt(luhn.substring(i, i + 1)); //each number in the string
            }
            catch(NumberFormatException e) { //needs to be a number 123456
               return false;
             }
             if (isSecond) 
             {
                 n *= 2; //every second digit, multiply by 2
                 if (n > 9)
                 {
                    n -= 9; //if doubling results in num greater than 9, subtract it
                 }
             }
             sum += n; //add that number to the sum
             isSecond = !isSecond; //we are constantly changing this as we iterate over the numbers
        }
        return (sum % 10 == 0);
}


	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		
		Pattern pattern = Pattern.compile("([+-]?\\d+)\\s+(plus|minus|multiplied\\s+by|divided\\s+by)\\s+([+-]?\\d+)", Pattern.CASE_INSENSITIVE); //beautiful regex for this specific type problem
	    Matcher matcher = pattern.matcher(string);
	   
	      if(matcher.find()) {                               
	         return doMath(matcher.group(1), matcher.group(2), matcher.group(3)); //group1 = first num, group 2 is operation, group 3 is 2nd num
	      }
	      else{
	         return 0; //no match found
	      }
	   }
	
	private static int doMath(String N1, String oper, String N2)
	   {
	      int n1 = Integer.parseInt(N1); //parse the numbers as ints (we sent them as strings)
	      int n2 = Integer.parseInt(N2);
	      
	      switch(oper) {
	         case "plus": 
	            return(n1 + n2); //perform specific logic depending on the case
	         case "minus": 
	            return(n1 - n2);
	         case "multiplied by": 
	            return(n1 * n2);
	         case "divided by": 
	            return(n1 / n2);
	            default:
	            	return 0;
	      }
	         
	    
	   }


		
		
	

}
