import java.util.*;
import java.io.*;
import java.util.Arrays;


public class USACO{
  //for bronze problem
  private static int[] lakeorders;
  private static int[][] land;
  private static int[][] newwater;
  private static int[][] stompingorders;
  //for silver problem
  private static int[] boardorders;
  private static char[][] board;
  private static int[] badgoodgrass;
  private static int[][] moves = new int[][]{
    {0, 1},
    {0, -1},
    {1, 0},
    {-1, 0}
  };





  public static int bronze(String filename) throws FileNotFoundException{
    separator3(filename);
    for(int i = 0; i < stompingorders.length; i++){
      stomp(stompingorders[i][0], stompingorders[i][1], stompingorders[i][2]);
    }
    return lakecalc(lakeorders[2]);
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


  //-----------------------------------------------------------------------


  private static void setUp(String filename)throws FileNotFoundException{

    //sets up intructions of the board dimensions and time

    boardorders = new int[3];
    File text1 = new File(filename);
    Scanner inf1 = new Scanner(text1);
    for(int i = 0; i < 3; i++){
      boardorders[i] = inf1.nextInt();
    }

    //sets up board with the grid in file

    board = new char[boardorders[0]][boardorders[1]];
    File text2 = new File(filename);
    Scanner inf2 = new Scanner(text2);
    String waste = inf2.nextLine();
    int r = 0;
    for(int i = 0; i < boardorders[0]; i++){
      String line = inf2.nextLine();
      for(int j = 0; j < boardorders[1]; j++){
        char a = line.charAt(j);
        board[i][j] = a;
      }
    }

    //sets up badgoodgrass my dudes
    badgoodgrass = new int[4];
    int a = Integer.parseInt(inf2.next());
    int b = Integer.parseInt(inf2.next());
    int c = Integer.parseInt(inf2.next());
    int d = Integer.parseInt(inf2.next());
    badgoodgrass[0] = a;
    badgoodgrass[1] = b;
    badgoodgrass[2] = c;
    badgoodgrass[3] = d;

  }

  private static boolean addCow(int r, int c, char[][] board){

    if(r < 0 || r >= board.length
    || c < 0 || c >= board[0].length
    || board[r][c] == '*'){
      return false;
    }

    board[r][c] = 'c';
    return true;
  }

  private static boolean removeCow(int r, int c, char[][] board){
    if(board[r][c] == 'c'){
      board[r][c] = '.';
      return true;
    }
    return false;
  }

  public static int silver(String filename)throws FileNotFoundException{
    setUp(filename);
    return silverhelp(badgoodgrass[0] - 1, badgoodgrass[1] - 1,
     badgoodgrass[2] - 1, badgoodgrass[3] - 1, board, boardorders[2]);
  }

  private static int silverhelp(int r1, int c1, int r2, int c2, char[][] board, int t){
    if(r1 == r2 && c1 == c2 && t == 0){
      return 1;
    }
    if(t == 0){
      return 0;
    }
    int sum = 0;
    for(int i = 0; i < 4; i++){
      if(addCow(r1, c1, board)){
        sum += silverhelp(r1 + moves[i][0], c1 + moves[i][1], r2, c2, board, t - 1);
        removeCow(r1, c1, board);
      }
    }
    return sum;
  }

  public static void main(String[] args) throws FileNotFoundException{
    /*separator3("makelake1.txt");
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
    int[] nums = new int[]{
      2, 3, 4, 5, 6, 7
    };
    System.out.println(max(nums));
    System.out.println(bronze("makelake4.txt"));*/
    /*setUp("ctravel2.txt");
    System.out.println(addCow(0, 2, board));
    for(int i = 0; i < board.length; i++){
      System.out.println(Arrays.toString(board[i]));
    }
    System.out.println(addCow(0,3,board));
    for(int i = 0; i < board.length; i++){
      System.out.println(Arrays.toString(board[i]));
    }
    System.out.println(removeCow(0,2,board));
    for(int i = 0; i < board.length; i++){
      System.out.println(Arrays.toString(board[i]));
    }*/
    System.out.println(silver("ctravel5.txt"));
  }



}
