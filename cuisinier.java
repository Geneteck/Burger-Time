public class cuisinier extends Entite
{
  private int ptVie; // Point de vie de l'entité

  public int getVie() { return this.ptVie; } // Renvoie les points de vie

  public void setVie(int v) { this.ptVie = v; }
  
  public cuisinier(int ind, int lig, int col)
  {
    super();
    this.ptVie = 3;
    this.setIndicePos(ind); //indice qui correspond a l'indice en bas a gauche du plateau
    this.setPosCol(col);
    this.setPosLig(lig);
  }
}
