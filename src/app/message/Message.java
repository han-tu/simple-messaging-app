package app.message;

import java.io.Serializable;

public class Message implements Serializable {
	/**
	 * Class Message sebagai objek yang akan dikirimkan melalui socket
	 */
	private static final long serialVersionUID = 123;
	private String sender;			// Message Sender
	private String receiver;		// Message Receiver
	private String text;			// Message Text
    private String request;			// Message Request Type
    
    /*
     * Request Type List:
     * "Online Users"	--> Request a list of online users
     * "Private"		--> Send message privately
     * "Broadcast"		--> Send message as a broadcast to all online users
     */

    public Message(String request) {
    	this.request = request;
    }
    
    public Message() {
    	
    }
    
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public String getReceiver() {
    	return receiver;
    }
    
    public void setReceiver(String receiver) {
    	this.receiver = receiver;
    }

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

}
