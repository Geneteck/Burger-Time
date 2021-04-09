import java.io.*;
import java.util.*;



class BROUILLON
{
  public static void main(String[] args) {
    String str = "14";
    int test = Integer.parseInt(str);
    System.out.println(str+" ; "+test);
  }
}

for(int i=0; i<5;i++)
{
  ligne = s.readLine();
  System.out.println("Score "+ligne);
  this.getLesScores().add(Integer.parseInt(ligne));
}
 s.close();

 this.getLesPseudos().add(p.readLine());
 for(int j=0; j<5;j++)
 {
    ligne = p.readLine();
    System.out.println("Pseudo " + ligne);
    this.getLesPseudos().add(ligne);
 }
 p.close();
