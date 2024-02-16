
package lab5_221;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Lab5_1{

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("F:/CSE221/Lab5_input1.txt");
        Scanner sc = new Scanner(file);
        String splitted[];
        String line;
        
        line = sc.nextLine();
        splitted = line.split(" ");
        int budget = Integer.parseInt(splitted[0]); 
        
        line = sc.nextLine();
        splitted = line.split(" ");
        int noOfPlayers = Integer.parseInt(splitted[0]);
        
        String players[] = new String[noOfPlayers];
        int price[] = new int[noOfPlayers];
        int form[] = new int[noOfPlayers];
        
        for(int i = 0; i < noOfPlayers; i++){
            line = sc.nextLine();
            splitted = line.split(", ");
            players[i] = splitted[0];
            price[i] = Integer.parseInt(splitted[1]);
            form[i] = Integer.parseInt(splitted[2]);
        }
        
        knapsackDyProg(price, form, budget, form.length, players);
    }
    
    public static void knapsackDyProg(int[] price, int[] form, int budget, int n, String[] players) {
	int B[][] = new int[n + 1][budget + 1];
	
        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= budget; j++){
                B[i][j] = B[i - 1][j];
                
                if((j >= price[i - 1]) && (B[i][j] < B[i - 1][j - price[i - 1]] + form[i - 1]))
                    B[i][j] = B[i - 1][j - price[i - 1]] + form[i - 1];
            }
        }
        
        int profit = B[n][budget];
        System.out.println("Bought players:");
        int k = 0;
        String reversedPlayers[] = new String[n - 1];
        
        while(n != 0){
            if(B[n][budget] != B[n - 1][budget]){
                reversedPlayers[k++] = players[n - 1];
                budget = budget - price[n - 1];
            }

            n--;
        }
        
        for(int i = reversedPlayers.length - 1; i >= 0; i--){
            if(reversedPlayers[i] != null){
                System.out.print(reversedPlayers[i]);
                
                if(i != 0)
                    System.out.print(" -> ");
            }
        }
        System.out.println("\n"+"Maxium summation of form: "+ profit);
    }
}
