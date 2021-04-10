/*
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc
    Fichier : Burger

*/


public class Burger
{
  // Déclaration des attributs

  private int Ligne;
  private int Colonne;
  private char Burger[][];

  char AFF_PAINH = '*';      //représente les pains haut du burger
  char AFF_PAINB = '*';      //représente les pains bas du burger
  char AFF_STEACK = '~';    //représente le steak du burger
  char AFF_FROMAGE = '=';   //représente le fromage du burger

  // Méthodes d'accès aux attributs

  public int getLigne(){ return this.Ligne; }
  public void setLigne(int lig) { this.Ligne = lig; }
  public int getColonne(){ return this.Colonne; }
  public void setColonne(int col) { this.Colonne = col; }
  public char getTabBurger(int lig, int col) { return this.Burger[lig][col]; }

  // Méthodes principales de la classe Burger

  
  public boolean pain(char c1, char c2, char c3)
  {
    Burger ref = new Burger();
    if(ref.getTabBurger(0, 0) == c1 && ref.getTabBurger(0, 1) == c2 && ref.getTabBurger(0, 2)==c3)
      return true;
    else return false;
  }

  public boolean fromage(char c1, char c2, char c3)
  {
    Burger ref = new Burger();
    if(ref.getTabBurger(1, 0) == c1 && ref.getTabBurger(1, 1) == c2 && ref.getTabBurger(1, 2)==c3)
      return true;
    else return false;
  }

  public boolean steack(char c1, char c2, char c3)
  {
    Burger ref = new Burger();
    if(ref.getTabBurger(2, 0) == c1 && ref.getTabBurger(2, 1) == c2 && ref.getTabBurger(2, 2)==c3)
      return true;
    else return false;
  }

  public void modifieBurger(int lig, int col, char c)
  {
    this.Burger[lig][col] = c;
  }

  // Constructeurs de la classe par défaut

  public Burger(int lig, int colonne)
  {
    setLigne(lig);                              // Ligne à laquelle on va commencer à placer les éléments de burger dans le plateau
    setColonne(colonne);
    this.Burger = new char[4][3];                  // Colonne à laquelle on commencer à placer les élements de burger dans le plateau
    this.modifieBurger(0, 0, '*');
    this.modifieBurger(0, 1, '*');
    this.modifieBurger(0, 2, '*');

    this.modifieBurger(1, 0, '=');
    this.modifieBurger(1, 1, '=');
    this.modifieBurger(1, 2, '=');

    this.modifieBurger(2, 0, '~');
    this.modifieBurger(2, 1, '~');
    this.modifieBurger(2, 2, '~');

    this.modifieBurger(3, 0, '*');
    this.modifieBurger(3, 1, '*');
    this.modifieBurger(3, 2, '*');
  }

  public Burger()
  {
    this.Burger = new char[4][3];

    this.modifieBurger(0, 0, '*');
    this.modifieBurger(0, 1, '*');
    this.modifieBurger(0, 2, '*');

    this.modifieBurger(1, 0, '=');
    this.modifieBurger(1, 1, '=');
    this.modifieBurger(1, 2, '=');

    this.modifieBurger(2, 0, '~');
    this.modifieBurger(2, 1, '~');
    this.modifieBurger(2, 2, '~');

    this.modifieBurger(3, 0, '*');
    this.modifieBurger(3, 1, '*');
    this.modifieBurger(3, 2, '*');
  }

}
