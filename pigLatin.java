import java.io.BufferedReader;
import java.io.InputStreamReader;

public class pigLatin {

	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
		while(true){
			String phrase = br.readLine();
			pigConvert(phrase);
		}
		}catch(Exception ex){
			System.out.println("I had trouble reading your input! Try again.");
			System.out.println(ex);
		}
		
	}
	
	public static void pigConvert(String phraseToConvert){
		String secretPhrase="";
		String[] tokens = phraseToConvert.split(" +");
		for(int i=0; i<tokens.length; i++){
			String[] token = tokens[i].split("-");
			for(int j=0; j<token.length;j++){
				
				String word=token[j];
				String secretWord=word;
				String vowels = "aeiouAEIOU";
				
				if(word.length() == 1){
					//do nothing
				}
				else if(vowels.contains(String.valueOf(word.charAt(0)))){
					secretWord=word+"way";
				}
				else{
					secretWord=word.substring(1)+word.charAt(0)+"ay";
				}
				
				secretWord = movePunc(secretWord,'!');
				secretWord = movePunc(secretWord,'?');
				secretWord = movePunc(secretWord,'.');
				secretWord = movePunc(secretWord,',');
				secretWord = movePunc(secretWord,'\'');
				
				secretWord = changeCase(word,secretWord);
				
				if(j!=0){secretPhrase+="-";}
				secretPhrase += secretWord;
				
			}
			secretPhrase+=" ";
		}
		System.out.println(secretPhrase);
	}

	//Checks if the punctuation exists in the string, and if so
	//pushes it over 3 indexes
	public static String movePunc(String word, char punctuation){
		String moved = word;
		int punc = word.indexOf(punctuation);
		//System.out.println("word: "+moved+" punctuation: "+punctuation+" punc: "+punc);
		if(punc != -1){
			moved = word.substring(0,punc)+word.substring(punc+1, punc+4)+punctuation+word.substring(punc+4);
		}
		return moved;
	}
	
	public static String changeCase(String originalWord, String secretWord){
		String secret = "";
		for(int i=0; i<originalWord.length(); i++){
			if(Character.isUpperCase(originalWord.charAt(i))){
				secret+=Character.toUpperCase(secretWord.charAt(i));
			}
			else{
				secret+=Character.toLowerCase(secretWord.charAt(i));
			}
		}
		secret+=secretWord.substring(originalWord.length());
		return secret;
	}
}
