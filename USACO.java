import java.util.*;
import java.io.*;
import java.util.Arrays;


public class USACO{
  //for bronze problem
  private static int[] lakeorders;
  private static int[][] land;
  private static int[][] newland;
  private static int[][] stompingorders;





  public static int bronze(String filename) throws FileNotFoundException{
    separator1(filename);//lakecommands
    //separator2(filename);//grid
    //separator3(filename);//stomp commands
    return 3;//so it compiles
  }
//each separator will separate the lake instructions the actual elevation
// and the stomping instructions
  private static void separator1(String fn)throws FileNotFoundException{
    lakeorders = new int[4];
    File text = new File(fn);
    Scanner inf = new Scanner(text);
    for(int i = 0; i < 4; i++){
      lakeorders[i] = inf.nextInt();
    }
  }

  private static void separator2(String fn)throws FileNotFoundException{
    separator1(fn);
    land = new int[lakeorders[0]][lakeorders[1]];
    newland = new int[lakeorders[0]][lakeorders[1]];
    File text = new File(fn);
    Scanner inf = new Scanner(text);
    //to get ride of first four ints
    int waste = inf.nextInt() + inf.nextInt() + inf.nextInt() + inf.nextInt();
    int r = 0;
    for(int i = 0; i < lakeorders[0]; i++){
      for(int j = 0; j < lakeorders[1]; j++){
        int a = inf.nextInt();
        newland[i][j] = a;
        land[i][j] = a;
      }
    }
  }

  private static void separator3(String fn) throws FileNotFoundException{
    separator2(fn);
    File text = new File(fn);
    Scanner inf = new Scanner(text);
    //gets ride of lakeorders and grid to get to stomping instructions
    int waste = 0;
    for(int i = 0; i < lakeorders[0] * lakeorders[1] + 4; i++){
      int a = inf.nextInt();
      waste += a;
    }
    System.out.println(waste);
    //filling in the stomping instructions
    stompingorders = new int[lakeorders[3]][3];
    for(int i = 0; i < lakeorders[3]; i++){
      for(int j = 0; j < 3; j++){
        int a = inf.nextInt();
        System.out.println(a);
        stompingorders[i][j] = a;
      }
    }
  }




  public static void main(String[] args) throws FileNotFoundException{
    separator3("makelake1.txt");
    System.out.println(Arrays.toString(lakeorders));
    for(int i = 0; i < land.length; i++){
      System.out.println(Arrays.toString(land[i]));
    }
    System.out.println();
    for(int i = 0; i < newland.length; i++){
      System.out.println(Arrays.toString(land[i]));
    }
    System.out.println();
    for(int i = 0; i < stompingorders.length; i++){
      System.out.println(Arrays.toString(stompingorders[i]));
    }

    //System.out.println(land[0][0]);


  }



}
