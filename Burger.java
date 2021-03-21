/* 
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc

    (Mettre à jour le numéro de version à chaque modification de Burger)
    Version : V.1.00
*/


public class Burger
{
  /* 
    Déclaration des attributs de la classe Burger :
    - posLigne pour récupérer la ligne d'un 
    - 
  */

  // Réfléchir à l'utilité des attributs
  private int posLigne;
  private int posColonne;

  private char pain;
  private char steak;
  private char salade;

  /* 
     Déclarations des différentes méthodes de la classe :
     --> Tous les accesseurs en lectures et en écritures des attributs dont on a besoin
  */

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


  // Burger(int lig, int col), est le constructeur définit avec des paramètres 
  public Burger()
  {
      char b[][] = new char[3][3];
      for(int i = 0; i<3 ; i++)
      {
        for(int j = 0; j<3 ; j++)
        {
          if( i == 0 ) { b[i][j] = 'P'; }
          else if ( i == 1) { b[i][j] = '~'; }
          else if ( i == 2) { b[i][j] = 'K'; }
          else { b[i][j] = 'P'; }
        }
      }
  }

  public static void main(String[] args)
  {

  }
}
