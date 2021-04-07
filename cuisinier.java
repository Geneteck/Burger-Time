/*
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc

    (Mettre à jour le numéro de version à chaque modification de Cuisinier)
    Version : V.1.00
*/


public class Cuisinier extends Thread
{
  private int ptVie; // Point de vie de l'entité
  private int posLigne; // Correspond à la ligne actuel de cuisto
  private int posColonne; // Correspond à la colonne actuel du cuisto
  private int indicePos;  // Correspond à l'indice du cuisinier (calculé en fonction de ligne et de la colonne actuel)
  private char CharCuisto;
  private int Score;

  // Acceseur Setter & Getter des attributs

  // Renvoie les poits de vie du cuisto
  public int getVie() { return this.ptVie; } // Renvoie les points de vie

  public void setVie(int v) { this.ptVie = v; }

  public int getPosLigne(){ return this.posLigne; }

  public void setPosLigne(int lig) { this.posLigne = lig; }

  public int getPosColonne(){ return this.posColonne; }

  public void setPosColonne(int col) { this.posColonne = col; }

  public void setIndicePos(int ind) { this.indicePos = ind;}

  public int getIndicePos() { return this.indicePos; }

  public void setCharCuisinier(char c) { this.CharCuisto = c;}

  public char getCharCuisinier() { return this.CharCuisto;}

  public int getScore(){ return this.Score; }

  public void setScore(int score) { this.Score =  score; }

  public void ClearCuisier(Plateau p)
  {
    char dyn[][] = p.getMapDynamic();

    int col = this.getPosColonne();
    int lig = this.getPosLigne();

    if( p.getCharat(dyn, lig, col-1) != 'C' || p.getCharat(dyn, lig, col-1) != 'O' || p.getCharat(dyn, lig, col-1) != 'S' || p.getCharat(dyn, lig, col+1) != 'C' || p.getCharat(dyn, lig, col+1) != 'O' || p.getCharat(dyn, lig, col-1) != 'S' )
      p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne(), ' ');
  }

  public boolean RencontreMonstre(int ligne, int col)
  {
    int ligneC = this.getPosLigne();
    int colC = this.getPosColonne();

    if(ligne == ligneC && col == colC)
       return true;
    else
       return false;
  }

  public boolean PartieFinie()
  {
    if(this.getVie() < 1)
      return true;
    else
      return false;
  }

  public void Affichage() { System.out.print(" SCORE : "+this.getScore() + "\n                                                " + "Vie du cuisinier : "+ this.getVie()); }
  // Méthode possible du cuisinier
  // public void jetPoivre()

  public Cuisinier(int lig, int col)
  {
    setVie(3);
    setPosColonne(col);
    setPosLigne(lig);
    setCharCuisinier('J');
    setScore(0);
  }
}
