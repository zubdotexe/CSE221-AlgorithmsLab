import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab_3_2{
    static int y0; // yellow colored locations have been kept static to compare
    static int y1; // in another method
    static int y2;
    static int y3;

    public static void main(String[] args) throws FileNotFoundException{

        File file = new File("F:/CSE221/Lab_3_input_2.txt");
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();
        int v = Integer.parseInt(line);
        int e = Integer.parseInt(sc.nextLine());
        int matrix[][] = new int[v][v];
        String splitted[];

        for(int i = 0; i < e; i++){
            line = sc.nextLine();
            splitted = line.split(",");
            int x = Integer.parseInt(splitted[0]);
            int y = Integer.parseInt(splitted[1]);
            int w = Integer.parseInt(splitted[2]);
            matrix[x][y] = w;
        }
        int source = Integer.parseInt(sc.nextLine());
        int dest = Integer.parseInt(sc.nextLine());

        line = sc.nextLine();
        splitted = line.split(",");
        y0 = Integer.parseInt(splitted[0]); // all the yellow colored locations are named y
        y1 = Integer.parseInt(splitted[1]);
        y2 = Integer.parseInt(splitted[2]);
        y3 = Integer.parseInt(splitted[3]);

        shortestPathFinder(matrix, source, dest);

    }

    public static void shortestPathFinder(int[][] matrix, int source, int dest){

        int dis[] = new int[matrix.length];
        Boolean vis[] = new Boolean[matrix.length];

        for(int i = 0; i < matrix.length;  i++){
            dis[i] = Integer.MAX_VALUE;
            vis[i] = false;
        }
        dis[source] = 0;

        for(int i = 0; i < matrix.length; i++){
            int u = minCostFinder(dis, vis); // to find the vertex with the shortest distance
            vis[u] = true;

            for(int v = 0; v < matrix.length; v++){

                if(!vis[v] && matrix[u][v] != 0){
                    if(v == y0 || v == y1 || v == y2 || v == y3) // skipping the routes that
                        continue;                                // have the yellow colored
                                                                 // locations in them
                    int cost = dis[u] + matrix[u][v];
                    if(cost < dis[v])
                        dis[v] = cost;

                }
            }
        }
        printCost(dis, dest);
    }

    public static int minCostFinder(int[] dis, Boolean[] vis){
        int minCost = Integer.MAX_VALUE;
        int minIndex = -1;

        for(int i = 0; i < dis.length; i++){
            if(!vis[i] && dis[i] <= minCost){
                minCost = dis[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void printCost(int[] dis, int dest){

        System.out.println(dis[dest]);
    }
}
