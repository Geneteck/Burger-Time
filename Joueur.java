/* 
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc

    (Mettre à jour le numéro de version à chaque modification de Joueur)
    Version : V.1.00
*/

class Joueur{
    private String pseudo;
    private int score;
    private cuisinier cuisto;

    public int getScore(){
      return this.score;
    }

    public void setScore(int score){
      this.score = score;
    }

    public Joueur(String pseudo, int score){
      this.pseudo = pseudo;
      this.score = score;
    }
}
