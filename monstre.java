/* 
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc

    (Mettre à jour le numéro de version à chaque modification de Monstre)
    Version : V.1.00
*/


public class Monstre extends Thread
{
  private int NbMonstre; //nombre de monstre que l'on pourra faire varier en fonction de la difficulté
  private int indicePos; // Attribut qui correspond à l'indice de l'entité sur le plateau

  // La ligne et la colonne d'une entité
  private int posLigne; // Correspond à la ligne actuel de l'entité
  private int posColonne; // Correspond à la colonne actuel de l'entité
  private char CharMonstre; //Correspond au caractères du monstre sur le plateau

  //public Plateau p; //permet d'utiliser les accesseur de plateau

  // méthode d'écriture et de lécture de la class mere Monstre
  public int getIndicePos(){ return this.indicePos; }

  //public int getCalculIndice(int ligne, int col) { return (p.getNbCol()*ligne)+col; } //a vérifier

  public void setIndicePos(int i) { this.indicePos = i; }

  public int getPosLigne() { return this.posLigne; }

  public void setPosLigne(int lig) { this.posLigne = lig; }

  public int getPosColonne() { return this.posColonne; }

  public void setPosColonne(int col) {  this.posColonne = col; }

  public int getNbMonstre() { return this.NbMonstre; }

  public void setNbMonstre(int nb) {  this.NbMonstre = nb; }

  public int getCharMonstre() { return this.CharMonstre; }

  public void setCharMonstre(char c) {  this.CharMonstre = c; }

  public void run(){
    //a voir plus tard
    //appliquer la fonction de calcul du plus cours chemin afin de rattraper le Joueur
    //utiliser des wait quand un monstre aura prit du poivre dans la tronche, ou quand il aura toucher le Joueur
    //ne pas oublier de start les monstre avec .start dans le jeu
  }

  public static void main(String[] args)
  {
    Oeuf o = new Oeuf(0, 0, 0);
    Saucisse s = new Saucisse(0, 0, 0);
  }
}

class Oeuf extends Monstre
{
  public Oeuf(int nb, int ligne, int col)
  {
    super();
    setNbMonstre(nb);
    setPosLigne(ligne);
    setPosColonne(col);
    setCharMonstre('O');
  }
}

class Saucisse extends Monstre
{
  public Saucisse(int nb, int ligne, int col)
  {
    super();
    setNbMonstre(nb);
    setPosLigne(ligne);
    setPosColonne(col);
    //setIndicePos(getCalculIndice(ligne, col));
    setCharMonstre('S');
  }
}

class Cornichon extends Monstre
{
  public Cornichon(int nb, int ligne, int col)
  {
    setNbMonstre(nb);
    setPosLigne(ligne);
    setPosColonne(col);
    //setIndicePos(getCalculIndice(ligne, col));
    setCharMonstre('C');
  }
}
