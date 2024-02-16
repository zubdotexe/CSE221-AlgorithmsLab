
package lab4_1;

public class Lab4_1{

    public static void main(String[] args){
        
        String s1 = "CDEFGABC";
        String s2 = "CEFDABGAC";
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
        for(int k = 0; k < lcs.length; k++)
            System.out.print(lcs[k]);
        
        System.out.println();
        
        double result = (double)lcs.length/m * 100;
        if(result >= 50)
            System.out.println(result+"% PASSED");
        else 
            System.out.println(result+"% FAIlED");
    }
    
    public static int max(int i, int j){
        
        if(i >= j)
            return i;
        
        else 
            return j;
    }
}