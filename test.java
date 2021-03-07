import java.util.*;


public class test {
  private int num;

  test(int n ){
    this.num = n;
  }
  public static void main(String[] args)
  {
    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    System.out.println("Enter username");
    Plateau p = new Plateau(4, 4);
    String userName = myObj.nextLine();  // Read user input
    System.out.println("Username is: " + userName);  // Output user input
  }
}
