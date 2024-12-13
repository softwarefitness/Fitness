package NotificationAndUpdate;

public class Program {
    private String title;
    private boolean scheduleUpdated;

    public Program(String title) {
        this.title = title;
        this.scheduleUpdated = false;
    }

    public void updateSchedule() {
        this.scheduleUpdated = true;
        System.out.println("Schedule updated for program: " + title);
    }

    public boolean isScheduleVisible() {
        // تحقق إذا كان الجدول المحدث مرئيًا
        return scheduleUpdated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isScheduleUpdated() {
        return scheduleUpdated;
    }

    public void setScheduleUpdated(boolean scheduleUpdated) {
        this.scheduleUpdated = scheduleUpdated;
    }
}
