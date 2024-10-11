public class User {
    private String username;
    //Default Constructor
    public User() {                   
        this.username = "Guest";
    }
    //Parameterized Constructor
    public User(String username) {
        this.username = username;
    }
    //Copy Constructor
    public User(User other) {
        this.username = new String(other.username);
    }
    //Encapsulation
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        return "User: " + username;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return username.equals(other.username);
    }
}
