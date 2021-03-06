public class Joueur{
    private String pseudo;
    private int score;

    public int getScore(){
      return this.score;
    }

    public void setScore(int score){
      this.score = score;
    }

    public void Joueur(String pseudo, int score){
      this.pseudo = pseudo;
      this.score = score;
    }
}

public static void main(){
  Joueur j = new Joueur("Loïc", 0);
}
