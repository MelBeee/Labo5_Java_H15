import  java.io.*;
import java.net.*;

public class Connexion implements Runnable
{
	public Socket unSocket = null; 
    public Connexion(Socket unSocketUtilise)
    {
		unSocket = unSocketUtilise;
    }

    public void run()
    {
		try
		{
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(unSocket.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(unSocket.getInputStream()));
			String uneLigne = new String();
			
			do
			{
				uneLigne = reader.readLine();
				writer.println(uneLigne);
				writer.flush();
			}while(!uneLigne.isEmpty());
			
			writer.close();
			reader.close();

			System.out.println("Client deconnecte");
		}
		catch(IOException ioe)
		{
			System.err.println("Fermeture innattendue de session sans fermer la connexion");
			System.exit(1);
		}	
		finally
		{
			try
			{
				unSocket.close();
			}
			catch(IOException ioe)
			{ 
				
			}
		}
	}
}

