import java.util.*;
import class Perso;
import java.util.ArrayList;

class Joueur{
    private String pseudo;
    private int score;
    private Perso p;

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
