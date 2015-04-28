  
public class MarioTwo{

public static String[] map;
public static ResizingArrayQueue<String> notes = new ResizingArrayQueue<String>();
public static ST<String, Integer> oldNotes = new ST<String, Integer>();
public static int numberOfStrings = Integer.parseInt(StdIn.readLine());
public static int lengthOfStrings = Integer.parseInt(StdIn.readLine());
public static int finalDistance = -1;

  /**
   * Method takes a note of the form String = stringPos, charPos, dString, dChar, distance
   * and looks up in the oldNotes ST and returns true if it is there
   * 
   */
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
   /**
   * Method takes a note of the form String = stringPos, charPos, dString, dChar, distance
   * and computes the possible nodes to move to from this node.
   * Before the computation, the method checks if the node corresponds to a
   * "O", "F", "S" or " " in the Strin[] map. 
   * The maksimum is nine notes becouse there exist a maximum of nine combinatios of dX, dY 
   * where dX belongs to {-1,0,1} and dY belongs to {-1,0,1}
   * however if a note is not in the String[] map, it is not put in the ResizingArrayQueue notes
   *
   */
  public static void findPath(String note){
        // Split the string in to desired variables
        String [] coordiantes = note.split(",");
        int stringPos=Integer.parseInt(coordiantes[0]);
        int charPos=Integer.parseInt(coordiantes[1]);
        int dString=Integer.parseInt(coordiantes[2]);
        int dChar=Integer.parseInt(coordiantes[3]);
        int distance = Integer.parseInt(coordiantes[4]);

        //create oldNote for the call of beenHere() method in line 73
        String oldNote = stringPos + "," + charPos + "," + dString + "," + dChar;

        // find the character in the String[] map that corresponds to the note
        char current = map[stringPos].charAt(charPos);

        // check if it is an ilegal position
        if(current=='O'){
           return;
        }

        // check if it is a goal position
        if(current=='F'){
          // update the global variabel finalDistance if this particular disatance is smaller
          if(finalDistance == -1){
            finalDistance = distance;
          }
          else{
          if(distance<finalDistance){
              finalDistance = distance;
            }
          }
           return;
        }

        // check if it is a legal note
        if(current==' '|| current=='S' ){

          // check if the note has been visitet before with the exact same velocity (dX, dY)
          if(beenHere(note)){
            return;
          }
          else{

            // put the note in the oldNotes ST
            oldNotes.put(oldNote, 1);

            // examine all 9 possible new notes in the que
            for (int i=-1; i<2; i++){
              for(int j=-1; j<2; j++){
                int newDString=dString+i;
                int newDChar=dChar+j;
                int newStringPos=stringPos+newDString;
                int newCharPos=charPos+newDChar;

                // if the position is "in the map"
                if(newStringPos>=0 && newStringPos<numberOfStrings && newCharPos>=0 && newCharPos<lengthOfStrings){
                  String newNote=""+newStringPos+","+newCharPos+","+newDString+","+newDChar+","+Integer.toString(distance+1);

                  // place the note in the queue
                  notes.enqueue(newNote);
                }
              }
            }
          }
        }
  }
   /**
   * Main method reads a file from StdInput of a specified format 
   * (see: https://github.com/thorehusfeldt/bads-labs/tree/master/mario/data) 
   * The file is read in to the String[] map and then examined for the shortest 
   * path from "S" to "F" via legal moves throgh " " not resulting in touching a "O"
   * 
   */
   public static void main(String[] args) {
		ResizingArrayStack posOfS= new ResizingArrayStack();

   	map = new String[numberOfStrings];

      //Read file into Sring[] map
      int index = 0;
      while(StdIn.hasNextLine()){
        map[index]= StdIn.readLine();
        for(int i=0; i<lengthOfStrings;i++){
          // find all the S'
          if (map[index].charAt(i)=='S'){
            String hereIsS=""+index+","+i;
            posOfS.push(hereIsS);
          }
        }
        index++;
      }
      //start in all the "S"s
      while(!posOfS.isEmpty()){
        oldNotes = new ST<String, Integer>(); // reset old notes

        String start=posOfS.pop()+","+"0"+","+"0"+","+1; // the start note
        notes.enqueue(start); // place the first note in the queue

        // dequeue all the notes from the queue
        while(!notes.isEmpty()){
          String tmp = notes.dequeue();
          findPath(tmp); // when a notes is dequeued, it is examined for possible "next notes"
        }
      }
      System.out.println(finalDistance);
  }
}

