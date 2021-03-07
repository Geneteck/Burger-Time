

spublic class Entite extends Thread
{
  // Attributs des Entités
  private String nom; // Cuisinier ou oeuf ou cornichon etc...
  private int indicePos; // Attribut qui correspond à l'indice de l'entité sur le plateau
  // La ligne et la colonne d'une entité
  private int posLig; // Correspond à la ligne actuel de l'entité
  private int posCol; // Correspond à la colonne actuel de l'entité

  // méthode de la class Entite
  public int getVie() { return this.ptVie; } // Renvoie les points de vie

  public void setVie(int v) { this.ptVie = v; }

  public String getNom(){ return this.nom; }

  public void setNom(String n){ this.nom = n ; }

  public int getIndicePos(){ return this.indicePos; }

  public void setIndicePos(int i) { this.indicePos = i; }

  public int getPosLig() { return this.posLig;}

  public void setPosLig(int lig) { this.posLig = lig; }

  public int getPosCol() { return this.posCol;}

  public void setPosCol(int col) {  this.posCol = col;}



  public class Monstre extends Entite
  {
    private int NbMonstre; //a faire varier en fonction de la difficulté

    public Monstre(int n)
    {
      super();
      this.NbMonstre = n;
    }

    public void run(){
      //a voir plutard
    }
  }





  public class Cuisinier extends Entite
  {
    private int ptVie; // Point de vie de l'entité

    public Cuisiner()
    {
      super();
      this.PtVie = 3;
    }

    while( partie != fini)
    {
      String touche = Lire.S();

      if(touche.equals("z"))
      {

      }
      else if(touche.equals("q"))
      {
        int
        ModifieCase();
      }
      else if(touche.equals("d"))
      {

      }
      else if(touche.equals("s"))
      {

      }
    }

}
