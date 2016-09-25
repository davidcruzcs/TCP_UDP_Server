package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPMain {
	
	public static void main(String args[]) throws Exception
    {
	
	System.out.println("Starting TCP connection on port: 6789");
    String clientSentence;
    String capitalizedSentence;
    ServerSocket welcomeSocket = new ServerSocket(6789);

    while(true)
    {
       Socket connectionSocket = welcomeSocket.accept();
       BufferedReader inFromClient =
       new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
       DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
       PrintWriter out = new PrintWriter(new BufferedWriter(
               new OutputStreamWriter(connectionSocket.getOutputStream())),
               true);
       clientSentence = inFromClient.readLine();
       if (clientSentence.equalsIgnoreCase("Hello")) {
    	   out.println("HelloBack");
    	   
    	   String readed = "";
    	   readed = inFromClient.readLine();
    	   while (readed.equalsIgnoreCase("Bye") == false) {
    		   
    		   System.out.println("Client: " + connectionSocket.getRemoteSocketAddress() + " - Sent Location: " + readed);
    		   
    		   readed = inFromClient.readLine();
    	   }
    	   
    	   if (readed.equalsIgnoreCase("Bye")) {
    		   connectionSocket.close();
    	   }
    	   
    	   
       } else {
    	   out.println("Close");
    	   connectionSocket.close();
       }

    }
    
    }

}
