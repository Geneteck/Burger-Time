/*
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc
*/

public class Monstre
{
  // Déclaration des attributs de la classe Monstre

  private int NbMonstre;                                                       // Indique le nombre de monstres crée sur un plateau
  private int indicePos;                                                       // Indice d'un monstre sur un plateau
  private int posLigne;                                                        // Correspond à la ligne actuel d'un monstre
  private int posColonne;                                                      // Correspond à la colonne actuel d'un monstre
  private char charMonstre;                                                   // Variable qui contient le caractère d'un monstre : permet de savoir de quel type de monstre il s'agit
  private Monstre tabMonstre[];                                                // Structure qui contient l'ensemble des monstres crée lors d'une partie
  private Plateau plat;
  private Cuisinier cuis;

  // Méthode d'accès

  public int getPosLigne() { return this.posLigne; }
  public void setPosLigne(int lig) { this.posLigne = lig; }
  public int getPosColonne() { return this.posColonne; }
  public void setPosColonne(int col) {  this.posColonne = col; }
  public int getNbMonstre() { return this.NbMonstre; }
  public void setNbMonstre(int nb) {  this.NbMonstre = nb; }
  public char getCharMonstre() { return this.charMonstre; }
  public void setCharMonstre(char c) {  this.charMonstre = c; }
  public Monstre getMonstre(int x) { return this.tabMonstre[x]; }

  // Méthodes principales de la classe Monstre

  // Méthode qui vérifie si le déplacement de monstre demandé est possible
  public boolean valideDep(Plateau p, int val)
  {
      char tab[][] = p.getMapStatic();
      char dyn[][] = p.getMapDynamic();

      int col = this.getPosColonne();
      int lig = this.getPosLigne();

        // Vérification à gauche
        if( val == 0 && col != 0)
        {
          if( (p.getCharat(tab, lig, col-1) == this.plat.AFF_SOL || p.getCharat(tab, lig, col-1) == this.plat.AFF_ECHELLE) && (p.getCharat(dyn, lig, col-1) == 'J' || p.getCharat(dyn, lig, col-1) == this.plat.AFF_VIDE) )
            return true;
          else
            return false;
        }

        // Vérification à droite
        if( val == 1 && col != p.getNbCol()-2)
        {
          if ( (p.getCharat(tab, lig, col+1) == this.plat.AFF_SOL || p.getCharat(tab, lig, col+1) == this.plat.AFF_ECHELLE) && (p.getCharat(dyn, lig, col+1) == 'J' || p.getCharat(dyn, lig, col+1) == this.plat.AFF_VIDE) )
            return true;
          else
            return false;
        }

        // Vérification en haut
        if( val == 2 && lig != 0)
        {
          if ( p.getCharat(tab, lig, col) == this.plat.AFF_ECHELLE && p.getCharat(tab, lig-1, col) == this.plat.AFF_ECHELLE && (p.getCharat(dyn, lig-1, col) == 'J' || p.getCharat(dyn, lig-1, col) == this.plat.AFF_VIDE) )
            return true;
          else
            return false;
        }

        // Vérification en bas
        if( val == 3 && lig != p.getNbLigne()-2)
        {
           if( p.getCharat(tab, lig, col) == this.plat.AFF_ECHELLE && p.getCharat(tab, lig+1, col) == this.plat.AFF_ECHELLE && (p.getCharat(dyn, lig+1, col) == 'J' || p.getCharat(dyn, lig+1, col) == this.plat.AFF_VIDE) )
             return true;
           else
             return false;
        }

        //Si on ne rentre dans aucun if alors on retourne false
        return false;
  }

  // Méthode qui permet aux monstres d'un plateau de se déplacer sur celui-ci
  public void deplaceMonstre(Plateau p, Cuisinier c)
  {
        int deplacement;
        boolean valide = true;
        int pasBoucleInfinie = 0;

        while(valide)
        {
          this.clearMonstre(p);
          deplacement = (int)(Math.random()*4);
          pasBoucleInfinie++;

          switch(deplacement)
          {
              case 0:                                                          // Déplacement vers la gauche
                  if(this.valideDep(p, 0) == true)                             // Vérification
                  {
                    p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne()-1, this.getCharMonstre());
                    this.setPosColonne(this.getPosColonne()-1);
                    valide = false;
                  }
                  break;

              case 1:                                                          // Pour aller à droite
                  if(this.valideDep(p, 1) == true)                             // Vérification
                  {
                    p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne()+1, this.getCharMonstre());
                    this.setPosColonne(this.getPosColonne()+1);
                    valide = false;
                  }
                  break;

              case 2:       // Pour aller en haut
                // Vérification en haut
                if(this.valideDep(p, 2) == true)
                {
                  p.modifieCaseDynamique(this.getPosLigne()-1, this.getPosColonne(), this.getCharMonstre());
                  this.setPosLigne(this.getPosLigne()-1);
                  valide = false;
                }
                break;

              case 3:      // Pour aller en bas
                // Vérification en bas
                if(this.valideDep(p, 3) == true)
                {
                  p.modifieCaseDynamique(this.getPosLigne()+1, this.getPosColonne(), this.getCharMonstre());
                  this.setPosLigne(this.getPosLigne()+1);
                  valide = false;
                }
                break;
          }

