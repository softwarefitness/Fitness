package ClientInstruction;

public class Client {
    private String clientName;

    public Client(String clientName) {
        this.clientName = clientName;
    }

    public void enroll() {
        System.out.println(clientName + " is now enrolled!");
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public boolean checkNotification() {
        System.out.println("Checking if " + clientName + " was notified.");
        return true; 
    }
}


