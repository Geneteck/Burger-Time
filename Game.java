class Game{
  private Joueur j;
  private Burger tabBurger[];

  public Joueur getJoueur(){ return this.j; }
  public void setJoueur(String pseudo){ this.j = new Joueur(pseudo); }
  public void setBurger() { this.tabBurger = new Burger[2];}


  public Game(String pseudo)
  {
    setJoueur(pseudo);
    Cuisinier c = new Cuisinier(1, 14);
    this.j.setCuisinier(c);

    Plateau p = new Plateau();
    Burger b = new Burger();
    p.addCuisinier(c);
    p.Complete();


    p.addCuisinier(c);                          // Ajoute le cuisinier sur le plateau (test)

    // Ajout de monstres dans le nouveau plateau dans lequel le joueur/cuisinier va jouer
    Saucisse S = new Saucisse(1,1,1);
    Cornichon C = new Cornichon(1,1,2);
    Oeuf O  = new Oeuf(1,1,3);
    p.addMonstre(S);
    p.addMonstre(C);
    p.addMonstre(O);

    p.affiche(c, b);

    for(int i = 0; i<2000; i++)
    {
      p.affiche(c, b);
      p.DeplacementCuisinier(c, b);
    }

  }

}