          if(pasBoucleInfinie > 50)
            valide = false;
      }
    }

  // Méthode pour disposer de manière aléatoire les monstres (au préalable déjà crée), sur un plateau
  public void spawnRandom()
  {
   for(int i = 0; i < this.getNbMonstre(); i++)
   {
     char map[][] = this.plat.getMapStatic();
     char dyn[][] = this.plat.getMapDynamic();

     boolean valide = false;

     while(valide == false)
     {
       int col = (int)(Math.random()*this.plat.getNbCol());
       int lig = (int)(Math.random()*this.plat.getNbLigne());

       int x = (int)(Math.random()*3);

       if( this.plat.getCharat(map, lig, col) == this.plat.AFF_SOL && this.plat.getCharat(dyn, lig, col) == this.plat.AFF_VIDE )
         {
           switch (x)
           {
             case 0:
              {
                this.tabMonstre[i] = new Oeuf(lig, col, plat, cuis);
                valide = true;
                break;
              }

              case 1:
              {
                this.tabMonstre[i] = new Saucisse(lig, col, plat, cuis);
                valide = true;
                break;
              }

              case 2:
              {
                this.tabMonstre[i] = new Cornichon(lig, col, plat, cuis);
                valide = true;
                break;
              }
           } //end swtich
         } //end if
     } //end while
   } //end for
 }

 public void clearMonstre(Plateau p)
 {
   char dyn[][] = p.getMapDynamic();

   int col = this.getPosColonne();
   int lig = this.getPosLigne();

   if( p.getCharat(dyn, lig, col-1) != 'C' || p.getCharat(dyn, lig, col-1) != 'O' || p.getCharat(dyn, lig, col-1) != 'S' || p.getCharat(dyn, lig, col+1) != 'C' || p.getCharat(dyn, lig, col+1) != 'O' || p.getCharat(dyn, lig, col-1) != 'S' )
     p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne(), ' ');
 }

 public Monstre(int nb, Plateau plat, Cuisinier c)
 {
   setNbMonstre(nb);
   this.tabMonstre = new Monstre[nb];
   this.plat = plat;
   this.cuis = c;
 }
}

class Oeuf extends Monstre
{
  public Oeuf(int ligne, int col, Plateau plat, Cuisinier c)
  {
    super(1, plat, c);
    setPosLigne(ligne);
    setPosColonne(col);
    setCharMonstre('O');
  }
}

class Saucisse extends Monstre
{
  public Saucisse(int ligne, int col, Plateau plat, Cuisinier c)
  {
    super(1, plat, c);
    setPosLigne(ligne);
    setPosColonne(col);
    setCharMonstre('S');
  }
}

class Cornichon extends Monstre
{
  public Cornichon(int ligne, int col, Plateau plat, Cuisinier c)
  {
    super(1, plat, c);
    setPosLigne(ligne);
    setPosColonne(col);
    setCharMonstre('C');
  }
}

class MouvementMonstre extends Thread
{
  private Monstre m;
  private Plateau plat;
  private Cuisinier cuis;

  public MouvementMonstre(Monstre m, Plateau p, Cuisinier c){
   this.m = m;
   this.plat = p;
   this.cuis = c;
   m.spawnRandom();
  }

  public synchronized void run(){
    Monstre monstre;
    while(this.cuis.partieFinie() == false)
    {
      for(int j = 0; j < m.getNbMonstre(); j++)
      {
        monstre = m.getMonstre(j);
        monstre.deplaceMonstre(this.plat, this.cuis);

        if(cuis.meetMonstre(monstre.getPosLigne(), monstre.getPosColonne()))
          cuis.setVie(cuis.getVie()-1);
      }

      try{
       wait();
      }catch(InterruptedException e){e.printStackTrace();}
    }
  }

  public synchronized void notif(){
    notifyAll();
  }
}
