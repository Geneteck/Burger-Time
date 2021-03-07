import java.util.*;
import Entite.*;




public class Plateau
{

  String AFF_LIMITE = "X ";
  String AFF_SOL = "_ ";
  String AFF_ECHELLE ="||";

  private int NB_LIGNES; // Nombre de ligne du plateau
  private int NB_COLONNES; // Nombre de colonne du plateau
  private String GRILLE[][];// Le plateau à deux dimension dans un tableau

  public void setNbLigne(int n) { this.NB_LIGNES = n; }

  public int getNbLigne() { return this.NB_LIGNES; }

  public void setNbCol(int n) { this.NB_COLONNES = n; }

  public int getNbCol() { return this.NB_COLONNES; }

  public int getID(int lig, int col) { return (this.NB_COLONNES*lig)+col; }


  public Plateau(int lig, int col)
  {
    this.NB_LIGNES = lig;
    this.NB_COLONNES = col;
    this.GRILLE = new String[lig][col];
  }

  public void ModifieCase(int lig, int col, String c)
  {
    GRILLE[lig][col] = c;
  }

  public void PlateauNiveau1()
  {
    ModifieCase(0,0,AFF_ECHELLE);
    ModifieCase(0,1,AFF_SOL);
    ModifieCase(0,2,AFF_SOL);
    ModifieCase(0,3,AFF_SOL);
    ModifieCase(1,0,AFF_SOL);
    ModifieCase(1,1,AFF_SOL);
    ModifieCase(1,2,AFF_SOL);
    ModifieCase(1,3,AFF_ECHELLE);
    ModifieCase(2,0,AFF_ECHELLE);
    ModifieCase(2,1,AFF_SOL);
    ModifieCase(2,2,AFF_SOL);
    ModifieCase(2,3,AFF_ECHELLE);
    ModifieCase(3,0,AFF_SOL);
    ModifieCase(3,1,AFF_SOL);
    ModifieCase(3,2,AFF_SOL);
    ModifieCase(3,3,AFF_SOL);
  }

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

  public boolean Valide(char c, int ligCuisto, int colCuisto)
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
          ModifieCase(cuisto.getPosLig(), cuisto.getPosCol(), "C ");
        }
        else if(touche == 'q' && this.valide('q', cuisto.getPosLig(), cuisto.getPosCol()))
        {
          cuisto.setPosCol(cuisto.getPosCol()-1);
          ModifieCase(cuisto.getPosLig(), cuisto.getPosCol(), "C ");
        }
        else if(touche == 'd'  && this.valide('d', cuisto.getPosLig(), cuisto.getPosCol()))
        {
          cuisto.setPosCol(cuisto.getPosCol()+1);
          ModifieCase(cuisto.getPosLig(), cuisto.getPosCol(), "C ");
        }
        else if(touche == 's'  && this.valide('s', cuisto.getPosLig(), cuisto.getPosCol()))
        {
          cuisto.setPosLig(cuisto.getPosLig()+1);
          ModifieCase(cuisto.getPosLig(), cuisto.getPosCol(), "C ");
        }
      this.affiche();
    }
  }

  public static void main(String[] args)
  {
    Plateau p = new Plateau(4,4);
    p.PlateauNiveau1();
    p.affiche();
    Entite e = new Entite(0,0,0);
  }
}
