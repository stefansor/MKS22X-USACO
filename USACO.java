import java.util.*;
import java.io.*;


public class USACO{
  //for bronze problem
  private static int[] lakeorders;
  private static int[][] land;
  private static int[][] newland;
  private static int[][] stompingorders;





  public static int bronze(String filename){
    return 3;//so it compiles
  }
//each separator will separate the lake instructions the actual elevation
// and the stomping instructions
  private static void seperator1(String fn){
    File text = new File(fn);
    Scanner inf = new Scanner(text);
    for(int i = 0; i < 3; i++){
      lakeorders[i] = inf.nextInt()
    }
  }


  

}
