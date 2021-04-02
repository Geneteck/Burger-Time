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
     Burger b1 = new Burger(1,5);
     Burger b2 = new Burger(1,1);
     Burger b3 = new Burger(2,10);

     Plateau p = new Plateau(5,14);

     p.setTabBurger(0, b1);
     p.setTabBurger(1, b2);
     p.setTabBurger(2, b3);

     //Cuisinier cuisto = new Cuisinier(0, 4, 5);
     p.PlateauNiveau1();
     Cuisinier c = new Cuisinier(3, 0);

     Monstre m = new Monstre(8);
     m.SpawnRandom(p);

     p.addBurger(b1);
     p.addBurger(b2);
     p.addBurger(b3);

     for(int i = 0; i<200; i++)
     {
       p.affiche(c);
       p.DeplacementCuisinier(c);
       //O.DeplacementMonstre(p);
       //S.DeplacementMonstre(p);
       //C.DeplacementMonstre(p);
       m.DeplacementMonstre(p);
     }

  }

  char AFF_LIMITE = 'X';
  char AFF_SOL = '_';
  char AFF_ECHELLE = '#';
  char AFF_VIDE = ' ';


  String tiret = "-------------------------------------------------------------------------------------------------------------------------------------------";
  String tabu1 = "                ";

  private int NB_LIGNES;                                        // Nombre de ligne du plateau
  private int NB_COLONNES;                                      // Nombre de colonne du plateau
  private char mapStatic[][];                                   // Le mapStatic du jeux representer par un tableau a 2 dimension
  private String mapDynam[][];                                  // Map dynamique pour le déplacement des joueurs/monstre

  private char mapDynamBurger[][];                              // Map dynamique pour différencier burger et monstre
  private char petitTabBurger[][];                              // Tableau statique que l'on affiche/visualise (= sous tableau)
  private Burger tabBurger[];                                   // Tableau ou chaque entité de burger est concaténer (utiliser dans l'affichage statique du sous tableau petitTabBurger)

  // Fonctions pour accéder aux attributs ou les retourner

  public void setNbLigne(int n) { this.NB_LIGNES = n; }

  public int getNbLigne() { return this.NB_LIGNES; }

  public void setNbCol(int n) { this.NB_COLONNES = n; }

  public int getNbCol() { return this.NB_COLONNES; }

  public int getID(int lig, int col) { return (this.NB_COLONNES*lig)+col; }

  public char[][] getMapStatic() { return this.mapStatic; }

  public char[][] getDynamBurgeur() { return this.mapDynamBurger; }

  public String[][] getMapDynamic() { return this.mapDynam; }

  public void setTabBurger(int x, Burger b) { this.tabBurger[x] = b; }

  public Burger getTabBurger(int x) { return this.tabBurger[x]; }

  public char getCharat(char[][] c, int lig, int col) { return c[lig][col];}

  public String getString(String[][] c, int lig, int col) { return c[lig][col];}

  public void modifieCaseStatic(int lig, int col, char c) { mapStatic[lig][col] = c; }

  public void modifieCaseDynamique(int lig, int col, String c){ mapDynam[lig][col] = c; }

  public void modifieCaseDynamiqueBurger(int lig, int col, char c){ mapDynamBurger[lig][col] = c; }

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

  public void DeplacementCuisinier(Cuisinier cuisto)              // Fonction qui permet de déplacer le cuisinier de la partie
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
        this.tomber(touche, cuisto.getPosLigne(), cuisto.getPosColonne());
        cuisto.setPosColonne(cuisto.getPosColonne()-1);
        verif = true;
      }
      else if(touche == 'd'  && this.valide(touche, cuisto.getPosLigne(), cuisto.getPosColonne()))
      {
        this.tomber(touche, cuisto.getPosLigne(), cuisto.getPosColonne());
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

  public void tomber(char c, int lig, int col)                    // le but est de faire tomber l'élément de burger fromage avec les déplacements à gauche
  {
      int ligne = lig;                                                                                  // On stocke le numéro de la ligne en question dans une variable
      if(mapDynamBurger[ligne][col-1] == '~')                                                             // Ici on vérifie, si la
      {
          if( c == 'q')                                                                                // Ici on vérifie quel déplacement à été effectuer (q ou d), sur cette ligne c'est q pour la gauche
          {
            modifieCaseDynamiqueBurger(ligne, col-1, AFF_VIDE);                                           // Si c'est le cas on enlève le caractère '=' de sa position actuel
            ligne++;                                                                                    // On incrémente la variable ligne pour passer à la ligne du dessous
            if(lig<this.getNbLigne())                              // On vérifie quand faisant "tomber"l'élément fromage, la ligne existe
            {                                                                                           // Puis on vérifie que néanmoins cette ligne du dessous ne possède pas de vide (en colonne) pour les autres éléments fromages qui tomberont
              while(this.getCharat(mapStatic, ligne, col-1) == AFF_VIDE ) { ligne++; }                   // Si il existe du vide on passe à la prochaine ligne, ligne++
              modifieCaseDynamiqueBurger(ligne, col-1, '~');
            }

          }
          else if( c == 'd')                                       // Ici on vérifie quel déplacement à été effectuer (z, q, s, d), sur cette ligne c'est q pour la gauche
          {
            modifieCaseDynamiqueBurger(ligne, col+1, AFF_VIDE);                                         // Si c'est le cas on enlève le caractère '=' de sa position actuel
            ligne++;                                                                                    // On incrémente la variable ligne pour passer à la ligne du dessous
            if(lig<this.getNbLigne())                                                                   // On vérifie quand faisant "tomber"l'élément fromage, la ligne existe
            {                                                                                           // Puis on vérifie que néanmoins cette ligne du dessous ne possède pas de vide (en colonne) pour les autres éléments fromages qui tomberont
              while(this.getCharat(mapStatic, ligne, col+1) == AFF_VIDE ) { ligne++; } // Si il existe du vide on passe à la prochaine ligne, ligne++
              modifieCaseDynamiqueBurger(ligne, col+1, '~');
            }
          }
      }
      else if(mapDynamBurger[ligne][col-1] == '=' )                                                     // La case à la gauche du cuisinier est un élement fromage
      {
          if( c == 'q' && this.verifSteack(c, ligne, col-1))                                            // Ici on vérifie quel déplacement à été effectuer (q ou d), sur cette ligne c'est q pour la gauche
          {
            modifieCaseDynamiqueBurger(ligne, col-1, AFF_VIDE);                                         // Si c'est le cas on enlève le caractère '=' de sa position actuel
            ligne++;                                                                                    // On incrémente la variable ligne pour passer à la ligne du dessous
            if(lig<this.getNbLigne() && mapDynamBurger[ligne][col-1] == '=')                            // On vérifie quand faisant "tomber"l'élément fromage, la ligne existe
            {                                                                                           // Puis on vérifie que néanmoins cette ligne du dessous ne possède pas de vide (en colonne) pour les autres éléments fromages qui tomberont
              while(this.getCharat(mapStatic, ligne, col-1) == AFF_VIDE ) { ligne++; }                  // Si il existe du vide on passe à la prochaine ligne, ligne++
              modifieCaseDynamiqueBurger(ligne, col-1, '=');
            }

          }
          else if( c == 'd' && this.verifSteack(c, ligne, col+1))                                       // Ici on vérifie quel déplacement à été effectuer (z, q, s, d), sur cette ligne c'est q pour la gauche
          {
            modifieCaseDynamiqueBurger(ligne, col+1, AFF_VIDE);                                         // Si c'est le cas on enlève le caractère '=' de sa position actuel
            ligne++;                                                                                    // On incrémente la variable ligne pour passer à la ligne du dessous
            if(lig<this.getNbLigne())                                                                   // On vérifie quand faisant "tomber"l'élément fromage, la ligne existe
            {                                                                                           // Puis on vérifie que néanmoins cette ligne du dessous ne possède pas de vide (en colonne) pour les autres éléments fromages qui tomberont
              while(this.getCharat(mapStatic, ligne, col+1) == AFF_VIDE ) { ligne++; }                  // Si il existe du vide on passe à la prochaine ligne, ligne++
              modifieCaseDynamiqueBurger(ligne, col+1, '=');
            }
          }
      }
   }

   public boolean verifSteack(char touche, int lig, int col)                                      // touche pour le déplacement efectuer, lig et col pour l'élément fromage dont on cherche l'existence (possible) de steack
   {
     boolean v = true;
     int ligne = lig;                                                                              // On stocke la ligne lig dans une nouvelle variable ligne
     ligne ++;                                                                                     // ligne prend pour numéro la ligne en-dessous de la ligne du fromage acuel dont on effectue la vérification
     if( touche == 'q')                                                                            // Si on se déplace sur la gauche
     {
       if( mapStatic[ligne][col] == AFF_SOL)                                                      // Alors on regarde si en-dessous du fromage on a du sol en static
       {
         if(mapDynamBurger[ligne][col] == '~' || mapDynamBurger[ligne][col-1] == '~' || mapDynamBurger[ligne][col-2] == '~' )      // Si c'est le cas on vérifie alors si en cette ligne il existe des fromages sur la gauche
         {
           v = false;
         }
       }
       else
       {
         while( mapStatic[ligne][col] == AFF_VIDE) { ligne++; }
         if(mapDynamBurger[ligne][col] == '~' || mapDynamBurger[ligne][col-1] == '~' || mapDynamBurger[ligne][col-2] == '~' )      // Si c'est le cas on vérifie alors si en cette ligne il existe des fromages sur la gauche
         {
           v = false;
         }
       }
     }
     else if (touche == 'd')                                                                      // Si on se déplace sur la droite
     {
       if( mapStatic[ligne][col] == AFF_SOL)                                                      // On regarde si en-dessous du fromage on a du sol en static
       {
         if(mapDynamBurger[ligne][col] == '~' || mapDynamBurger[ligne][col+1] == '~' || mapDynamBurger[ligne][col+2] == '~' )      // Si c'est le cas on vérifie alors si en cette ligne il existe des fromages sur la droite
         {
           v = false;
         }
       }
       else
       {
         while( mapStatic[ligne][col] == AFF_VIDE) { ligne++; }
         if(mapDynamBurger[ligne][col] == '~' || mapDynamBurger[ligne][col+1] == '~' || mapDynamBurger[ligne][col+2] == '~' )      // Si c'est le cas on vérifie alors si en cette ligne il existe des fromages sur la gauche
         {
           v = false;
         }
       }
     }
     return v;
   }

  public void affiche(Cuisinier c)                                     // Affiche l'état du jeu à chaque déplacement du joueur
  {
      // Pour aérer à chaque actualisation de la partie
      System.out.println(tiret+"\n"+tiret+"\n");
      for(int i=0; i<15; i++)
      {
        if(i == 9)
        {
          System.out.print("                                                ");
          c.Affichage();
        }
        else System.out.println("");
      }

      for(int i=-1; i<=this.getNbLigne()+2; i++)                       // Affichage du terrain dans lequel les monstres et le cuisinier évolue
      {
        for(int j=-1; j<=this.getNbCol()+2; j++)
        {
          if(j == -1)
            System.out.print(tabu1);
          if (i == -1 || j == -1 || i == this.getNbLigne()+2 || j ==  this.getNbCol()+2) // Permet l'affichage des bords du tableau
            System.out.print(AFF_LIMITE);
          else if( mapDynam[i][j] == "S" || mapDynam[i][j] == "O" || mapDynam[i][j] == "C" || mapDynam[i][j] == "J")
            System.out.print(mapDynam[i][j]);
          else if (mapDynamBurger[i][j] == '*' || mapDynamBurger[i][j] == '~' || mapDynamBurger[i][j] == '=')
          {
            System.out.print(mapDynamBurger[i][j]);
            this.mapDynam[i][j] = " ";
          }
          else
            {
              this.mapDynam[i][j] = " ";
              System.out.print(mapStatic[i][j]);
            }
        }
        System.out.println("\n");
      }

      this.afficheBurger();

      for(int i=0; i<12; i++) { System.out.println(""); }
      System.out.println(tiret+"\n"+tiret+"\n");
  }

  public void afficheBurger()
  {
      for(int i=0; i<4; i++)                                                                            // Parcours des ligne du sous tableau (4 donc 0, 1, 2, 3, 4)
      {
          for(int j=0; j<this.getNbCol()+2; j++)
          {      // Parcours de l'ensemble des colonnes du sous tableau au même nombre que le grand tableau du dessus
                if (i== 3 && (j == this.getTabBurger(0).getColonne() || j == this.getTabBurger(1).getColonne() || j == this.getTabBurger(2).getColonne()))
                {
                  this.petitTabBurger[i][j] = '*';
                  this.petitTabBurger[i][j+1] = '*';
                  this.petitTabBurger[i][j+2] = '*';
                }
                else if (this.petitTabBurger[i][j] != '*')
                this.petitTabBurger[i][j] = AFF_VIDE;

          }
      }

        for(int i=-1; i<=4;i++)                                   // Affichage des burgers qui se complèe au fur et à mesure, Tableau du bas
        {
            for(int j=-1; j<=this.getNbCol()+2; j++)
            {
              if(j == -1)
                System.out.print(tabu1);
              if (i == -1 || j == -1 || i == 4 || j == this.getNbCol()+2) // Permet l'affichage des bords du tableau
                System.out.print(AFF_LIMITE);
              else
                System.out.print(this.petitTabBurger[i][j]);
          }
          System.out.print("\n");
      }

  }

  public Plateau(int lig, int col)                               // Constructeur(s) de la classe spécifique
  {
    this.setNbLigne(lig);
    this.setNbCol(col);
    this.mapStatic = new char[lig+2][col+2];
    this.mapDynam = new String[lig+2][col+2];
    this.mapDynamBurger = new char[lig+2][col+2];
    this.tabBurger = new Burger[3];
    this.petitTabBurger = new char[4+2][col+2];
  }

  public Plateau()                                               // Constructeur par défaut du Plateau
  {
    this.NB_LIGNES = 8;
    this.NB_COLONNES = 70;
    this.mapStatic = new char[getNbLigne()+2][getNbCol()+2];
    this.mapDynam = new String[getNbLigne()+2][getNbCol()+2];
    this.mapDynamBurger = new char[getNbLigne()+2][getNbCol()+2];
    this.tabBurger = new Burger[3];                              // 0, 1, 2 soit 3 burgers qui sont crées
    this.petitTabBurger = new char[4+2][getNbCol()+2];           // Deuxième tableau static simplement pour l'affichage du sous tableau de burger qui se complète au cours de la partie
  }

  public void Complete()                                         // Complete un plateau du constructeur par défaut
  {
    for(int i=0; i<=getNbLigne()+1; i++)
    {
      for(int j=0; j<=getNbCol()+1; j++)
      {
        if(i == 0 || j == 0 || i == getNbLigne()+1 || j == getNbCol()+1) modifieCaseStatic(i, j, AFF_VIDE);
        else if ( i == 1 && ( j > 20 && j < 50))
            modifieCaseStatic(i, j, AFF_VIDE);
        else if ( i == 3 && ( j > 52 && j <= 70))
            modifieCaseStatic(i, j, AFF_VIDE);
        else if ( i == 4 && ( j > 25 && j < 42))
            modifieCaseStatic(i, j, AFF_VIDE);
        else if ( i == 3 &&  j <10)
            modifieCaseStatic(i, j, AFF_VIDE);
        else if ( i == this.getNbLigne() && j <= 15)   //permet de faire le vide dans le coin inferieur droit du décor
            modifieCaseStatic(i, j, AFF_VIDE);
        else if ( i == this.getNbLigne()-1 && (j >= 17 && j <= 36))
            modifieCaseStatic(i, j, AFF_VIDE);
        else if ( i == this.getNbLigne()-1 && (j >= 60 && j <= 70))
            modifieCaseStatic(i, j, AFF_VIDE);
        else if ( i == this.getNbLigne()-2 && (j >= 10 && j <= 25))
            modifieCaseStatic(i, j, AFF_VIDE);
        else
            modifieCaseStatic(i, j, AFF_SOL);
      }
    }

    modifieCaseStatic(1, 15, AFF_ECHELLE);
    modifieCaseStatic(2, 15, AFF_ECHELLE);
    modifieCaseStatic(3, 15, AFF_ECHELLE);

    modifieCaseStatic(1, 50, AFF_ECHELLE);
    modifieCaseStatic(2, 50, AFF_ECHELLE);

    modifieCaseStatic(1, 70, AFF_ECHELLE);
    modifieCaseStatic(2, 70, AFF_ECHELLE);

    modifieCaseStatic(2, 60, AFF_ECHELLE);
    modifieCaseStatic(3, 60, AFF_ECHELLE);
    modifieCaseStatic(4, 60, AFF_ECHELLE);
    modifieCaseStatic(5, 60, AFF_ECHELLE);

    modifieCaseStatic(1, 3, AFF_ECHELLE);
    modifieCaseStatic(2, 3, AFF_ECHELLE);
    modifieCaseStatic(3, 3, AFF_ECHELLE);
    modifieCaseStatic(4, 3, AFF_ECHELLE);

    modifieCaseStatic(3, 35, AFF_ECHELLE);
    modifieCaseStatic(4, 35, AFF_ECHELLE);
    modifieCaseStatic(5, 35, AFF_ECHELLE);
    modifieCaseStatic(6, 35, AFF_ECHELLE);

    modifieCaseStatic(4, 25, AFF_ECHELLE);
    modifieCaseStatic(5, 25, AFF_ECHELLE);

    modifieCaseStatic(this.getNbLigne(), this.getNbCol(), AFF_ECHELLE);
    modifieCaseStatic(this.getNbLigne()-1, this.getNbCol(), AFF_ECHELLE);
    modifieCaseStatic(this.getNbLigne()-2, this.getNbCol(), AFF_ECHELLE);

    modifieCaseStatic(this.getNbLigne(), 16, AFF_ECHELLE);
    modifieCaseStatic(this.getNbLigne()-1, 16, AFF_ECHELLE);

    modifieCaseStatic(this.getNbLigne()-1, 9, AFF_ECHELLE);
    modifieCaseStatic(this.getNbLigne()-2, 9, AFF_ECHELLE);
    modifieCaseStatic(this.getNbLigne()-3, 9, AFF_ECHELLE);

    modifieCaseStatic(this.getNbLigne()-1, 50, AFF_ECHELLE);
    modifieCaseStatic(this.getNbLigne()-2, 50, AFF_ECHELLE);
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

  public void addBurger(Burger b)
  {
    int lig = b.getLigne();                                                // On récupère la ligne à partir de laquelle on veut commencer
    int col = b.getColonne();                                              // On récupère la colonne à partir de laquelle on veut commencer

    // Deux boucles pour placer sur ligne et colonne les éléments (caractères) des burgers
    for(int i = 0; i < 3; i++ )
    {
      for(int j = 0; j < 3; j++)
      {
          // On part du principe que la ligne et la colonne choisis de départ sont possibles
          // Tentons de les ajouter CORRECTEMENT
          if(i == 0)
          {
            while(mapStatic[lig][col] ==  AFF_VIDE ||  mapStatic[lig][col+1] == AFF_VIDE || mapStatic[lig][col+2] == AFF_VIDE) { lig++; }
            modifieCaseDynamiqueBurger(lig, col, b.AFF_PAINH); col++;
          }
          else if (i == 1)
          {
            while(mapStatic[lig][col] ==  AFF_VIDE ||  mapStatic[lig][col+1] == AFF_VIDE || mapStatic[lig][col+2] == AFF_VIDE) { lig++; }
            modifieCaseDynamiqueBurger(lig, col, b.AFF_FROMAGE); col++;
          }
          else
          {
            while(mapStatic[lig][col] ==  AFF_VIDE ||  mapStatic[lig][col+1] == AFF_VIDE || mapStatic[lig][col+2] == AFF_VIDE) { lig++; }
            modifieCaseDynamiqueBurger(lig, col, b.AFF_STEACK); col++;
          }
      }
      col = b.getColonne();
      lig++;
    }
  }


    public  boolean verifPosBurgerLigne (int lig, int col)               // Fonction pour vérifier si la position du burger est possible
    {
        return true;
    }

 }
