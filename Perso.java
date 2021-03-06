public class Perso extends Thread
{
  private int PtVie;
  private int Position;




  public class Monstre extends Perso
  {
    private int NbMonstre; //a faire varier en fonction de la difficulté

    public Monstre(int n)
    {
      super();
      this.NbMonstre = n;
    }
  }





  public class Cuisinier extends Perso
  {



    public Cuisiner()
    {
      super();
      this.PtVie = 3;
    }

}
