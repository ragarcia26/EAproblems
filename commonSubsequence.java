import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;


public class commonSubsequence {

	public static void main(String[] args) {
		BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
		try{
			String file = brInput.readLine();
			BufferedReader brFile = new BufferedReader(new FileReader(file));
			String sequence;
			while ((sequence = brFile.readLine()) != null) {
				if(!sequence.equals("")){
					if(!sequence.contains(";")){
						System.out.println("The Sequence "+sequence+" does not contain the ';' delimiter.");
					}
					else{
						System.out.println("Sequence: "+sequence);
						String[] sequences = sequence.split(";");
						String lcs = largestCommonSubsequence(sequences[0],sequences[1]);
				        System.out.println(lcs+"\n");
					}
				}
			}
			brInput.close();
			brFile.close();
		}catch(Exception ex){
			System.out.println("Sorry an error occured");
			System.out.println(ex);
		}

	}
	public static String largestCommonSubsequence(String first, String second){
		String largest = "";
		//build an LCS matrix
		int[][] lcs = new int [first.length()+1][second.length()+1];
		//start at +1 because first row and column are zero 
		for(int i=1; i<=first.length();i++){
			for(int j=1; j<=second.length(); j++){
				if(first.charAt(i-1)==second.charAt(j-1)){
					lcs[i][j]=lcs[i-1][j-1]+1;
				}
				else{
					lcs[i][j]=Math.max(lcs[i-1][j], lcs[i][j-1]);
				}
			}
		}
		
		//Traverse the matrix in reverse to find the lcs, diagonal moves are part of the lcs
		int i = first.length();
		int j = second.length();
		//System.out.println("arrSize: "+lcs[0].length+" firstLen: "+first.length()+" i: "+i);
		while(i>0 && j>0){
			if(first.charAt(i-1)==second.charAt(j-1)){
				largest+=first.charAt(i-1);
				i--;
				j--;
			}
			else if(lcs[i-1][j] >= lcs[i][j-1]){
				i--;
			}
			else{
				j--;
			}
		}
		String ans="";
		for ( int a = largest.length()-1; a>=0; a--){
	          ans = ans + largest.charAt(a);
		}
		return ans;
	}
	
	
}
