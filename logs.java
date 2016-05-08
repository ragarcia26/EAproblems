import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;


public class logs {

	public static void main(String[] args) {
		System.out.print("To search a logs file please enter the path of the file, "
				+ "followed by the following search options: \n-o for OS\n-b for browser\n"
				+ "-i for IP\n-d for date and time\n-f for file requested\n-r for requester\n"
				+ "each of which followed by what you're searching for!\n"
				+ "All searches will be considered inclusive unless the -e option is used for an exclusive search.\n"
				+ "Note: The program is case sensitive; Windows and windows are different!");
		BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
		try{
			while(true){
				String input = brInput.readLine();
				searchOne(input);
			}
		}
		catch(Exception ex){
			System.out.println("Sorry, an error has occured");
			System.out.println(ex);
		}

	}
	
	public static void searchOne(String input){
		try{
			String[] fileSearch = input.split(" +");
			BufferedReader brFile = new BufferedReader(new FileReader(fileSearch[0]));
			String log;
			String os=findFlag(input,"-o");
			String browser=findFlag(input,"-b");
			String ip=findFlag(input,"-i");
			String dateTime=findFlag(input,"-d");
			String file=findFlag(input,"-f");
			//String requester=findFlag(input,"-r");	

			int outcomes = 0;
			while ((log = brFile.readLine()) != null) {
				String actualIP = log.substring(0, log.indexOf(" "));
				String actualDate=log.substring(log.indexOf("[")+1, log.indexOf("]"));
				String actualFile=log.substring(log.indexOf("\"")+1, NthIndexOf(log,"\"",2));
				String actualBrowser=log.substring(NthIndexOf(log,"\"",5)+1, NthIndexOf(log,"\"",6));
				//System.out.println("IP: "+actualIP);
				//System.out.println("Date: "+actualDate);
				//System.out.println("Browser: "+actualBrowser);
				//System.out.println("file: "+actualFile);
				if(input.contains("-e")){
					if(exclusiveCheck(os,actualBrowser) && exclusiveCheck(browser,actualBrowser) &&
							exclusiveCheck(ip,actualIP) && exclusiveCheck(dateTime,actualDate) &&
							exclusiveCheck(file,actualFile)){
						System.out.println(log);
						outcomes++;
					}
					
				}
				else{
					if((actualBrowser.contains(os) && !os.equals("")) || (actualBrowser.contains(browser) && !browser.equals("")) ||
							(actualIP.contains(ip) && !ip.equals("")) || (actualDate.contains(dateTime)&&!dateTime.equals("")) ||
							(actualFile.contains(file) && !file.equals(""))){
						System.out.println(log);
						outcomes++;
					}
				}
			}
			System.out.println(outcomes+" total logs found!");
			brFile.close();
		}
		catch(Exception ex){
			System.out.println("Sorry, an error has occured");
			System.out.println(ex);
		}
	}
	
	public static String findFlag(String input, String flag){
		String found="";
		if(input.contains(flag)){
			found=input.substring(input.indexOf(flag)).split(" +")[1];
		}		
		return found;
	}
	
	//will find the nth occurence of a char recursively
	public static int NthIndexOf(String input, String delim, int n){
		//System.out.println("input: "+input+"\ndelim: "+delim+"\nn: "+n);
		if(n == 1){
			//System.out.println("Final: "+input.indexOf(delim));
			return input.indexOf(delim);
		}
		else{
			n--;
			//System.out.println("Recurse: "+input.indexOf(delim));
			//we lose 1 every recursive call due to substring
			return 1+input.indexOf(delim)+NthIndexOf(input.substring(input.indexOf(delim)+1),delim,n);
		}
	}
	
	public static boolean exclusiveCheck(String input, String actual){
		boolean isExclusive=true;
		if(input.equals("")){
			isExclusive=true;
		}
		else if(!actual.contains(input)){
			isExclusive=false;
		}
		return isExclusive;
	}

}
