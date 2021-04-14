/*
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc

*/


public class Burger
{
  // Déclaration des attributs de la classe Cuisinier

  private int Ligne;
  private int Colonne;
  private char Burger[][];

  // Représentent les éléments des burgers
  char AFF_PAINH = '*';
  char AFF_PAINB = '*';
  char AFF_STEACK = '~';
  char AFF_FROMAGE = '=';

  // Méthodes d'accès

  public int getLigne(){ return this.Ligne; }
  public void setLigne(int lig) { this.Ligne = lig; }
  public int getColonne(){ return this.Colonne; }
  public void setColonne(int col) { this.Colonne = col; }
  public char getTabBurger(int lig, int col) { return this.Burger[lig][col]; }

  // Méthodes principales de la classe Burger

  // Vérifie si tous les pains d'un burger sont tombés, utiliser dans la méthode calcScore
  public boolean pain(char c1, char c2, char c3)
  {
    Burger ref = new Burger();
    if(ref.getTabBurger(0, 0) == c1 && ref.getTabBurger(0, 1) == c2 && ref.getTabBurger(0, 2)==c3)
      return true;
    else return false;
  }

  // Vérifie si tous les fromages d'un burger sont tombés, utiliser dans la méthode calcScore
  public boolean fromage(char c1, char c2, char c3)
  {
    Burger ref = new Burger();
    if(ref.getTabBurger(1, 0) == c1 && ref.getTabBurger(1, 1) == c2 && ref.getTabBurger(1, 2)==c3)
      return true;
    else return false;
  }

  // Vérifie si tous les steacks d'un burger sont tombés, utiliser dans la méthode calcScore
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

  // Constructeurs de la classe Burger à paramètres par défaut
  public Burger(int lig, int colonne)
  {
    setLigne(lig);                              // Ligne à laquelle on va commencer à placer les éléments de burger dans le plateau
    setColonne(colonne);
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
