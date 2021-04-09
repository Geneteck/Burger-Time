import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	private int port;
	private BufferedReader sisr;
	private PrintWriter sisw;
	private Socket socket;


public Client(int port) throws Exception
{
	this.port = port;													// Par défaut, le port sera 8080
	this.socket = new Socket("127.0.0.1", port);
	this.sisr = new BufferedReader( new InputStreamReader(socket.getInputStream()));
	this.sisw = new PrintWriter( new BufferedWriter( new OutputStreamWriter(socket.getOutputStream())),true);
	String recep = sisr.readLine();
	System.out.println(recep);
	if(recep.equals("TRUE"))
	{
		this.lance();
	}
	else
	{
		this.fermer();
	}

}

public void lance() throws Exception
{
		for(int i=0; i<50; i++) { System.out.println(""); }
		Scanner sc = new Scanner(System.in);
		System.out.println("Choisir votre pseudo ");
		String str = sc.nextLine();
		this.sisw.println(str);          																						// envoi d'un message
		str = this.sisr.readLine();      																						// lecture de la reponse
		System.out.println(str);

		while(str != "END")
		{
			for(int i=0; i<50; i++) { System.out.println(""); }
			str = this.sisr.readLine();      																					// lecture de la reponse
			System.out.println(str);
			str = sc.nextLine();
			this.sisw.println(str);          																					// envoi d'un message
		}
		this.fermer();
}

public void fermer() throws Exception
{
	this.sisr.close();
	this.sisw.close();
	this.socket.close();
}

}
