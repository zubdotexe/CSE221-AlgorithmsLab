import java.util.Scanner;
public class Lab1Task3{
public static void main(String []args){
  System.out.println("Please enter the value of n:");
  Scanner bot= new Scanner(System.in);
  int n=bot.nextInt();
  int a=n;
  int sum=0;
  while (n>0){
    int r=n%10;
    sum=sum+r*r*r;
    n=n/10;
  }
  if(a==sum){
    System.out.println("Armstrong Number");
  }
  else{
    System.out.println("not an Armstrong Number");
  }
}
}
