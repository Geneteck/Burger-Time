/*
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc

    (Mettre à jour le numéro de version à chaque modification de Plateau)
    Version : V.1.01
*/


import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Plateau
{
  public static void main(String[] args)                        // Main de Plateau pour vérifier que le jeu fonctionne
  {
     Burger b = new Burger();
     Plateau p = new Plateau(4,14);
     //Cuisinier cuisto = new Cuisinier(0, 4, 5);
     p.PlateauNiveau1();
     Cuisinier c = new Cuisinier(3, 0);

     Saucisse S = new Saucisse(1,0,0);
     Cornichon C = new Cornichon(1,2,0);
     Oeuf O  = new Oeuf(1,2,8);

     p.addMonstre(S);
     p.addMonstre(C);
     p.addMonstre(O);

     for(int i = 0; i<200; i++)
     {
       p.affiche(c, b);
       p.DeplacementCuisinier(c, b);
       O.DeplacementMonstre(p);
       S.DeplacementMonstre(p);
       C.DeplacementMonstre(p);
     }

  }

  char AFF_LIMITE = 'X';
  char AFF_SOL = '_';
  char AFF_ECHELLE = '#';
  char AFF_VIDE = ' ';


  String tiret = "------------------------------------------------------------------------------------";
  String tabu1 = "                ";

  private int NB_LIGNES;                                        // Nombre de ligne du plateau
  private int NB_COLONNES;                                      // Nombre de colonne du plateau
  private char mapStatic[][];                                   // Le mapStatic du jeux representer par un tableau a 2 dimension
  private String mapDynam[][];                                    // Genre pour les thread quoi

  // Fonctions pour accéder aux attributs ou les retourner

  public void setNbLigne(int n) { this.NB_LIGNES = n; }

  public int getNbLigne() { return this.NB_LIGNES; }

  public void setNbCol(int n) { this.NB_COLONNES = n; }

  public int getNbCol() { return this.NB_COLONNES; }

  public int getID(int lig, int col) { return (this.NB_COLONNES*lig)+col; }

  public char[][] getMapStatic() { return this.mapStatic; }

  public String[][] getMapDynamic() { return this.mapDynam; }

  public char getCharat(char[][] c, int lig, int col) { return c[lig][col];}

  public String getString(String[][] c, int lig, int col) { return c[lig][col];}

  public void modifieCaseStatic(int lig, int col, char c) { mapStatic[lig][col] = c; }

  public void modifieCaseDynamique(int lig, int col, String c){ mapDynam[lig][col] = c; }

  // Autre fonctions hors attributs

  public boolean valide(char c, int ligCuisto, int colCuisto)     // Fonction valide vérifie si le déplacement (cuisto) désiré est réalisable
  {
    boolean verif = false;
    switch(c)
    {
      case 'z' :
      {
        if(mapStatic[ligCuisto][colCuisto] == AFF_ECHELLE && mapStatic[ligCuisto-1][colCuisto] == AFF_ECHELLE ) { verif = true;}
        break;
      }

      case 'q' :
      {
        if( mapStatic[ligCuisto][colCuisto-1] != AFF_VIDE) { verif = true;}
        break;
      }

      case 's' :
      {
        if(mapStatic[ligCuisto][colCuisto] == AFF_ECHELLE && mapStatic[ligCuisto+1][colCuisto] == AFF_ECHELLE ) { verif = true;}
        break;
      }

      case 'd' :
      {
        if( mapStatic[ligCuisto][colCuisto+1] != AFF_VIDE) { verif = true;}
        break;
      }
    }
    return verif;
  }

  public void DeplacementCuisinier(Cuisinier cuisto, Burger b)    // Fonction qui permet de déplacer le cuisinier de la partie
  {
    int t = 0;
    Scanner sc = new Scanner(System.in);      // Create a Scanner object
    System.out.println("Deplacer le cuisto :");

    boolean verif = false;

    while( verif == false)
    {
      char touche = sc.next().charAt(0);  // Read user input
      modifieCaseDynamique(cuisto.getPosLigne(), cuisto.getPosColonne(), " ");
      if(touche == 'z' && this.valide(touche, cuisto.getPosLigne(), cuisto.getPosColonne()))
      {
        cuisto.setPosLigne(cuisto.getPosLigne()-1);
        verif = true;
      }

      else if(touche == 'q' && this.valide(touche, cuisto.getPosLigne(), cuisto.getPosColonne()))
      {
        // this.tomber(b, touche, cuisto.getPosLigne(), cuisto.getPosColonne());
        cuisto.setPosColonne(cuisto.getPosColonne()-1);
        verif = true;
      }
      else if(touche == 'd'  && this.valide(touche, cuisto.getPosLigne(), cuisto.getPosColonne()))
      {
        cuisto.setPosColonne(cuisto.getPosColonne()+1);
        verif = true;
      }

      else if(touche == 's'  && this.valide(touche, cuisto.getPosLigne(), cuisto.getPosColonne()))
      {
        cuisto.setPosLigne(cuisto.getPosLigne()+1);
        verif = true;
      }

      else
        System.out.println("Mauvaise touche, pour rappel Z pour monter, S pour descendre, Q pour aller a gauche, et S pour descendre !");
    }

      modifieCaseDynamique(cuisto.getPosLigne(), cuisto.getPosColonne(), cuisto.getStringCuisinier());
}

  // public void tomber(Burger b, char c, int lig, int col)
  //{
  //    if( c == 'q' && mapDynam[lig][col-1] == 'F')
  //    {
  //      modifieCaseDynamique(lig, col-1, AFF_VIDE);
  //      if(lig<=getNbLigne())
  //        modifieCaseDynamique(lig+1, col-1, 'F');
  //    }
  // }

  public void affiche(Cuisinier c, Burger b)                     // Affiche l'état du jeu à chaque déplacement du joueur
  {
      // Pour aérer à chaque actualisation de la partie
      System.out.println(tiret+"\n"+tiret+"\n");
      for(int i=0; i<15; i++)
      {
        if(i == 9)
        {
          System.out.print("                                      ");
          c.Affichage();
        }
        else System.out.println("");
      }

      for(int i=-1; i<=this.getNbLigne(); i++)                       // Affichage du terrain dans lequel les monstres et le cuisinier évolue
      {
        for(int j=-1; j<=this.getNbCol(); j++)
        {
          if(j == -1)
            System.out.print(tabu1);
          if (i == -1 || j == -1 || i == this.getNbLigne() || j ==  this.getNbCol()) // Permet l'affichage des bords du tableau
            System.out.print(AFF_LIMITE);
          else if( mapDynam[i][j] == "S" || mapDynam[i][j] == "O" || mapDynam[i][j] == "C" || mapDynam[i][j] == "J" || mapDynam[i][j] == "P" || mapDynam[i][j] == "K" || mapDynam[i][j] == "F" )
            System.out.print(mapDynam[i][j]);
          else
            {
              this.mapDynam[i][j] = " ";
              System.out.print(mapStatic[i][j]);
            }
        }
        System.out.println("\n");
      }

      for(int i=-1; i<=4;i++)                                   // Affichage des burgers qui se complèe au fur et à mesure
      {
        for(int j=-1; j<=this.getNbCol(); j++)
        {
          int col = 7;
          if(j == -1)
            System.out.print(tabu1);
          if (i == -1 || j == -1 || i == 4 || j == this.getNbCol()) // Permet l'affichage des bords du tableau
            System.out.print(AFF_LIMITE);
          else if ((j == 7 || j == 8 || j == 9) && (b.getTabBurger(i, j-col) == 'P' || b.getTabBurger(i, j-col) == 'S' || b.getTabBurger(i, j-col) == 'K'))
                System.out.print(b.getTabBurger(i, j-col));
          else
              System.out.print(AFF_VIDE);
        }
        System.out.print("\n");
      }

      for(int i=0; i<12; i++) { System.out.println(""); }
      System.out.println(tiret+"\n"+tiret+"\n");
  }

  public Plateau(int lig, int col)                               // Constructeur(s) de la classe spécifique
  {
    this.setNbLigne(lig+2);
    this.setNbCol(col+2);
    this.mapStatic = new char[lig+2][col+2];
    this.mapDynam = new String[lig+2][col+2];
  }

  public Plateau()                                               // Constructeur par défaut du Plateau
  {
    this.NB_LIGNES = 8;
    this.NB_COLONNES = 50;
    this.mapStatic = new char[getNbLigne()+2][getNbCol()+2];
    this.mapDynam = new String[getNbLigne()+2][getNbCol()+2];
  }

  public void Complete()                                         // Complete un plateau du constructeur par défaut
  {
    for(int i=0; i<=getNbLigne()+1; i++)
    {
      for(int j=0; j<=getNbCol()+1; j++)
      {
        if(i == 0 || j == 0 || i == getNbLigne()+1 || j == getNbCol()+1) modifieCaseStatic(i, j, AFF_VIDE);
        else modifieCaseStatic(i, j, AFF_SOL);
      }
    }

    modifieCaseStatic(1, 13, AFF_ECHELLE);
    modifieCaseStatic(1, 13, AFF_ECHELLE);
    modifieCaseStatic(1, 34, AFF_ECHELLE);
    modifieCaseStatic(1, 34, AFF_ECHELLE);
    modifieCaseStatic(1, 20, AFF_ECHELLE);
    modifieCaseStatic(2, 20, AFF_ECHELLE);
    modifieCaseStatic(1, 48, AFF_ECHELLE);
    modifieCaseStatic(2, 48, AFF_ECHELLE);
    modifieCaseStatic(2, 40, AFF_ECHELLE);
    modifieCaseStatic(3, 40, AFF_ECHELLE);
    modifieCaseStatic(2, 13, AFF_ECHELLE);
    modifieCaseStatic(3, 13, AFF_ECHELLE);
    modifieCaseStatic(3, 34, AFF_ECHELLE);
    modifieCaseStatic(4, 34, AFF_ECHELLE);
    modifieCaseStatic(4, 20, AFF_ECHELLE);
    modifieCaseStatic(5, 20, AFF_ECHELLE);

    modifieCaseStatic(1, 5, AFF_ECHELLE);
    modifieCaseStatic(2, 5, AFF_ECHELLE);
    modifieCaseStatic(3, 5, AFF_ECHELLE);
    modifieCaseStatic(4, 5, AFF_ECHELLE);
    modifieCaseStatic(4, 38, AFF_ECHELLE);
    modifieCaseStatic(5, 38, AFF_ECHELLE);

    // modifieCaseDynamique(3, 7, 'P');
    // modifieCaseDynamique(3, 8, 'P');
    // modifieCaseDynamique(3, 9, 'P');

    // modifieCaseDynamique(4, 7, 'F');
    // modifieCaseDynamique(4, 8, 'F');
    // modifieCaseDynamique(4, 9, 'F');

    modifieCaseDynamique(5, 7, "K");
    modifieCaseDynamique(5, 8, "K");
    modifieCaseDynamique(5, 9, "K");
  }

  public void PlateauNiveau1()                                   // Modifie les cases d'un Plateau(4, 14) (Spécifique)
  {
    modifieCaseStatic(0,0,AFF_SOL);
    modifieCaseStatic(0,1,AFF_SOL);
    modifieCaseStatic(0,2,AFF_SOL);
    modifieCaseStatic(0,3,AFF_SOL);
    modifieCaseStatic(0,4,AFF_ECHELLE);
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
    modifieCaseStatic(2,4,AFF_ECHELLE);
    modifieCaseStatic(2,5,AFF_SOL);
    modifieCaseStatic(2,6,AFF_SOL);
    modifieCaseStatic(2,7,AFF_SOL);
    modifieCaseStatic(2,8,AFF_SOL);
    modifieCaseStatic(2,9,AFF_SOL);
    modifieCaseStatic(2,10,AFF_SOL);
    modifieCaseStatic(2,11,AFF_SOL);
    modifieCaseStatic(2,12,AFF_SOL);
    modifieCaseStatic(2,13,AFF_SOL);

    modifieCaseStatic(3,0,AFF_ECHELLE);
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

  public void addMonstre(Monstre m)                              // Ajoute un monstre m sur le plateau pour qu'il puisse être visualiser
  {
    int ligne = m.getPosLigne();
    int col = m.getPosColonne();
    String c = m.getStringMonstre();
    modifieCaseDynamique(ligne, col, c);
  }

  public void addCuisinier(Cuisinier c)                          // Ajoute le cuisinier au plateau pour qu'il puisse être visualiser
  {
    int ligne = c.getPosLigne();
    int col = c.getPosColonne();
    String cuis = c.getStringCuisinier();
    modifieCaseDynamique(ligne, col, cuis);
  }
 }
