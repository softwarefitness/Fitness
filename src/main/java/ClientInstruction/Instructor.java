package ClientInstruction;
	
	public class Instructor {
	    private String username;
	    private String password;

	    public Instructor(String username, String password) {
	        this.username = username;
	        this.password = password;
	    }

	    public void login() {
	        System.out.println(username + " logged in successfully!");
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	}

