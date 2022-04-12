package app.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import app.message.Message;
import app.server.ServerThread;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Client Alive");
		try {
			Socket socket = new Socket("localhost",6666);
			
			ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
			
			System.out.println("Input request type: ");
			Scanner msgRequest = new Scanner(System.in);
			Message msg = new Message(msgRequest.toString());
			
			System.out.println("Input sender name: ");
			Scanner msgSender = new Scanner(System.in);
			msg.setSender(msgSender.toString());
			
			System.out.println("Input receiver name: ");
			Scanner msgReceiver = new Scanner(System.in);
			msg.setReceiver(msgReceiver.toString());
			
			System.out.println("Input text message: ");
			Scanner msgText = new Scanner(System.in);
			msg.setText(msgText.toString());
			
			ServerThread st = new ServerThread();
			
			if(msg.getRequest() == "Online User") {
				st.getOnlineUsers();
				
			} else if (msg.getRequest() == "Private") {
				st.sendPrivately(msg, msg.getReceiver());
			} else {
				st.sendToAll(msg);
			}
			
			ous.flush();
			
			ous.close();
			socket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
