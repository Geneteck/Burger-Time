public class Plateau
{

  char AFF_LIMITE = 'X';
  char AFF_SOL = '_';
  char AFF_ECHELLE ='|';

  private int NB_LIGNES; // Nombre de ligne du plateau
  private int NB_COLONNES; // Nombre de colonne du plateau
  private char GRILLE[][];// Le plateau à deux dimension dans un tableau

  public void setNbLigne(int n)
  {
    this.NB_LIGNES = n;
  }

  public int getNbLigne(int n)
  {
    return this.NB_LIGNES;
  }

  public void setNbCol(int n)
  {
    this.NB_COLONNES = n;
  }

  public int getNbCol(int n)
  {
    return this.NB_COLONNES;
  }

  public Plateau(int lig, int col)
  {
    this.NB_LIGNES = lig;
    this.NB_COLONNES = col;
    this.GRILLE = new char[this.NB_LIGNES][this.NB_COLONNES];
  }

  public int getID(int lig, int col)
  {
    return (this.NB_COLONNES*lig)+col;
  }

  public void ModifieCase(int lig, int col, char c)
  {
    GRILLE[lig][col] = c;
  }

  public void PlateauNiveau1()
  {
    ModifieCase(0,0,'x');
    ModifieCase(0,1,'_');
    ModifieCase(0,2,'_');
    ModifieCase(0,3,'_');
    ModifieCase(1,0,'_');
    ModifieCase(1,1,'_');
    ModifieCase(1,2,'_');
    ModifieCase(1,3,'x');
    ModifieCase(2,0,'x');
    ModifieCase(2,1,'_');
    ModifieCase(2,2,'_');
    ModifieCase(2,3,'x');
    ModifieCase(3,0,'_');
    ModifieCase(3,1,'_');
    ModifieCase(3,2,'_');
    ModifieCase(3,3,'_');
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

  public static void main(String[] args)
  {
    Plateau p = new Plateau(4,4);
    p.PlateauNiveau1();
    p.affiche();
  }
}
