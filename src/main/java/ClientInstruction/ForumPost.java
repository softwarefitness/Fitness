package ClientInstruction;


public class ForumPost {
    private String title;
    private String content;
    private static boolean postVisible = false;

    public ForumPost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void create() {
        postVisible = true;
        System.out.println("Post titled \"" + title + "\" with content: \"" + content + "\" created successfully.");
    }

    public static boolean isPostVisible() {
        return postVisible;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

