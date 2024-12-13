package NotificationAndUpdate;

import java.util.ArrayList;
import java.util.List;

import ClientInstruction.Client;

public class Notification {
    private String content;
    private List<Client> notifiedClients;

    public Notification(String content) {
        this.content = content;
        this.notifiedClients = new ArrayList<>();
    }

    public void sendToAllClients() {
        System.out.println("Sending notification to all enrolled clients: " + content);
        // Logic to simulate notifying all clients
        notifiedClients.add(new Client("Client1"));
        notifiedClients.add(new Client("Client2"));
    }

    // جعلها غير ثابتة (non-static)
    public boolean areAllClientsNotified(List<Client> enrolledClients) {
        // تحقق مما إذا كان جميع العملاء تم إبلاغهم
        return enrolledClients.size() == notifiedClients.size();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Client> getNotifiedClients() {
        return notifiedClients;
    }
}
