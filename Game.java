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
    Burger b1 = new Burger(1, 45);
    Burger b2 = new Burger(2, 19);
    Burger b3 = new Burger(4, 53);

    p.addCuisinier(c);
    p.Complete();

    p.addBurger(b1);
    p.addBurger(b2);
    p.addBurger(b3);

    p.setTabBurger(0, b1);
    p.setTabBurger(1, b2);
    p.setTabBurger(2, b3);


    p.affiche(c);

    // Ajout de monstres dans le nouveau plateau dans lequel le joueur/cuisinier va jouer
    Monstre m = new Monstre(1);
    Monstre pig;
    m.SpawnRandom(p);

    while(c.PartieFinie() == false)
    {
      p.DeplacementCuisinier(c);
      p.score(c);

      for(int j = 0; j < m.getNbMonstre(); j++)
      {
        pig = m.getMonstre(j);
        pig.DeplacementMonstre(p, c);
      }
      p.affiche(c);
    }

    System.out.println("END GAME, THE PLAYER IS DEAD !!");

  }

  public Game2()
  {
    setJoueur(pseudo);
    Cuisinier c = new Cuisinier(1, 14);
    this.j.setCuisinier(c);

    Plateau p = new Plateau();
    LocalServ serv = new Local(8080)
    Burger b1 = new Burger(1, 45);
    Burger b2 = new Burger(2, 19);
    Burger b3 = new Burger(4, 53);

    p.addCuisinier(c);
    p.Complete();

    p.addBurger(b1);
    p.addBurger(b2);
    p.addBurger(b3);

    p.setTabBurger(0, b1);
    p.setTabBurger(1, b2);
    p.setTabBurger(2, b3);


    p.affiche(c);

    // Ajout de monstres dans le nouveau plateau dans lequel le joueur/cuisinier va jouer
    Monstre m = new Monstre(1);
    Monstre pig;
    m.SpawnRandom(p);
    LocalServ serv = new Local(8080, p);

    while(c.PartieFinie() == false)
    {
      p.DeplacementCuisinier(c);
      p.score(c);

      for(int j = 0; j < m.getNbMonstre(); j++)
      {
        pig = m.getMonstre(j);
        pig.DeplacementMonstre(p, c);
      }
      p.affiche(c);
    }

    System.out.println("END GAME, THE PLAYER IS DEAD !!");

  }

}
