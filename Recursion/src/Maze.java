import java.util.ArrayList;
import java.util.List;

public class Maze {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int[][] map = new int[8][7];

        for (int i = 0; i < 7 ; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        for (int i = 0; i < 8 ; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        map[3][1] = 1;
        map[3][2] = 1;

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 7; j++) {

                System.out.print(map[i][j]+"\t");
            }
            System.out.println();
        }

        setMap(1,1,map);

        System.out.println();

        System.out.println("*****************");

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 7; j++) {

                System.out.print(map[i][j]+"\t");
            }
            System.out.println();
        }
    }

    public static boolean setMap(int i, int j, int[][] map){

            if(map[6][5] == 2){
                return true;
            }

            if(map[i][j]==0){
                map[i][j] = 2;
                if (setMap(i+1,j,map)){
                    return true;
                }
                else if (setMap(i,j+1,map)){
                    return true;
                }
                else if (setMap(i-1,j,map)){
                    return true;
                }
                else if (setMap(i,j-1,map)){
                    return true;
                }else{
                    map[i][j] = 3;
                    return false;
                }
            }
            else{
                return false;
            }
    }

}
