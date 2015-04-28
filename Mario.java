  
public class Mario{

      static int numberOfStrings = Integer.parseInt(StdIn.readLine());
      static int lengthOfStrings = Integer.parseInt(StdIn.readLine());
      static ResizingArrayStack posOfS= new ResizingArrayStack();
      static ResizingArrayStack posOfF= new ResizingArrayStack();
      static int numberOfSpaces=0;
      static int shortestDistance=-1;
      static String [] map;
      static ST<String, Integer> oldNotes= new ST<String, Integer>();
      static  int shortInstance=-1;


    public static boolean beenHere(String note){
          if (oldNotes.contains(note)){
            return true;
          }
            
            return false;
          }


  public static int putNote(String note, int dist){
        String [] coordiantes = note.split(",");
        int stringPos=Integer.parseInt(coordiantes[0]);
        int charPos=Integer.parseInt(coordiantes[1]);
        int dString=Integer.parseInt(coordiantes[2]);
        int dChar=Integer.parseInt(coordiantes[3]);

        int returndist=0;


        char current =map[stringPos].charAt(charPos);

        if(dist>shortInstance && shortInstance!=-1){
          return -1;
        }

        if(current=='O'){
           return -1;
        }
        if(current=='F'){
          System.out.println("Found F at dist:  "+dist);
            if (shortInstance==-1){
              shortInstance=dist;
            }else{
              if(dist<shortInstance){
                shortInstance=dist;
              }
            }
           return dist;
        }

        if(current==' '|| current=='S' ){
          if(beenHere(note)){
           // System.out.println("beenHere: "+note);
            return -1;
          }else{
            dist++;

 //             System.out.println(note);
//            System.out.println(dist);
            oldNotes.put(note,1);
            for (int i=-1; i<2; i++){
              for(int j=-1; j<2; j++){
             // System.out.println(i+" : "+j);            

                int newDString=dString+i;
                int newDChar=dChar+j;

                int newStringPos=stringPos+newDString;
                int newCharPos=charPos+newDChar;


                if(newStringPos>=0 && newStringPos<numberOfStrings && newCharPos>=0 && newCharPos<lengthOfStrings){

      //            System.out.println("hurray");

                  String newNote=""+newStringPos+","+newCharPos+","+newDString+","+newDChar;
                  int newDist = putNote(newNote, dist);
                  System.out.println(note);


                  if(newDist==-1){
                    continue;

                  }
                  returndist=newDist;

                }

              }
            }
          }
          System.out.println(returndist);
           return returndist;
        }

        return -1;
           
      }




   public static void main(String[] args) {


		map = new String[numberOfStrings];
   		
   		int index = 0;
   		while(StdIn.hasNextLine()){
   			map[index]= StdIn.readLine();
        for(int i=0; i<lengthOfStrings;i++){
          if (map[index].charAt(i)=='S'){
            String hereIsS=""+index+","+i;
            posOfS.push(hereIsS);
          }
          if (map[index].charAt(i)=='F'){
            String hereIsF=""+index+","+i;
            posOfF.push(hereIsF);
          }
          if (map[index].charAt(i)==' '){
            numberOfSpaces++;
          }


        }
   			index++;
   		}
        
        for (int i = 0; i <numberOfStrings ; i++){
         StdOut.println(map[i]);
    	}

      //System.out.println(posOfS.pop());
      //System.out.println(posOfF.pop());

      while(!posOfS.isEmpty()){

        String start=posOfS.pop()+","+"0"+","+"0";
        int test= putNote(start,0);
        System.out.println("Shostes dist to F: "+shortInstance);

      }
      System.out.println("Shostes dist to F: "+shortInstance);

     // String tal="-1";

     // int nytTal=Integer.parseInt(tal);

     // System.out.println(nytTal);

              for (int i = 0; i <numberOfStrings ; i++){
         StdOut.println(map[i]);
      }
  }
}
