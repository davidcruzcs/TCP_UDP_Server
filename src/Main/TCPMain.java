package Main;


import java.io.File;
import java.io.FileWriter;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;

public class TCPMain {
	
	@SuppressWarnings({ "deprecation", "resource", "unused" })
	public static void main(String args[]) throws Exception
    {
	
	System.out.println("Starting TCP connection on port: 6789");
    String clientSentence;
    String capitalizedSentence;
    ServerSocket welcomeSocket = new ServerSocket(6789);
    
    Date now = new Date();
	   String csvFile = "csvTCP/Run"+ now.getDay() + now.getHours() + now.getMinutes() + now.getSeconds()  +".csv";
    File file = new File(csvFile);
    if (file.exists() == false) {
 	   file.createNewFile();
    }
	   FileWriter writer = new FileWriter(csvFile);
	   
	CSVUtils.writeLine(writer, Arrays.asList("Thread ID", "Client IP Address", "Client Port", "User Latitud", "User Longitud"));
    writer.flush();

    while(true)
    {
    	
       Socket connectionSocket = welcomeSocket.accept();
       
       new Thread(new TCPWorkerThread(connectionSocket, writer)).start();
       
       
    }

    
    }

}
