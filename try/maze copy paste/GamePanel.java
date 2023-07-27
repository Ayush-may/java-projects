import java.io.IOException;

public class GamePanel {
   String[][] maze = {   
   		{"   #############"},
   		{"#        #  #   "},
   		{"#  #######  #  #"},
   		{"#     #  #     #"},
   		{"####  #  #  #  #"},
   		{"#     #     #  #"},
   		{"#  #  #  #######"},
   		{"#  #  #     #  #"},
   		{"#  ####  ####  #"},
   		{"#               "},
   		{"#############   "}
   			};
	
   GamePanel(){
      printMaze();
      System.out.println("\f");
      printMaze();         printMaze();         printMaze();         printMaze();         printMaze();
      // clrscr();
   }
	
   void printMaze() {
      for(int i=0;i<maze.length;i++) {
         for(int j=0;j<maze[i].length;j++) {
            System.out.println(maze[i][j]);
         }
      }
   }
   public static void clrscr(){
   
       //Clears Screen in java
   
      try {
      
         if (System.getProperty("os.name").contains("Windows"))
         
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
         
         else
         
            Runtime.getRuntime().exec("clear");
      
      } catch (IOException | InterruptedException ex) {}
   
   }
   
   public static void main(String arg[]){
      new GamePanel();
   }
}
