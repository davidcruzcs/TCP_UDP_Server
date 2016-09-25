package Main;


import java.io.File;
import java.io.FileWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class UDPMain {
	
	public static ExecutorService tpes;
	
	
	@SuppressWarnings({ "deprecation", "resource" })
	public static void main(String args[]) throws Exception
    {
		System.out.println("Starting UDP connection on port: 9876");
	
		tpes = Executors.newFixedThreadPool(300);
		
		Date now = new Date();
 	   String csvFile = "csvUDP/Run"+ now.getDay() + now.getHours() + now.getMinutes() + now.getSeconds()  +".csv";
     File file = new File(csvFile);
     if (file.exists() == false) {
  	   file.createNewFile();
     }
 	   FileWriter writer = new FileWriter(csvFile);
 	   
 	CSVUtils.writeLine(writer, Arrays.asList("Thread ID", "Client IP Address", "Client Port", "User Latitud", "User Longitud"));
  
		
       DatagramSocket serverSocket = new DatagramSocket(9876);
          byte[] receiveData = new byte[1024];
          while(true)
             {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                tpes.execute(new UDPWorkerThread(receivePacket, writer));
                
             }
        
          
          
    }

}
