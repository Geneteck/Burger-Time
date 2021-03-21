/* 
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc

    (Mettre à jour le numéro de version à chaque modification de Plateau)
    Version : V.1.00
*/


import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Plateau
{
  char AFF_LIMITE = 'X';
  char AFF_SOL = '_';
  char AFF_ECHELLE ='#';
  char AFF_VIDE =' ';

  private int NB_LIGNES; // Nombre de ligne du plateau
  private int NB_COLONNES; // Nombre de colonne du plateau
  private char mapStatic[][]; // Le mapStatic du jeux representer par un tableau a 2 dimension
  private char mapDynam[][];// Genre pour les thread quoi

  public Cuisinier c;

  public void setNbLigne(int n) { this.NB_LIGNES = n; }

  public int getNbLigne() { return this.NB_LIGNES; }

  public void setNbCol(int n) { this.NB_COLONNES = n; }

  public int getNbCol() { return this.NB_COLONNES; }

  public int getID(int lig, int col) { return (this.NB_COLONNES*lig)+col; }

  public void modifieCaseStatic(int lig, int col, char c) { mapStatic[lig][col] = c; }

  public void modifieCaseDynamique(int lig, int col, char c){ mapDynam[lig][col] = c; }

  // Fonction valide vérifie si le déplacement (cuisto) désiré est réalisable
  public boolean valide(char c, int ligCuisto, int colCuisto)
  {
    boolean verif = false;
    switch(c)
    {
      case 'z' :
      {
        if(mapStatic[ligCuisto][colCuisto] == AFF_ECHELLE) { verif = true;}
        break;
      }

      case 'q' :
      {
        if(mapStatic[ligCuisto][colCuisto-1] != AFF_LIMITE && mapStatic[ligCuisto][colCuisto-1] != AFF_VIDE) { verif = true;}
        break;
      }

      case 's' :
      {
        if(mapStatic[ligCuisto][colCuisto] == AFF_ECHELLE) { verif = true;}
        break;
      }

      case 'd' :
      {
        if(mapStatic[ligCuisto][colCuisto+1] != AFF_LIMITE && mapStatic[ligCuisto][colCuisto+1] != AFF_VIDE) { verif = true;}
        break;
      }
    }
    return verif;
  }

  // Fonction appelé lorsque l'on demande le déplacement du cuisinier, déplacement asynchrone
public void DeplacementCuisinier(Cuisinier cuisto)
{
  for (int i = 0 ; i<200; i++ )
  {
    Scanner sc = new Scanner(System.in);  // Create a Scanner object
    System.out.println("Déplacer le cuisto");

    char touche = sc.next().charAt(0);    // Read user input

      modifieCaseDynamique(cuisto.getPosLigne(), cuisto.getPosColonne(), ' ');
      if(touche == 'z' && this.valide('z', cuisto.getPosLigne(), cuisto.getPosColonne()))
      {
        cuisto.setPosLigne(cuisto.getPosLigne()-1);
      }

      else if(touche == 'q' && this.valide('q', cuisto.getPosLigne(), cuisto.getPosColonne()))
      {
        cuisto.setPosColonne(cuisto.getPosColonne()-1);
      }
      else if(touche == 'd'  && this.valide('d', cuisto.getPosLigne(), cuisto.getPosColonne()))
      {
        cuisto.setPosColonne(cuisto.getPosColonne()+1);
      }

      else if(touche == 's'  && this.valide('s', cuisto.getPosLigne(), cuisto.getPosColonne()))
      {
        cuisto.setPosLigne(cuisto.getPosLigne()+1);
      }

      modifieCaseDynamique(cuisto.getPosLigne(), cuisto.getPosColonne(), cuisto.getCharCuisinier());
      System.out.println("Je suis ici : lig : "+cuisto.getPosLigne()+" , col :"+cuisto.getPosColonne());
      affiche();
  }
}

  // Fonction affiche permet de visualiser l'état du plateau
  public void affiche()
  {
    for(int i=-1; i<=this.NB_LIGNES;i++)
    {
      for(int j=-1; j<=this.NB_COLONNES; j++)
      {
        if (i == -1 || j == -1 || i == NB_LIGNES || j ==  NB_COLONNES) // Permet l'affichage des bords du tableau
          System.out.print(AFF_LIMITE);

        else if( mapDynam[i][j] == 'S' || mapDynam[i][j] == 'O' || mapDynam[i][j] == 'C' || mapDynam[i][j] == 'J' )
          System.out.print(mapDynam[i][j]);

        else
          System.out.print(mapStatic[i][j]);
      }
      System.out.println("\n");
    }
  }

  // Constructeur(s) de la classe spécifique
  public Plateau(int lig, int col)
  {
    this.setNbLigne(lig);
    this.setNbCol(col);
    this.mapStatic = new char[lig][col];
    this.mapDynam = new char[lig][col];
  }

  // Plateau() est le constructeur
  public Plateau()
  {
    this.NB_LIGNES = 4;
    this.NB_COLONNES = 4;
    this.mapStatic = new char[this.NB_LIGNES][this.NB_COLONNES];
  }

  // Modifie chaque case d'une nouvelle instance de la classe Plateau, par des caractères définis plus haut
  public void PlateauNiveau1()
  {
    modifieCaseStatic(0,0,AFF_SOL);
    modifieCaseStatic(0,1,AFF_SOL);
    modifieCaseStatic(0,2,AFF_SOL);
    modifieCaseStatic(0,3,AFF_SOL);
    modifieCaseStatic(0,4,AFF_SOL);
    modifieCaseStatic(0,5,AFF_SOL);
    modifieCaseStatic(0,6,AFF_SOL);
    modifieCaseStatic(0,7,AFF_SOL);
    modifieCaseStatic(0,8,AFF_SOL);
    modifieCaseStatic(0,9,AFF_SOL);
    modifieCaseStatic(0,10,AFF_SOL);
    modifieCaseStatic(0,11,AFF_SOL);
    modifieCaseStatic(0,12,AFF_SOL);
    modifieCaseStatic(0,13,AFF_ECHELLE);

    modifieCaseStatic(1,0,AFF_SOL);
    modifieCaseStatic(1,1,AFF_SOL);
    modifieCaseStatic(1,2,AFF_SOL);
    modifieCaseStatic(1,3,AFF_SOL);
    modifieCaseStatic(1,4,AFF_ECHELLE);
    modifieCaseStatic(1,5,AFF_SOL);
    modifieCaseStatic(1,6,AFF_SOL);
    modifieCaseStatic(1,7,AFF_SOL);
    modifieCaseStatic(1,8,AFF_VIDE);
    modifieCaseStatic(1,9,AFF_VIDE);
    modifieCaseStatic(1,10,AFF_SOL);
    modifieCaseStatic(1,11,AFF_SOL);
    modifieCaseStatic(1,12,AFF_SOL);
    modifieCaseStatic(1,13,AFF_ECHELLE);

    modifieCaseStatic(2,0,AFF_ECHELLE);
    modifieCaseStatic(2,1,AFF_SOL);
    modifieCaseStatic(2,2,AFF_SOL);
    modifieCaseStatic(2,3,AFF_SOL);
    modifieCaseStatic(2,4,AFF_SOL);
    modifieCaseStatic(2,5,AFF_SOL);
    modifieCaseStatic(2,6,AFF_SOL);
    modifieCaseStatic(2,7,AFF_SOL);
    modifieCaseStatic(2,8,AFF_SOL);
    modifieCaseStatic(2,9,AFF_SOL);
    modifieCaseStatic(2,10,AFF_SOL);
    modifieCaseStatic(2,11,AFF_SOL);
    modifieCaseStatic(2,12,AFF_SOL);
    modifieCaseStatic(2,13,AFF_SOL);

    modifieCaseStatic(3,0,AFF_SOL);
    modifieCaseStatic(3,1,AFF_SOL);
    modifieCaseStatic(3,2,AFF_SOL);
    modifieCaseStatic(3,3,AFF_SOL);
    modifieCaseStatic(3,4,AFF_SOL);
    modifieCaseStatic(3,5,AFF_SOL);
    modifieCaseStatic(3,6,AFF_SOL);
    modifieCaseStatic(3,7,AFF_SOL);
    modifieCaseStatic(3,8,AFF_SOL);
    modifieCaseStatic(3,9,AFF_SOL);
    modifieCaseStatic(3,10,AFF_SOL);
    modifieCaseStatic(3,11,AFF_SOL);
    modifieCaseStatic(3,12,AFF_SOL);
    modifieCaseStatic(3,13,AFF_SOL);
  }

  public void Test()
  {
    modifieCaseDynamique(0,0,'J');
    modifieCaseDynamique(1,3,'S');
    modifieCaseDynamique(2,2,'O');
    modifieCaseDynamique(3,1,'C');
  }

  public void AfficheCuisinier(Cuisinier c)
  {
    char couille = c.getCharCuisinier();
    int ligne = c.getPosLigne();
    int colonne = c.getPosColonne();
    modifieCaseDynamique(ligne, colonne, couille);
  }

  // Main
 public static void main(String[] args) {
    Plateau p = new Plateau(4,14);
    //Cuisinier cuisto = new Cuisinier(0, 4, 5);
    p.PlateauNiveau1();
    Cuisinier c = new Cuisinier(0, 0);

    p.DeplacementCuisinier(c);


    //Entite e = new Entite(p.getID(p.getNbLigne()-1, p.getNbCol()-1),p.getNbLigne()-1,p.getNbCol()-1);
    //p.DeplacementCuisinier(e);
 }
}
