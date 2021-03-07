import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Plateau
{

  String AFF_LIMITE = "X ";
  String AFF_SOL = "_ ";
  String AFF_ECHELLE ="||";
  String AFF_CUISTO = "C ";

  private int NB_LIGNES; // Nombre de ligne du plateau
  private int NB_COLONNES; // Nombre de colonne du plateau
  private String GRILLE[][];// Le plateau à deux dimension dans un tableau

  public void setNbLigne(int n) { this.NB_LIGNES = n; }

  public int getNbLigne() { return this.NB_LIGNES; }

  public void setNbCol(int n) { this.NB_COLONNES = n; }

  public int getNbCol() { return this.NB_COLONNES; }

  public int getID(int lig, int col) { return (this.NB_COLONNES*lig)+col; }

  public void modifieCase(int lig, int col, String c) { GRILLE[lig][col] = c; }

  // Fonction valide vérifie si le déplacement (cuisto) désiré est réalisable
  public boolean valide(char c, int ligCuisto, int colCuisto)
  {
    boolean verif = false;
    switch(c)
    {
      case 'z' :
      {
        if(GRILLE[ligCuisto+1][colCuisto] == AFF_ECHELLE) { verif = true;}
        break;
      }

      case 'q' :
      {
        if(GRILLE[ligCuisto][colCuisto-1] != AFF_LIMITE) { verif = true;}
        break;
      }

      case 's' :
      {
        if(GRILLE[ligCuisto-1][colCuisto] == AFF_ECHELLE) { verif = true;}
        break;
      }

      case 'd' :
      {
        if(GRILLE[ligCuisto][colCuisto+1] != AFF_LIMITE) { verif = true;}
        break;
      }
    }
    return verif;
  }

  // Fonction appelé lorsque l'on demande le déplacement du cuisinier, déplacement asynchrone
  public void DeplacementCuisinier(Entite cuisto)
  {
    for (int i = 0 ; i<5; i++ )
    {
      Scanner sc = new Scanner(System.in);  // Create a Scanner object
      System.out.println("Déplacer le cuisto");

      char touche = sc.next().charAt(0);    // Read user input

        if(touche == 'z' && this.valide('z', cuisto.getPosLig(), cuisto.getPosCol()))
        {
          cuisto.setPosLig(cuisto.getPosLig()-1);
          modifieCase(cuisto.getPosLig(), cuisto.getPosCol(), AFF_CUISTO);
          modifieCase(cuisto.getPosLig()+1, cuisto.getPosCol()+1, AFF_SOL);
        }
        else if(touche == 'q' && this.valide('q', cuisto.getPosLig(), cuisto.getPosCol()))
        {
          cuisto.setPosCol(cuisto.getPosCol()-1);
          modifieCase(cuisto.getPosLig(), cuisto.getPosCol(), AFF_CUISTO);
          modifieCase(cuisto.getPosLig(), cuisto.getPosCol()+1, AFF_SOL);
        }
        else if(touche == 'd'  && this.valide('d', cuisto.getPosLig(), cuisto.getPosCol()))
        {
          cuisto.setPosCol(cuisto.getPosCol()+1);
          modifieCase(cuisto.getPosLig(), cuisto.getPosCol(), AFF_CUISTO);
          modifieCase(cuisto.getPosLig(), cuisto.getPosCol()-1, AFF_SOL);
        }
        else if(touche == 's'  && this.valide('s', cuisto.getPosLig(), cuisto.getPosCol()))
        {
          cuisto.setPosLig(cuisto.getPosLig()+1);
          modifieCase(cuisto.getPosLig(), cuisto.getPosCol(), AFF_CUISTO);
          modifieCase(cuisto.getPosLig()-1, cuisto.getPosCol(), AFF_SOL);
        }
      this.affiche();
    }
  }

  // Fonction affiche permet de visualiser l'état du plateau
  public void affiche()
  {
    for(int i=-1; i<=this.NB_COLONNES;i++)
    {
      for(int j=-1; j<=this.NB_LIGNES; j++)
      {
        if (i == -1 || j == -1 || i == NB_LIGNES || j ==  NB_COLONNES) // permet de faire une limite autour du plateau de jeux
            {
                System.out.print(AFF_LIMITE);
            }
        else
          System.out.print(GRILLE[i][j]);
      }
      System.out.println("\n");
    }
  }

  // Constructeur(s) de la classe
  public Plateau(int lig, int col)
  {
    this.setNbLigne(lig);
    this.setNbCol(col);
    this.GRILLE = new String[lig][col];
  }

  // Modifie chaque case d'une nouvelle instance de la classe Plateau, par des caractères définis plus haut
  public void PlateauNiveau1()
  {
    modifieCase(0,0,AFF_ECHELLE);
    modifieCase(0,1,AFF_SOL);
    modifieCase(0,2,AFF_SOL);
    modifieCase(0,3,AFF_SOL);
    modifieCase(1,0,AFF_SOL);
    modifieCase(1,1,AFF_SOL);
    modifieCase(1,2,AFF_SOL);
    modifieCase(1,3,AFF_ECHELLE);
    modifieCase(2,0,AFF_ECHELLE);
    modifieCase(2,1,AFF_SOL);
    modifieCase(2,2,AFF_SOL);
    modifieCase(2,3,AFF_ECHELLE);
    modifieCase(3,0,AFF_SOL);
    modifieCase(3,1,AFF_SOL);
    modifieCase(3,2,AFF_SOL);
    modifieCase(3,3,AFF_SOL);
  }

  // Main
 public static void main(String[] args) {
    Plateau p = new Plateau(4,4);
    p.PlateauNiveau1();
    p.affiche();
    Entite e = new Entite(p.getID(p.getNbLigne()-1, p.getNbCol()-1),p.getNbLigne()-1,p.getNbCol()-1);
    p.DeplacementCuisinier(e);
 }
}
