package Main;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

/**

 */
public class TCPWorkerThread implements Runnable{

    protected Socket connectionSocket = null;
    protected FileWriter writer = null;
    protected String clientSentence = "";

    public TCPWorkerThread(Socket clientSocket, FileWriter writer) {
        this.connectionSocket = clientSocket;
        this.writer = writer;
    }

    public void run() {
        try {
            

            BufferedReader inFromClient =
            new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(connectionSocket.getOutputStream())),
                    true);
            clientSentence = inFromClient.readLine();
            if (clientSentence.equalsIgnoreCase("Hello")) {
         	   out.println("HelloBack");
         	   
         	   
         	   
         	   String readed = inFromClient.readLine().toString() + "";
         	   String readedID = inFromClient.readLine().toString() + "";
           	  
         	   

                while (readed.equalsIgnoreCase("Bye") == false) {
         		   

                    CSVUtils.writeLine(writer, Arrays.asList(readedID, connectionSocket.getInetAddress().toString().replaceAll("/", ""), connectionSocket.getPort()+"", readed));
                    
         		   System.out.println("Client: " + connectionSocket.getRemoteSocketAddress() + " - Sent Location: " + readed);
         		   
         		   
         		   readed = inFromClient.readLine();
         		   readedID = inFromClient.readLine();
         	   }
         	   
         	   if (readed.equalsIgnoreCase("Bye")) {
         		   writer.flush();
         		   connectionSocket.close();
         	   }
         	   
         	   
            } else {
         	   out.println("Close");
         	   connectionSocket.close();
            }

        	
        	
        } catch (Exception e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}