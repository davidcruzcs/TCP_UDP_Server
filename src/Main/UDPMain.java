package Main;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class UDPMain {
	
	
	
	public static void main(String args[]) throws Exception
    {
		System.out.println("Starting UDP connection on port: 9876");
		
       DatagramSocket serverSocket = new DatagramSocket(9876);
          byte[] receiveData = new byte[1024];
          while(true)
             {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String( receivePacket.getData());
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                
                System.out.println("Client IPAddress: " + IPAddress +", Port: " + port + " - Sent Location: " + sentence);
                
             }
          
          
    }

}
