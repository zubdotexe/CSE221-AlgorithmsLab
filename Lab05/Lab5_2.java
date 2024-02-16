
package lab5_221;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Lab5_2 {
   
    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("F:/CSE221/Lab5_input2.txt");
        Scanner sc = new Scanner(file);
        String line;
        String splitted[];
        
        line = sc.nextLine();
        splitted = line.split(" ");
        int maxWeight = Integer.parseInt(splitted[0]);
        
        line = sc.nextLine();
        splitted = line.split(" ");
        int noOfTrophies = Integer.parseInt(splitted[0]);
        
        String clubs[] = new String[noOfTrophies];
        int weight[] = new int[noOfTrophies];
        double price[] = new double[noOfTrophies];
        
        for(int i = 0; i < noOfTrophies; i++){
            line = sc.nextLine();
            splitted = line.split(", ");
            clubs[i] = splitted[0];
            weight[i] = Integer.parseInt(splitted[1]);
            price[i] = Double.parseDouble(splitted[2]);
        }
        
        knapsack(weight, price, maxWeight, price.length, clubs);
    }
    
    public static void knapsack(int[] weight, double[] price, int maxWeight, int n, String[] clubs){
        
        double B[][] = new double[n + 1][maxWeight + 1];
        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= maxWeight; j++){
                B[i][j] = B[i - 1][j];
                
                if((j >= weight[i - 1]) && (B[i][j] < B[i - 1][j - weight[i - 1]] + price[i - 1]))
                    B[i][j] = B[i - 1][j - weight[i - 1]] + price[i - 1];
            }
        }
        
        double profit = B[n][maxWeight];
        
        System.out.println("Name of clubs whose trophies were sold: ");
        int k = 0;
        String reversedClubs[] = new String[n - 1];
        
        while(n != 0){
            if(B[n][maxWeight] != B[n - 1][maxWeight]){
                reversedClubs[k++] = clubs[n - 1];
                maxWeight = maxWeight - weight[n - 1];
            }
            
            n--;
        }
        
        for(int i = reversedClubs.length - 1; i >= 0; i--){
            if(reversedClubs[i] != null){
                System.out.print(reversedClubs[i]);
                
                if(i != 0)
                    System.out.print(" -> ");
            }
        }
        System.out.println("\n"+"Maximum money he earned: "+ profit);
    }
}
