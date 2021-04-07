import java.io.*;
import java.net.*;

class LocalServ {
	 // Déclaration des attributs de la classes LocalServ
   private static final int port = 8080;
	 private ServerSocket s;
	 private Socket soc;

	 // BufferedReader permet de lire par ligne
	 private BufferedReader sisr;

	 // Un PrintWriter possede toutes les operations print classiques.
	 private PrintWriter sisw;

   public static void main(String[] args) throws Exception {
        LocalServ ls = new LocalServ();
   }

	// Pour fermer le serveur (si besoin)
	public void fin() throws Exception
	{
		this.sisr.close();
		this.sisw.close();
		this.soc.close();
	}

	// Pour lancer le LocalServ
	public void start() throws Exception
	{
		String str;
		do{
		 str = sisr.readLine();          // lecture du message
		 // System.out.println("ECHO = " + str);   // trace locale
	 	}while("END".equals(str));
		// this.fin();											// Permet de fermet le serveur une fois qu'on a reçus un score hors ce n'est pas ce que l'on veut
	}

	public LocalServ() throws Exception
	{
		 this.s = new ServerSocket(port);
		 this.soc = s.accept();

		 this.sisr = new BufferedReader( new InputStreamReader(soc.getInputStream()));
		 this.sisw = new PrintWriter( new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())),true);
		 this.start();
	}

}
