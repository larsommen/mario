  
public class MarioTwo{

public static String[] map;
public static ResizingArrayQueue<String> notes = new ResizingArrayQueue<String>();
public static ST<String, Integer> oldNotes = new ST<String, Integer>();
public static int numberOfStrings = Integer.parseInt(StdIn.readLine());
public static int lengthOfStrings = Integer.parseInt(StdIn.readLine());

public static int finalDistance = -1;



  static public boolean beenHere(String note){
        String [] coordiantes = note.split(",");
        int stringPos=Integer.parseInt(coordiantes[0]);
        int charPos=Integer.parseInt(coordiantes[1]);
        int dString=Integer.parseInt(coordiantes[2]);
        int dChar=Integer.parseInt(coordiantes[3]);
        int distance = Integer.parseInt(coordiantes[4]);

        note = stringPos + "," + charPos + "," + dString + "," + dChar;

    if(oldNotes.contains(note)){
      return true;
    }
    else{
      return false;
    }
  }


  public static boolean findPath(String note){
        String [] coordiantes = note.split(",");
        int stringPos=Integer.parseInt(coordiantes[0]);
        int charPos=Integer.parseInt(coordiantes[1]);
        int dString=Integer.parseInt(coordiantes[2]);
        int dChar=Integer.parseInt(coordiantes[3]);
        int distance = Integer.parseInt(coordiantes[4]);

        String oldNote = stringPos + "," + charPos + "," + dString + "," + dChar;

    
        char current =map[stringPos].charAt(charPos);

        if(current=='O'){
           return false;
        }

        if(current=='F'){
          //System.out.println("found F at distance: " + distance);

          if(finalDistance == -1){
            finalDistance = distance;
          }
          else{
            if(distance<finalDistance){
              finalDistance = distance;
            }
          }
           return true;
        }

        if(current==' '|| current=='S' ){
          if(beenHere(note)){
            return false;
          }
          else{
            oldNotes.put(oldNote, 1);
          // place all 9 possible new nodes in the que
            for (int i=-1; i<2; i++){
              for(int j=-1; j<2; j++){

                int newDString=dString+i;
                int newDChar=dChar+j;
                int newStringPos=stringPos+newDString;
                int newCharPos=charPos+newDChar;

                if(newStringPos>=0 && newStringPos<numberOfStrings && newCharPos>=0 && newCharPos<lengthOfStrings){
                  String tmpDist = Integer.toString(distance+1);

                  String newNote=""+newStringPos+","+newCharPos+","+newDString+","+newDChar+","+tmpDist;
                  notes.enqueue(newNote);
                }
              }
            }
          }
        }
        return false;
  }

   public static void main(String[] args) {

    int numberOfSpaces = 0;
		
		map = new String[numberOfStrings];

    ResizingArrayStack posOfS= new ResizingArrayStack();
    ResizingArrayStack posOfF= new ResizingArrayStack();
   	
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

      boolean foundPath = false;


      while(!posOfS.isEmpty()){
        oldNotes = new ST<String, Integer>(); // reset old notes

        String start=posOfS.pop()+","+"0"+","+"0"+","+1;
        notes.enqueue(start);

        while(!notes.isEmpty()){
          String tmp = notes.dequeue();

          int initialSize = notes.size();

          foundPath = findPath(tmp);

        }
      }
      System.out.println(finalDistance);
  }
}

