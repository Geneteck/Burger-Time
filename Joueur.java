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
