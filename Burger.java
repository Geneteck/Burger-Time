/*
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc

    (Mettre à jour le numéro de version à chaque modification de Burger)
    Version : V.1.00
*/


public class Burger
{
  public static void main(String[] args)
  {
    // Burger b = new Burger();
    // boolean verif = b.valideBurger();
    // if( verif ) System.out.println(" VRAI ");
    // else  System.out.println(" FAUX ");
  }

  char AFF_PAINH = '*';      //représente les pains haut du burger
  char AFF_PAINB = '*';      //représente les pains bas du burger
  char AFF_STEACK = '~';    //représente le steak du burger
  char AFF_FROMAGE = '=';   //représente le fromage du burger


  private int Ligne;
  private int Colonne;
  // private char Burger[][];

  public int getLigne(){ return this.Ligne; }

  public void setLigne(int lig) { this.Ligne = lig; }

  public int getColonne(){ return this.Colonne; }

  public void setColonne(int col) { this.Colonne = col; }

  // public char getTabBurger(int lig, int col) { return this.Burger[lig][col]; }

  //public void modifieBurger(int lig, int col, char c)
  //{
  //  this.Burger[lig][col] = c;
  //}

  //public boolean valideBurger()
  //{
  //  boolean verif = false;
  //  int nbrPain = 0;
  //  int nbrFromage = 0;
  //  int nbrSteack = 0;

  //  for(int i=0; i<this.getNbLigne(); i++)
  //  {
  //    for(int j=0; j<this.getNbColonne(); j++)
  //    {
  //    if( (i == 0 || i == 3) && this.getTabBurger(i, j) == AFF_PAIN )
  //          nbrPain = nbrPain + 1;
  //      else if ( i == 1 && this.getTabBurger(i, j) == AFF_FROMAGE)
  //          nbrFromage = nbrFromage + 1;
  //      else if (i == 2 && this.getTabBurger(i, j) == AFF_STEACK)
  //          nbrSteack = nbrSteack + 1;
  //    }
  //  }
  //  System.out.println(" nbSteack : "+nbrSteack +" nbrPain : "+nbrPain+" nbrFromage : "+ nbrFromage);
  //  if(nbrPain == 6 && nbrFromage == 3 && nbrSteack == 3) verif=true;

  //  return verif;
  //}

  // public void affichageBurger()
  // {
  //  for(int i=0; i<4; i++)
  //  {
  //    for(int j=0; j<3; j++)
  //    {
  //      System.out.print(getTabBurger(i, j));
  //    }
  //    System.out.print("\n");
  // }
  // }
  // Burger(int lig, int col), est le constructeur définit avec des paramètres


  public Burger(int lig, int colonne)
  {
    setLigne(lig);                              // Ligne à laquelle on va commencer à placer les éléments de burger dans le plateau
    setColonne(colonne);                          // Colonne à laquelle on commencer à placer les élements de burger dans le plateau

    // this.modifieBurger(ligne, colone, AFF_PAINB);
    // this.modifieBurger(ligne, colonne-1, AFF_PAINB);
    // this.modifieBurger(ligne, colonne+1, AFF_PAINB);
  }

}
