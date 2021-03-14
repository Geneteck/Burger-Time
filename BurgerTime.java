public class Burger
{
  //attributs
  private int posLigne;
  private int posColonne;

  private char pain;
  private char steak;
  private char salade;

  public char getCharPain() { return this.pain;}

  public void setCharPain(char pain) { this.pain = pain; }

  public char getCharSteak() { return this.steak; }

  public void setCharSteack(char steak) { this.steak = steak; }

  public char getCharSalade() { return this.salade;}

  public void setCharSalade(char salade) { this.salade = salade; }

  public int getPosLigne(){ return this.posLigne; }

  public void setPosLigne(int lig) { this.posLigne = lig; }

  public int getPosColonne(){ return this.posColonne; }

  public void setPosColonne(int col) { this.posColonne = col; }


  public Burger(int id, int col)
  {
      char b = new char[3][3];
      int[][] matriceEntiers = new int[3][3];
      for(int i = 0; i<3 ; i++)
      {
        for(int j = 0; j<3 ; j++)
        {
          if( i == 0 )
            b[i][j] = 'P';
          else if ( i == 1)
            b[i][j] = '~';
          else if ( i == 2)
           b[i][j] = 'K';
          else
            b[i][j] = 'P';
        }
      }
  }

  public static void main(String[] args)
  {

  }
}
