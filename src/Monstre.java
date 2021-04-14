/*
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc
*/

public class Monstre
{
  // Déclaration des attributs de la classe Monstre

  private int NbMonstre;                                                        // Indique le nombre de monstres crée sur un plateau
  private int indicePos;                                                        // Indice d'un monstre sur un plateau
  private int posLigne;                                                         // Correspond à la ligne actuel d'un monstre
  private int posColonne;                                                       // Correspond à la colonne actuel d'un monstre
  private char charMonstre;                                                     // Variable qui contient le caractère d'un monstre : permet de savoir de quel type de monstre il s'agit
  private Monstre tabMonstre[];                                                 // Structure qui contient l'ensemble des monstres crée lors d'une partie
  private Plateau plat;                                                         //plateau courant
  private Cuisinier cuis;                                                       //cuisinier courant

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
  public boolean valideDep(int val)
  {
      char tab[][] = this.plat.getMapStatic();
      char dyn[][] = this.plat.getMapDynamic();

      int col = this.getPosColonne();
      int lig = this.getPosLigne();

        // Vérification à gauche
        if( val == 0 && col != 0)
        {
          if( (this.plat.getCharat(tab, lig, col-1) == this.plat.AFF_SOL || this.plat.getCharat(tab, lig, col-1) == this.plat.AFF_ECHELLE) && (this.plat.getCharat(dyn, lig, col-1) == 'J' || this.plat.getCharat(dyn, lig, col-1) == this.plat.AFF_VIDE) )
            return true;
          else
            return false;
        }

        // Vérification à droite
        if( val == 1 && col != this.plat.getNbCol()-2)
        {
          if ( (this.plat.getCharat(tab, lig, col+1) == this.plat.AFF_SOL || this.plat.getCharat(tab, lig, col+1) == this.plat.AFF_ECHELLE) && (this.plat.getCharat(dyn, lig, col+1) == 'J' || this.plat.getCharat(dyn, lig, col+1) == this.plat.AFF_VIDE) )
            return true;
          else
            return false;
        }

        // Vérification en haut
        if( val == 2 && lig != 0)
        {
          if ( this.plat.getCharat(tab, lig, col) == this.plat.AFF_ECHELLE && this.plat.getCharat(tab, lig-1, col) == this.plat.AFF_ECHELLE && (this.plat.getCharat(dyn, lig-1, col) == 'J' || this.plat.getCharat(dyn, lig-1, col) == this.plat.AFF_VIDE) )
            return true;
          else
            return false;
        }

        // Vérification en bas
        if( val == 3 && lig != this.plat.getNbLigne()-2)
        {
           if( this.plat.getCharat(tab, lig, col) == this.plat.AFF_ECHELLE && this.plat.getCharat(tab, lig+1, col) == this.plat.AFF_ECHELLE && (this.plat.getCharat(dyn, lig+1, col) == 'J' || this.plat.getCharat(dyn, lig+1, col) == this.plat.AFF_VIDE) )
             return true;
           else
             return false;
        }

        //Si on ne rentre dans aucun if alors on retourne false
        return false;
  }

  // Méthode qui permet aux monstres d'un plateau de se déplacer sur celui-ci
  public void deplaceMonstre()
  {
        int deplacement;
        boolean valide = true;
        int pasBoucleInfinie = 0;

        while(valide)
        {
          this.clearMonstre();
          deplacement = (int)(Math.random()*4);
          pasBoucleInfinie++;

          switch(deplacement)
          {
              case 0:                                                           // Déplacement vers la gauche
                  if(this.valideDep(0) == true)                                 // Vérification
                  {
                    this.plat.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne()-1, this.getCharMonstre());
                    this.setPosColonne(this.getPosColonne()-1);
                    valide = false;
                  }
                  break;

              case 1:                                                           // Déplacement vers la droite
                  if(this.valideDep(1) == true)                                 // Vérification
                  {
                    this.plat.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne()+1, this.getCharMonstre());
                    this.setPosColonne(this.getPosColonne()+1);
                    valide = false;
                  }
                  break;

              case 2:                                                           // Déplacement vers le haut
                if(this.valideDep(2) == true)                                   // Vérification
                {
                  this.plat.modifieCaseDynamique(this.getPosLigne()-1, this.getPosColonne(), this.getCharMonstre());
                  this.setPosLigne(this.getPosLigne()-1);
                  valide = false;
                }
                break;

              case 3:                                                           // Déplacement vers le bas
                if(this.valideDep(3) == true)                                   // Vérification
                {
                  this.plat.modifieCaseDynamique(this.getPosLigne()+1, this.getPosColonne(), this.getCharMonstre());
                  this.setPosLigne(this.getPosLigne()+1);
                  valide = false;
                }
                break;
          }

          if(pasBoucleInfinie > 50)                                             // Condition qui permet de ne pas boucler a l'infinie
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

   // Méthode qui efface l'ancienne position du monstre en verifiant a ne pas effacer d'autre éléments
  public void clearMonstre()
  {
     char dyn[][] = this.plat.getMapDynamic();

     int col = this.getPosColonne();
     int lig = this.getPosLigne();

     if( this.plat.getCharat(dyn, lig, col-1) != 'C' || this.plat.getCharat(dyn, lig, col-1) != 'O' || this.plat.getCharat(dyn, lig, col-1) != 'S' || this.plat.getCharat(dyn, lig, col+1) != 'C' || this.plat.getCharat(dyn, lig, col+1) != 'O' || this.plat.getCharat(dyn, lig, col-1) != 'S' )
       this.plat.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne(), ' ');
   }

  //Constructeur de la classe Monstre
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
        monstre.deplaceMonstre();

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

class MouvementMonstreMulti extends Thread
{
  private Monstre m;
  private Plateau plat;
  private Cuisinier cuis1;
  private Cuisinier cuis2;

  public MouvementMonstreMulti(Monstre m, Plateau p, Cuisinier c1, Cuisinier c2){
   this.m = m;
   this.plat = p;
   this.cuis1 = c1;
   this.cuis2 = c2;
   m.spawnRandom();
  }

  public synchronized void run(){
    Monstre monstre;
    while(this.cuis1.partieFinie() == false )
    {
      for(int j = 0; j < m.getNbMonstre(); j++)
      {
        monstre = m.getMonstre(j);
        monstre.deplaceMonstre();
        if(cuis1.meetMonstre(monstre.getPosLigne(), monstre.getPosColonne()) || cuis2.meetMonstre(monstre.getPosLigne(), monstre.getPosColonne()))
          cuis1.setVie(cuis1.getVie()-1);
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
