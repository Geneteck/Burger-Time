/*
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc
*/


import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Plateau
{
  // Déclaration des attributs

  private int NB_LIGNES;                                                       // Nombre de ligne du plateau
  private int NB_COLONNES;                                                     // Nombre de colonne du plateau
  private char mapStatic[][];                                                  // Tableau static dans lequel est diposé le décor
  private char mapDynam[][];                                                   // Map dynamique pour le déplacement des joueurs/monstre

  private char mapDynamBurger[][];                                             // Map dynamique pour différencier burger et monstre
  private char petitTabBurger[][];                                             // Tableau statique que l'on affiche/visualise (= sous tableau)
  private Burger tabBurger[];                                                  // Tableau ou chaque entité de burger est concaténer (utiliser dans l'affichage statique du sous tableau petitTabBurger)

  // Permet de réaliser les différents affichages, et de rendre le code plus lisibles

  char AFF_LIMITE = 'X';
  char AFF_SOL = '_';
  char AFF_ECHELLE = '#';
  char AFF_VIDE = ' ';
  String tiret = "-------------------------------------------------------------------------------------------------------------------------------------------";
  String tabu1 = "                ";

  // Méthodes d'accès aux différents attributs

  public void setNbLigne(int n) { this.NB_LIGNES = n; }
  public int getNbLigne() { return this.NB_LIGNES; }
  public void setNbCol(int n) { this.NB_COLONNES = n; }
  public int getNbCol() { return this.NB_COLONNES; }
  public int getID(int lig, int col) { return (this.NB_COLONNES*lig)+col; }
  public char[][] getMapStatic() { return this.mapStatic; }
  public char[][] getDynamBurgeur() { return this.mapDynamBurger; }
  public char[][] getMapDynamic() { return this.mapDynam; }
  public void setTabBurger(int x, Burger b) { this.tabBurger[x] = b; }

  // Méthodes intermédiaires de la classe Plateau

  public Burger getTabBurger(int x) { return this.tabBurger[x]; }
  public char getCharat(char[][] c, int lig, int col) { return c[lig][col];}
  public void modifieCaseStatic(int lig, int col, char c) { mapStatic[lig][col] = c; }
  public void modifieCaseDynamique(int lig, int col, char c){ mapDynam[lig][col] = c; }
  public void modifieCaseDynamiqueBurger(int lig, int col, char c){ mapDynamBurger[lig][col] = c; }

  // Méthodes principales de la classe Plateau

  // Permet de visualiser au fur et à mesure d'une partie l'état du jeu
  public void affiche(Cuisinier c)
  {
      System.out.println(tiret+"\n"+tiret+"\n");
      this.calcScore(c);
      for(int i=0; i<15; i++)
      {
        if(i == 9)
        {
          System.out.print("                                                ");
          c.afficheScorePv();
        }
        else System.out.println("");
      }

      for(int i=-1; i<=this.getNbLigne()+2; i++)
      {
        for(int j=-1; j<=this.getNbCol()+2; j++)
        {
          if(j == -1)
            System.out.print(tabu1);

          if (i == -1 || j == -1 || i == this.getNbLigne()+2 || j ==  this.getNbCol()+2)
            System.out.print(AFF_LIMITE);

          else if( mapDynam[i][j] == 'J'|| mapDynam[i][j] == 'S' || mapDynam[i][j] == 'O' || mapDynam[i][j] == 'C' )
            System.out.print(mapDynam[i][j]);

          else if (mapDynamBurger[i][j] == '*' || mapDynamBurger[i][j] == '~' || mapDynamBurger[i][j] == '=')
            System.out.print(mapDynamBurger[i][j]);

          else
              System.out.print(mapStatic[i][j]);
        }
        System.out.println("\n");
      }

      this.afficheBurger();

      for(int i=0; i<12; i++) { System.out.println(""); }
      System.out.println(tiret+"\n"+tiret+"\n");
      System.out.println("Deplacer le cuisinier :");
  }

  // Pemert l'affichage du tableau dans lequel les éléments de burger tombe
  public void afficheBurger()
  {
      for(int i=0; i<4; i++)
      {
          for(int j=0; j<this.getNbCol()+2; j++)
          {      // Parcours de l'ensemble des colonnes du sous tableau ayant le même nombre que le grand tableau du dessus
                if (i == 3 && (j == this.getTabBurger(0).getColonne() || j == this.getTabBurger(1).getColonne() || j == this.getTabBurger(2).getColonne()))
                {
                  this.petitTabBurger[i][j] = '*';
                  this.petitTabBurger[i][j+1] = '*';
                  this.petitTabBurger[i][j+2] = '*';
                }
                else if (!(this.petitTabBurger[i][j] == '*' || this.petitTabBurger[i][j] == '~' || this.petitTabBurger[i][j] == '='))
                {
                  this.petitTabBurger[i][j] = AFF_VIDE;
                }
          }
      }

      // Affichage des burgers qui se complèe au fur et à mesure, Tableau du bas
      for(int i=-1; i<=4;i++)
      {
          for(int j=-1; j<=this.getNbCol()+2; j++)
          {
            if(j == -1)
              System.out.print(tabu1);
            if (i == -1 || j == -1 || i == 4 || j == this.getNbCol()+2)
              System.out.print(AFF_LIMITE);
            else
              System.out.print(this.petitTabBurger[i][j]);
        }
        System.out.print("\n");
      }
  }

  // Fonction valide vérifie si le déplacement (cuisto) désiré est réalisable
  public boolean valide(char c, int ligCuisto, int colCuisto)
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

  // Permet de faire tomber (si cela est possibe) les éléments de burger sur lequel un cuisinier se déplace
  public void tomber(char c, int lig, int col)
  {
      int ligne = lig;
      if(c == 'q')
      {
          if( mapDynamBurger[ligne][col-1] == '~' )
          {
            modifieCaseDynamiqueBurger(ligne, col-1, AFF_VIDE);
            ligne++;
            if(lig<this.getNbLigne())
            {
              while(this.getCharat(mapStatic, ligne, col-1) == AFF_VIDE ) { ligne++; }
              modifieCaseDynamiqueBurger(ligne, col-1, '~');
            }
            else  this.petitTabBurger[2][col-1] = '~';                                                  // Affichage de l'élement dans le sous tableau de burger
          }
          else if( mapDynamBurger[ligne][col-1] == '=' && this.verifSteack(c, ligne, col-1))
          {
            modifieCaseDynamiqueBurger(ligne, col-1, AFF_VIDE);
            ligne++;
            if(lig<this.getNbLigne())
            {
              while(this.getCharat(mapStatic, ligne, col-1) == AFF_VIDE ) { ligne++; }
              modifieCaseDynamiqueBurger(ligne, col-1, '=');
            }
            else  this.petitTabBurger[1][col-1] = '=';
          }
          else if( mapDynamBurger[ligne][col-1] == '*' && this.verifSteack(c, ligne, col-1) && this.verifFromage(c, ligne, col-1))
          {
            modifieCaseDynamiqueBurger(ligne, col-1, AFF_VIDE);
            ligne++;
            if(lig<this.getNbLigne())
            {
              while(this.getCharat(mapStatic, ligne, col-1) == AFF_VIDE ) { ligne++; }
              modifieCaseDynamiqueBurger(ligne, col-1, '*');
            }
            else this.petitTabBurger[0][col-1] = '*';
          }
      }
      else if( c == 'd')
      {
          if( mapDynamBurger[ligne][col+1] == '~')
          {
            modifieCaseDynamiqueBurger(ligne, col+1, AFF_VIDE);
            ligne++;
            if(lig<this.getNbLigne())
            {
              while(this.getCharat(mapStatic, ligne, col+1) == AFF_VIDE ) { ligne++; }
              modifieCaseDynamiqueBurger(ligne, col+1, '~');
            }
            else  this.petitTabBurger[2][col+1] = '~';
          }
          else if( mapDynamBurger[ligne][col+1] == '=' && this.verifSteack(c, ligne, col+1))
          {
            modifieCaseDynamiqueBurger(ligne, col+1, AFF_VIDE);
            ligne++;
            if(lig<this.getNbLigne())
            {
              while(this.getCharat(mapStatic, ligne, col+1) == AFF_VIDE ) { ligne++; }
              modifieCaseDynamiqueBurger(ligne, col+1, '=');
            }
            else  this.petitTabBurger[1][col+1] = '=';
          }
          else if( mapDynamBurger[ligne][col+1] == '*' && this.verifSteack(c, ligne, col+1) && this.verifFromage(c, ligne, col+1))
          {
            modifieCaseDynamiqueBurger(ligne, col+1, AFF_VIDE);
            ligne++;
            if(lig<this.getNbLigne())
            {
              while(this.getCharat(mapStatic, ligne, col+1) == AFF_VIDE ) { ligne++; }
              modifieCaseDynamiqueBurger(ligne, col+1, '*');
            }
            else this.petitTabBurger[0][col+1] = '*';
          }
      }
   }

  // Permet de vérifier si il existe encore des steacks sur la ligne du dessous, lorsque le déplacement s'effectue sur les fromages d'un burger
  public boolean verifSteack(char touche, int lig, int col)
     boolean v = true;
     int ligne = lig;
     ligne ++;
     if( touche == 'q')
     {
       if( mapStatic[ligne][col] == AFF_SOL)
       {
         if(mapDynamBurger[ligne][col] == '~' || mapDynamBurger[ligne][col-1] == '~')  { v = false; }
       }
       else
       {
         if(lig<this.getNbLigne())
         {
           while( mapStatic[ligne][col] == AFF_VIDE) { ligne++; }
           if(mapDynamBurger[ligne][col] == '~' || mapDynamBurger[ligne][col-1] == '~') { v = false; }
         }
         else v = true;
       }
     }
     else if (touche == 'd')
     {
       if( mapStatic[ligne][col] == AFF_SOL)
       {
         if(mapDynamBurger[ligne][col] == '~' || mapDynamBurger[ligne][col+1] == '~')  { v = false; }
       }
       else
       {
         if(lig<this.getNbLigne())
         {
           while( mapStatic[ligne][col] == AFF_VIDE) { ligne++; }
           if(mapDynamBurger[ligne][col] == '~' || mapDynamBurger[ligne][col+1] == '~') { v = false; }
         }
         else v = true;
       }
     }
     return v;
   }

  // Permet de vérifier si il existe encore des fromages sur la ligne du dessous, lorsque le déplacement s'effectue sur les pains supérieur d'un burger
  public boolean verifFromage(char touche, int lig, int col)                                      // touche pour le déplacement efectuer, lig et col pour l'élément fromage dont on cherche l'existence (possible) de steack
  {
     boolean v = true;
     int ligne = lig;                                                                              // On stocke la ligne lig dans une nouvelle variable ligne
     ligne ++;                                                                                     // ligne prend pour numéro la ligne en-dessous de la ligne du fromage acuel dont on effectue la vérification
     if( touche == 'q')                                                                            // Si on se déplace sur la gauche
     {
       if( mapStatic[ligne][col] == AFF_SOL)                                                      // Alors on regarde si en-dessous du fromage on a du sol en static
       {
         if(mapDynamBurger[ligne][col] == '=' || mapDynamBurger[ligne][col-1] == '=')  { v = false; }            // Si c'est le cas on vérifie alors si en cette ligne il existe des fromages sur la gauche
       }
       else
       {
         if(lig<this.getNbLigne())
         {
           while( mapStatic[ligne][col] == AFF_VIDE) { ligne++; }
           if(mapDynamBurger[ligne][col] == '=' || mapDynamBurger[ligne][col-1] == '=') { v = false; }       // Si c'est le cas on vérifie alors si en cette ligne il existe des fromages sur la gauche
         }
         else v = true;
       }
     }
     else if (touche == 'd')                                                                      // Si on se déplace sur la droite
     {
       if( mapStatic[ligne][col] == AFF_SOL)                                                      // On regarde si en-dessous du fromage on a du sol en static
       {
         if(mapDynamBurger[ligne][col] == '=' || mapDynamBurger[ligne][col+1] == '=')  { v = false; }    // Si c'est le cas on vérifie alors si en cette ligne il existe des fromages sur la droite
       }
       else
       {
         if(lig<this.getNbLigne())
         {
           while( mapStatic[ligne][col] == AFF_VIDE) { ligne++; }
           if(mapDynamBurger[ligne][col] == '=' || mapDynamBurger[ligne][col+1] == '=') { v = false; }     // Si c'est le cas on vérifie alors si en cette ligne il existe des fromages sur la gauche
         }
         else v = true;
       }
     }
     return v;
   }




  public void calcScore(Cuisinier c)
  {
    int x = 0;
    int point = 0;
    for(int z = 0; z<3; z++)
    {
      Burger b = this.getTabBurger(z);
      for(int i=0; i<4; i++)
      {
          for(int j=0; j<this.getNbCol()+2; j++)
          {
              if (j == this.getTabBurger(z).getColonne())
              {
                if( i == 0 && b.pain(this.petitTabBurger[i][j], this.petitTabBurger[i][j+1], this.petitTabBurger[i][j+2])) { x++; point = point + 70;}
                if( i == 1 && b.fromage(this.petitTabBurger[i][j], this.petitTabBurger[i][j+1], this.petitTabBurger[i][j+2])) { point = point + 50; }
                if( i == 2 && b.steack(this.petitTabBurger[i][j], this.petitTabBurger[i][j+1], this.petitTabBurger[i][j+2])) {  point = point + 30; }
                if( x == 1 || x == 2 || x == 3) { point = point*2; }
              }
          }
          c.setScore(point);
      }
    }
  }


  public Plateau(int lig, int col)                               // Constructeur(s) de la classe spécifique
  {
    this.setNbLigne(lig);
    this.setNbCol(col);
    this.mapStatic = new char[lig+2][col+2];
    this.mapDynam = new char[lig+2][col+2];
    this.mapDynamBurger = new char[lig+2][col+2];
    this.tabBurger = new Burger[3];
    this.petitTabBurger = new char[4+2][col+2];
  }

  public Plateau()                                               // Constructeur par défaut du Plateau
  {
    this.NB_LIGNES = 8;
    this.NB_COLONNES = 70;
    this.mapStatic = new char[getNbLigne()+2][getNbCol()+2];
    this.mapDynam = new char[getNbLigne()+2][getNbCol()+2];
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
        modifieCaseDynamiqueBurger(i, j, AFF_VIDE); //map du burger mis par défaut a vide

        if(i == 0 || j == 0 || i == getNbLigne()+1 || j == getNbCol()+1) modifieCaseStatic(i, j, AFF_VIDE);
        else if ( i == 1 && ( j > 20 && j < 50))
        {
            modifieCaseStatic(i, j, AFF_VIDE);
            modifieCaseDynamique(i,j, '!');
        }
        else if ( i == 3 && ( j > 52 && j <= 70))
        {
            modifieCaseStatic(i, j, AFF_VIDE);
            modifieCaseDynamique(i,j,'!');
        }
        else if ( i == 4 && ( j > 25 && j < 42))
        {
            modifieCaseStatic(i, j, AFF_VIDE);
            modifieCaseDynamique(i,j, '!');
        }
        else if ( i == 3 &&  j <10)
        {
            modifieCaseStatic(i, j, AFF_VIDE);
            modifieCaseDynamique(i,j, '!');
        }
        else if ( i == this.getNbLigne() && j <= 15)   //permet de faire le vide dans le coin inferieur droit du décor
        {
            modifieCaseStatic(i, j, AFF_VIDE);
            modifieCaseDynamique(i,j, '!');
        }
        else if ( i == this.getNbLigne()-1 && (j >= 17 && j <= 36))
        {
            modifieCaseStatic(i, j, AFF_VIDE);
            modifieCaseDynamique(i,j, '!');
        }
        else if ( i == this.getNbLigne()-1 && (j >= 60 && j <= 70))
        {
            modifieCaseStatic(i, j, AFF_VIDE);
            modifieCaseDynamique(i,j, '!');
        }
        else if ( i == this.getNbLigne()-2 && (j >= 10 && j <= 25))
        {
            modifieCaseStatic(i, j, AFF_VIDE);
            modifieCaseDynamique(i,j, '!');
        }
        else
          {
            modifieCaseStatic(i, j, AFF_SOL);
            modifieCaseDynamique(i,j, ' ');
          }
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


  public void addMonstre(Monstre m)                              // Ajoute un monstre m sur le plateau pour qu'il puisse être visualiser
  {
    int ligne = m.getPosLigne();
    int col = m.getPosColonne();
    char c = m.getCharMonstre();
    modifieCaseDynamique(ligne, col, c);
  }

  public void addCuisinier(Cuisinier c)                          // Ajoute le cuisinier au plateau pour qu'il puisse être visualiser
  {
    int ligne = c.getPosLigne();
    int col = c.getPosColonne();
    char cuis = c.getCharCuisinier();
    this.modifieCaseDynamique(ligne, col, cuis);
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
}
