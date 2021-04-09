/*
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc
*/


public class Cuisinier extends Thread
{
  // Déclaration des attributs de la classe Cuisinier

  private int pv;                                                              // Indique le nombre de poits de vie d'un cuisnier
  private int posLigne;                                                        // Correspond à la ligne actuel d'un Cuisinier
  private int posColonne;                                                      // Correspond à la colonne actuel du Cuisinier
  private char charCuis;
  private int Score;

  // Méthodes d'accès

  // Renvoie les poits de vie du cuisto
  public int getVie() { return this.pv; }
  public void setVie(int v) { this.pv = v; }
  public int getPosLigne(){ return this.posLigne; }
  public void setPosLigne(int lig) { this.posLigne = lig; }
  public int getPosColonne(){ return this.posColonne; }
  public void setPosColonne(int col) { this.posColonne = col; }
  public char getCharCuisinier() { return this.charCuis; }
  public void setCharCuis(char c) { this.charCuis = c;}
  public int getScore(){ return this.Score; }
  public void setScore(int score) { this.Score = score; }

  // Méthodes principales de la classe Cuisinier

  // Méthode qui
  public void clearCuisinier(Plateau p)
  {
    char dyn[][] = p.getMapDynamic();

    int col = this.getPosColonne();
    int lig = this.getPosLigne();

    if( p.getCharat(dyn, lig, col-1) != 'C' || p.getCharat(dyn, lig, col-1) != 'O' || p.getCharat(dyn, lig, col-1) != 'S' || p.getCharat(dyn, lig, col+1) != 'C' || p.getCharat(dyn, lig, col+1) != 'O' || p.getCharat(dyn, lig, col-1) != 'S' )
      p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne(), ' ');
  }

  // Méthode qui vérifie si le cuisinier à rencontrer/toucher un monstre
  public boolean meetMonstre(int ligne, int col)
  {
    int ligneC = this.getPosLigne();
    int colC = this.getPosColonne();

    if(ligne == ligneC && col == colC)
       return true;
    else
       return false;
  }

  // Méthode qui vérifie les points de vie restat d'un cuisinier
  public boolean partieFinie()
  {
    if(this.getVie() < 1)
      return true;
    else
      return false;
  }

  // Méthode qui affiche le score et les points de vies du joueur
  public void afficheScorePv()
  {
    System.out.print(" SCORE : "+this.getScore() + "\n                                             " + " PV Cuisinier : "+ this.getVie());
  }

  // Constructeur de la classe Cuisinier
  public Cuisinier(int lig, int col)
  {
    setVie(3);
    setPosColonne(col);
    setPosLigne(lig);
    setCharCuis('J');
    setScore(0);
  }

}
