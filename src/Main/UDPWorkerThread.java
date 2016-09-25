package Main;

import java.io.FileWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Arrays;

public class UDPWorkerThread implements Runnable {
	
    protected FileWriter writer = null;
    protected String clientSentence = "";
    protected DatagramPacket receivePacket = null;

    public UDPWorkerThread(DatagramPacket packet, FileWriter writer) {
       
        this.writer = writer;
        this.receivePacket = packet;
    }

    public void run() {
        try {
        	String sentence = new String( receivePacket.getData());
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            
            String ThreadID = sentence.substring(sentence.indexOf("|")).replace("|", "");

            CSVUtils.writeLine(writer, Arrays.asList(ThreadID, IPAddress.toString().replaceAll("/", ""), port+"", sentence));
            
            writer.flush();
            
            
            System.out.println("Client IPAddress: " + IPAddress +", Port: " + port + " - Sent Location: " + sentence);
            
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
    }
            


}
