/*
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc

    (Mettre à jour le numéro de version à chaque modification de Joueur)
    Version : V.1.00
*/

class Joueur{
    String tiret = "------------------------------------------------------------------------------------";
    String tabu1 = "                      ";         // 12 tabulations
    String tabu2 = "              ";                        // 7 tabulations

    private String pseudo;
    private int score;
    private Cuisinier cuisto;

    public String getPseudo() { return this.pseudo; }

    public int getScore(){ return this.score; }

    public void setScore(int score){ this.score = score; }

    public Cuisinier getCuisinier(){ return this.cuisto; }

    public void setCuisinier(Cuisinier c) { this.cuisto = c; }



    public Joueur(String pseudo){
      this.pseudo = pseudo;
      this.score = 0;
    }
}
