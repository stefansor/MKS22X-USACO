import java.util.*;
import java.io.*;
import java.util.Arrays;


public class USACO{
  //for bronze problem
  private static int[] lakeorders;
  private static int[][] land;
  private static int[][] newwater;
  private static int[][] stompingorders;





  public static int bronze(String filename) throws FileNotFoundException{
    separator3(filename);
    //separator1(filename);//lake comands
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
    newwater = new int[lakeorders[0]][lakeorders[1]];
    File text = new File(fn);
    Scanner inf = new Scanner(text);
    //to get ride of first four ints
    int waste = inf.nextInt() + inf.nextInt() + inf.nextInt() + inf.nextInt();
    int r = 0;
    for(int i = 0; i < lakeorders[0]; i++){
      for(int j = 0; j < lakeorders[1]; j++){
        int a = inf.nextInt();
        newwater[i][j] = a;
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
    //System.out.println(waste);
    //filling in the stomping instructions
    stompingorders = new int[lakeorders[3]][3];
    for(int i = 0; i < lakeorders[3]; i++){
      for(int j = 0; j < 3; j++){
        int a = inf.nextInt();
        //System.out.println(a);
        stompingorders[i][j] = a;
      }
    }
  }

  private static int max(int[] nums){
    int max = 0;
    for(int i = 0; i < nums.length; i++){
      if(nums[i] > max){
        max = nums[i];
      }
    }
    return max;
  }


  private static void stomp(int r, int c, int e){
    int[] elevations = new int[]{
      land[r-1][c-1],
      land[r-1][c],
      land[r-1][c+1],
      land[r][c-1],
      land[r][c],
      land[r][c+1],
      land[r+1][c-1],
      land[r+1][c],
      land[r+1][c+1]
    };
    for(int elev = e; elev > 0; elev--){
      int a = max(elevations);
      for(int i = 0; i < elevations.length; i++){
        if(elevations[i] == a){
          elevations[i]--;
          //System.out.println(elevations[i]);
        }
      }
    }
    land[r-1][c-1] = elevations[0];
    land[r-1][c] = elevations[1];
    land[r-1][c+1] = elevations[2];
    land[r][c-1] = elevations[3];
    land[r][c] = elevations[4];
    land[r][c+1] = elevations[5];
    land[r+1][c-1] = elevations[6];
    land[r+1][c] = elevations[7];
    land[r+1][c+1] = elevations[8];
  }

  private static int lakecalc(int waterlevel){
    int aggregatedepth = 0;
    for(int i = 0; i < land.length; i++){
      for(int j = 0; j < land[0].length; j++){
        if(land[i][j] < waterlevel){
          aggregatedepth += waterlevel - land[i][j];
        }
      }
    }
    return aggregatedepth * 72 * 72;
  }



  public static void main(String[] args) throws FileNotFoundException{
    separator3("makelake1.txt");
    System.out.println(Arrays.toString(lakeorders));
    for(int i = 0; i < land.length; i++){
      System.out.println(Arrays.toString(land[i]));
    }
    System.out.println();
    for(int i = 0; i < stompingorders.length; i++){
      System.out.println(Arrays.toString(stompingorders[i]));
    }

    //System.out.println(land[0][0]);
    stomp(1,4,4);
    stomp(1, 1, 10);
    for(int i = 0; i < land.length; i++){
      System.out.println(Arrays.toString(land[i]));
    }

    System.out.println(lakecalc(22));
    /*int[] nums = new int[]{
      2, 3, 4, 5, 6, 7
    };
    System.out.println(max(nums));*/

  }



}
