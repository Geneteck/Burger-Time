/*
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc
*/

class Joueur
{
    // Déclaration des attributs
    private String pseudo;
    private int score;
    private Cuisinier cuisto;

    // Méthodes d'accès

    public String getPseudo() { return this.pseudo; }
    public int getScore(){ return this.score; }
    public void setScore(int score){ this.score = score; }
    public Cuisinier getCuisinier(){ return this.cuisto; }
    public void setCuisinier(Cuisinier c) { this.cuisto = c; }


    // Constructeur de la classe Joueur
    public Joueur(String pseudo)
    {
      this.pseudo = pseudo;
      this.score = 0;
    }
}
