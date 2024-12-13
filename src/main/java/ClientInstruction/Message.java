package ClientInstruction;

public class Message {
    private String content;
    private boolean delivered;  

    // Constructor
    public Message(String content) {
        this.content = content;
        this.delivered = false; 
    }

    // Method to send the message
    public void sendToClient() {
        System.out.println("Message sent: " + content);
        this.delivered = true; 
        }

    // Getter for content
    public String getContent() {
        return content;
    }

    // Setter for content
    public void setContent(String content) {
        this.content = content;
    }

    // Method to check if the message is delivered
    public boolean isDelivered() {
        return delivered;
    }
}


