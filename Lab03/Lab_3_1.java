import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Lab_3_1{

    static int caseCounter = 0;
    public static void main(String[] args) throws FileNotFoundException{

    File file = new File("F:/CSE221/Lab_3_input_1.txt");
        Scanner sc = new Scanner(file);
        String splitted[];
        String line = sc.nextLine();
        splitted = line.split(" ");
        int n = Integer.parseInt(splitted[0]); // no of countries
        int m = Integer.parseInt(splitted[1]); // no of flights
        int x = Integer.parseInt(splitted[2]); // location of intercontinental
        int q = Integer.parseInt(splitted[3]); // no of missions
        int matrix[][] = new int[m + 1][m + 1];

        for(int i = 0; i < m; i++){
            line = sc.nextLine();
            splitted = line.split(" ");
            int u = Integer.parseInt(splitted[0]);
            int v = Integer.parseInt(splitted[1]);
            int w = Integer.parseInt(splitted[2]);
            matrix[u][v] = w;
        }

        line = sc.nextLine();
        splitted = line.split(" ");
        int source1 = Integer.parseInt(splitted[0]);
        int dest1 = Integer.parseInt(splitted[1]);
        line = sc.nextLine();
        splitted = line.split(" ");
        int source2 = Integer.parseInt(splitted[0]);
        int dest2 = Integer.parseInt(splitted[1]);

        shortestPathFinder(matrix, source1, dest1);
        shortestPathFinder(matrix, source2, dest2);

    }

    public static int minCostFinder(int dis[], Boolean vis[]){

        int minCost = Integer.MAX_VALUE;
        int minIndex = -1;

        for(int v = 0; v < dis.length; v++){
            if(!vis[v] && dis[v] <= minCost){
                minCost = dis[v];
                minIndex = v;
            }
	    }

        return minIndex;
    }

    public static void printCost(int[] dis, int dest){
        caseCounter++;
        System.out.println("Case "+caseCounter+":");
        System.out.println(dis[dest]);
    }

    public static void shortestPathFinder(int[][] matrix, int source, int dest){

        int dis[] = new int[matrix.length];
        Boolean vis[]= new Boolean[matrix.length];

        for (int i = 0; i < matrix.length; i++){
            dis[i] = Integer.MAX_VALUE;
            vis[i] = false;
        }

        dis[source] = 0;

        for(int count = 0; count < matrix.length; count++){
            int u = minCostFinder(dis, vis);
            vis[u] = true;

            for(int v = 0; v < matrix.length; v++){
                if(!vis[v] && matrix[u][v] != 0){
                    int cost = dis[u] + matrix[u][v];
                    if(cost < dis[v])
                        dis[v] = cost;

                }
            }
        }

        printCost(dis, dest);
    }
}
