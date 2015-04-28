  
public class Mario{

   public static void main(String[] args) {

   		int numberOfStrings = Integer.parseInt(StdIn.readLine());
   		int lengthOfStrings = Integer.parseInt(StdIn.readLine());
		
		String[] a = new String[numberOfStrings];
   		
   		int index = 0;
   		while(StdIn.hasNextLine()){
   			a[index]= StdIn.readLine();
   			index++;
   		}
        
        for (int i = 0; i <numberOfStrings ; i++){
         StdOut.println(a[i]);
    	}
  }
}