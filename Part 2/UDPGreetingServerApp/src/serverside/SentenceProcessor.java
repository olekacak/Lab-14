package serverside;

public class SentenceProcessor {
	
	private int size = 65535;
	
	private String sentence;
	
	private static final String VOWELS = "aeiouAEIOU";
	
	private static final String PUNCTUATION = ",.!?;:";
	
	
	public SentenceProcessor(byte[] byteData) {
		
		this.sentence = new String(byteData);
	}
	
	public String getSentence() {
		
		return sentence;
	}
	
	/**
	 * This method convert value into an array of byte
	 * @param length
	 * @return
	 */
	public byte[] convertToByteArray(int value) {
		
		byte[] outData = new byte[size];
		
		String stringValue = Integer.valueOf(value).toString();
		
        outData = stringValue.getBytes();
        
        return outData;
	}
	
	/**
	 * This method count the number of characters in a sentence
	 * @return
	 */
	public int countCharacters() {
		
        int index = 0;
        char currentChar = sentence.charAt(index);
        while (currentChar != '\0') {
        	
        	currentChar = sentence.charAt(index++);
        }
        

        return index - 1;
	}
	
	/**
	   * This method counts the number of vowels in the sentence.
	   * @return the number of vowels in the sentence
	   */
	  public int countVowel() {
	    int vowel = 0;
	    for (char c : sentence.toCharArray()) {
	      if (VOWELS.contains(String.valueOf(c))) {
	    	  vowel++;
	      }
	    }
	    return vowel;
	  }
	  
	  /**
	   * This method counts the number of consonants in the sentence.
	   * @return the number of consonants in the sentence
	   */
	  public int countConsonant() {
	    int consonant = 0;
	    for (char c : sentence.toCharArray()) {
	      if (Character.isLetter(c) && !VOWELS.contains(String.valueOf(c))) {
	    	  consonant++;
	      }
	    }
	    return consonant;
	  }
	  
	  /**
	   * This method counts the number of punctuation marks in the sentence.
	   * @return the number of punctuation marks in the sentence
	   */
	  public int countPunctuation() {
	    int punctuation = 0;
	    for (char c : sentence.toCharArray()) {
	      if (PUNCTUATION.contains(String.valueOf(c))) {
	    	  punctuation++;
	      }
	    }
	    return punctuation;
	  }
	

}
