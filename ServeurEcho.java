import java.io.*;
import java.net.*;

public  class ServeurEcho
{
	public void lancerServeur(int port)
	{
		try
		{
			Socket unSocket = null;
			ServerSocket socketServeur = null;
			
			socketServeur = new ServerSocket(port);
			System.out.println("Serveur echo en attente d'une connexion.");
			
			Terminateur unTerminateur = new Terminateur();
			Thread unDeTerminateur = new Thread(unTerminateur);
			unDeTerminateur.setDaemon(true);
			unDeTerminateur.start();
			
			while(unDeTerminateur.isAlive())
			{
				unSocket = socketServeur.accept();
				System.out.println("Connexion du client.");
				
				Connexion uneConnexion = new Connexion(unSocket);
				Thread unDeConnexion = new Thread(uneConnexion);
				unDeConnexion.setDaemon(true);
				unDeConnexion.start();
			}	
			
			if(unSocket != null)
			{
				unSocket.close();
				socketServeur.close();
			}
		
			System.out.println("Deconnexion du client.");
			System.exit(1);
		}
		catch(IOException ioe)
		{
			System.err.println(ioe);
			System.exit(1);
		}
	}

	public static void main(String args[]) throws IOException
	{
		ServeurEcho unBeauServeur = new ServeurEcho();
		unBeauServeur.lancerServeur(7);
	}
}













