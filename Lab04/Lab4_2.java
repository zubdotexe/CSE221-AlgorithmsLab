
package lab4_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab4_2{
    
    static String words[] = new String[10];
    
    public static void main(String[] args) throws FileNotFoundException{
        
        File file = new File("F:/CSE221/lab4_input.txt");
        Scanner sc = new Scanner(file);
        String splitted[];
        String letters[] = new String[10];
        
        for(int i = 0; i < 10; i++){
            String line = sc.nextLine();
            splitted = line.split(": ");
            letters[i] = splitted[0];
            words[i] = splitted[1];
        }
        String s1 = "MWCADBOE";
        String s2 = "DMWCAROP";
        int m = s1.length();
        int n = s2.length();
        
        lcs(s1, s2, m, n);
    }
    
    public static void lcs(String x, String y, int m, int n){
        
        int lcs_table[][] = new int[m + 1][n + 1];
        
        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++){
                if(i == 0 || j == 0)
                    lcs_table[i][j] = 0;
                
                else if(x.charAt(i - 1) == y.charAt(j - 1))
                    lcs_table[i][j] = lcs_table[i - 1][j - 1] + 1;
                
                else 
                    lcs_table[i][j] = max(lcs_table[i - 1][j], lcs_table[i][j - 1]);
            }
        }
        int matches = lcs_table[m][n];
        char lcs[] = new char[matches];
        int i = m;
        int j = n;
        
        while(i > 0 & j > 0){
            if(x.charAt(i - 1) == y.charAt(j - 1)){
                lcs[matches - 1] = x.charAt(i - 1);
                matches--;
                i--;
                j--;
            }
            else if(lcs_table[i - 1][j] > lcs_table[i][j - 1])
                i--;
            
            else
                j--;    
        }
        
        System.out.println(lcs.length);
        
        for(int k = 0; k < lcs.length; k++){
            for(int l = 0; l < words.length; l++){
                char word[] = (words[l].toUpperCase()).toCharArray();
                if(word[0] == lcs[k])
                    System.out.print(words[l]+" ");
            }
        }
    }
    
    public static int max(int i, int j){
        
        if(i >= j)
            return i;
        
        else 
            return j;
    }
}
