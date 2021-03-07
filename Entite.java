public class Entite
{
  // Attributs des Entités
  private String nom; // Cuisinier ou oeuf ou cornichon etc...
  private int indicePos; // Attribut qui correspond à l'indice de l'entité sur le plateau
  // La ligne et la colonne d'une entité
  private int posLig; // Correspond à la ligne actuel de l'entité
  private int posCol; // Correspond à la colonne actuel de l'entité

  // méthode de la class Entite
  public String getNom(){ return this.nom; }

  public void setNom(String n){ this.nom = n ; }

  public int getIndicePos(){ return this.indicePos; }

  public void setIndicePos(int i) { this.indicePos = i; }

  public int getPosLig() { return this.posLig;}

  public void setPosLig(int lig) { this.posLig = lig; }

  public int getPosCol() { return this.posCol;}

  public void setPosCol(int col) {  this.posCol = col;}

  // Constructeur de la classe Entite
  Entite(int ind, int lig, int col)
  {
    this.setIndicePos(ind); // Indice qui correspond a l'indice en bas a gauche du plateau (pour le début de partie)
    this.setPosLig(lig);
    this.setPosCol(col);
  }

  Entite(){ }

  public static void main(String[] args) {
      Entite e = new Entite(0,0,0);
      e.setPosLig(1);
  }
}
