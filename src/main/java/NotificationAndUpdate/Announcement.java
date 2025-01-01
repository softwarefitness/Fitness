package NotificationAndUpdate;

import java.util.ArrayList;
import java.util.List;

import ClientInstruction.Client;

public class Announcement {
    private String programTitle;
    private String offerDetails;
    private List<Client> notifiedClients;

    public Announcement(String programTitle, String offerDetails) {
        this.programTitle = programTitle;
        this.offerDetails = offerDetails;
        this.notifiedClients = new ArrayList<>();
    }

    public void create() {
        System.out.println("Creating announcement...");
        if (programTitle != null && !programTitle.isEmpty()) {
            System.out.println("Announcement for new program: " + programTitle);
        }
        if (offerDetails != null && !offerDetails.isEmpty()) {
            System.out.println("Announcement for special offer: " + offerDetails);
        }
    }

    public void notifyAllClients(List<Client> clients) {
        System.out.println("Notifying all clients about the announcement...");
        for (Client client : clients) {
            notifiedClients.add(client);
            System.out.println("Client notified: " + client.getClientName());
        }
    }

    public static boolean areAllClientsNotified() {
        // Logic to check if all clients are notified (stubbed for now)
        System.out.println("Checking if all clients are notified...");
        return true; // Simulate all clients being notified
    }

    public static boolean isAnnouncementVisibleInNotifications() {
        // هنا نحقق إذا كان الإعلان مرئيًا في قسم الإشعارات
        System.out.println("Checking if announcement is visible in notifications...");
        // منطق للتحقق من ظهور الإعلان (هنا سنعتبر أن الإعلان مرئي دائمًا)
        return true;  // يمكنك تخصيص المنطق بناءً على الحاجة
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    public String getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(String offerDetails) {
        this.offerDetails = offerDetails;
    }

    public List<Client> getNotifiedClients() {
        return notifiedClients;
    }
}

