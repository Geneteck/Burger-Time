import java.io.*;
import java.net.*;

class LocalClient {
   // Déclaration des attributs de la classes LocalServ
   private static final int port = 8080;
	 private Socket socket;

	 // BufferedReader permet de lire par ligne
	 private BufferedReader sisr;

	 // Un PrintWriter possede toutes les operations print classiques.
	 private PrintWriter sisw;

   public static void main(String[] args) throws Exception {
        Socket socket = new Socket(args[0], port);
        // illustration des capacites bidirectionnelles du flux
        BufferedReader sisr = new BufferedReader(
                               new InputStreamReader(socket.getInputStream()));

        PrintWriter sisw = new PrintWriter(new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream())),true);
        String str = "bonjour ";
        for (int i = 0; i < 10; i++) {
           sisw.println(str+i);          // envoi d'un message
           str = sisr.readLine();      // lecture de la reponse
	   System.out.println(str);
        }
        System.out.println("END");     // message de fermeture
        //sisw.println("END") ;
        sisr.close();
        sisw.close();
        socket.close();
   }

  public void fin()
  {
    this.sisr.close();
    this.sisw.close();
    this.socket.close();
  }

  public LocalClient()
  {
    this.socket = new Socket(args[0], port);

    BufferedReader sisr = new BufferedReader( new InputStreamReader(soc.getInputStream()));
    PrintWriter sisw = new PrintWriter( new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())),true);
  }

}
