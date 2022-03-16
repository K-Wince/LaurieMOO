package mp1;

import java.util.Random;
/**
 * @author wince
 * @Description Creates a 4-digit secret value (0000 through 9999) for a player to guess. Feedback is returned in the form of big and little moos. Each
 *"MOO!" indicates a digit correctly guessed in both value and position. Each "moo." indicates a digit correctly guessed in terms of
 *value, but not position. If no digits are correctly guessed, then all the user hears are cowbells... Please note that the number generated
 *by the program can be any four-digit number: it can have leading zeros, it can have multiple instances of the same digit, and so on.
 *For example, the following values are all possible: 0000, 0123, 3455, 7870. When generating big (MOO!) and little (moo.) moos, each
 *guessed digit can only match at most one digit in the secret value. For example, if the secret value is 0055 and the user's guess is
 *5550, our favorite cow should be uttering "MOO! moo. moo." as there is an exact match in digit position 3, plus two inexact matches.
 */
public class RandomMooValue {
	private int secretValue = 0;
	
	Random rand = new Random();
	
	RandomMooValue(){}
	/**
	 * The number of digits in the user's guess that exactly (i.e., same position) matches digits in the secret value.
	 * @param guess - The number that the user guessed.
	 * @return a number 0-4 representing how many digits the user guessed correctly by position.
	 */
	public int getBigMooCount(int guess) {
		String str1 = String.format("%04d", this.secretValue);
		String str2 = String.format("%04d", guess);
		
		int count = 0;
		
		for(int i = 0; i < str1.length(); i++) {
			if(str1.charAt(i) == str2.charAt(i)) {
				count++;
			}
		}
		
		return count;
	}
	/**
	 * The number of digits in the user's guess that match digits in the secret value, but are not at the same position.
	 * @param guess - The number that the user guessed.
	 * @return a number 0-4 representing how many of the guessed digits match, but are in different
	 * positions. Note that a guessed number cannot have any one digit generate both a "MOO!"
     * and a "moo." as a result
	 */
	public int getLittleMooCount(int guess) {
		String str1 = String.format("%04d", this.secretValue);
		String str2 = String.format("%04d", guess);
		
		int count = 0;
		
		char[] ch1 = new char[str1.length()];
		char[] ch2 = new char[str2.length()];
		for(int i = 0; i < str1.length(); i++) {
			ch1[i] = str1.charAt(i);
			ch2[i] = str2.charAt(i);
			if(ch1[i] == ch2[i]) {
				ch1[i] = 'X';
				ch2[i] = 'V';
			}
		}
		
		for(int i = 0; i < str1.length(); i++) {
			for(int j = 0; j < str1.length(); j++) {
				if(ch1[i] == ch2[j]) {
					count++;
					ch1[i] = 'X';
					ch2[j] = 'V';
				}
			}
		}
		return count;
	}
	/**
	 * Access the secret value that the user is trying to guess, primarily to show the user after running out of guesses.
	 * @return the secret value that the user is/was attempting to guess.
	 */
	public int getSecretValue() {
		return this.secretValue;
	}
	/**
	 * The number of digits in the user's guess that exactly (i.e., same position) matches digits in the secret value
	 * @param guess - The number that the user guessed
	 * @return a number 0-4 representing how many digits the user guessed correctly by position
	 */
	public boolean isCorrectGuess(int guess) {
		if(this.secretValue == guess) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Generates a new secret value to be guessed in a game of LaurieMOO.
	 * @return true in all cases.
	 */
	public boolean setSecretValue() {
		this.secretValue = rand.nextInt(10000); 
		return true;
	}
	/**
	 * Sets the "secret" value to be guessed in a game of LaurieMOO to a known 4-digit quantity
	 * @param n - The number that will be set as the secret value, if it is within the inclusive
     * range of 0000-9999
	 * @return true if the secret value was reset; false if the passed value was outside of the
     * allowed range of values
	 */
	public boolean setSecretValue(int n) {
		if(n >= 0 && n <= 9999) {
			this.secretValue = n;
			return true;
		} else {
			return false;
		}
	}
}
