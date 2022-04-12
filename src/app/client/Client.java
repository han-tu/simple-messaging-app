package app.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import java.util.logging.Level;
import java.util.logging.Logger;

import app.message.Message;
import app.server.ServerThread;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Client Alive");
        try {
            Socket socket = new Socket("127.0.0.1", 9000);
 
            ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
            WorkerThread wt = new WorkerThread(new ObjectInputStream(socket.getInputStream()));
            wt.start();
 
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
 
            System.out.println("Enter your username: \n>> ");
            String username = reader.readLine();
            
            Message firstMessage = new Message();
            firstMessage.setText(username);
            ous.writeObject(firstMessage);
            ous.flush();
            
            while(true) {
                System.out.println("---Request List Keyword---");
                System.out.println("> Private");
                System.out.println("> Broadcast");
                System.out.println("> Online Users");
                System.out.println("Enter your request: \n>>");
                String req = reader.readLine();
                
                Message message = new Message();
                message.setSender(username);
                message.setRequest(req);
                
                if (req.equals("Private")) {
                    System.out.println("Enter message receiver: \n>> ");
                    String receiver = reader.readLine();
                    message.setReceiver(receiver);
                }
                else if (req.equals("Broadcast")) {
                    
                }
                else if (req.equals("Online Users")) {
                    
                }
                
                if (req.equals("Private") || req.equals("Broadcast")) {                 
                    System.out.println("Enter your message: \n>>");
                    String text = reader.readLine();
                    message.setText(text);
                }
 
                ous.writeObject(message);
                ous.flush();
            }
 
//            unreacable statements
//            ous.close();
//            socket.close();
 
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

}
