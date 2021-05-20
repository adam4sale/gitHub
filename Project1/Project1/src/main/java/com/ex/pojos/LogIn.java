package com.ex.pojos;

/**
 * This class object is used by the User to log into the website...
 */
public class LogIn {
    //vars
    private String username; //Picard1
    private String password; //Wolf359
    private String id;//000000001

    //getters and setters
    public String getPassword() { return password; }

    public String getUsername() { return username; }

    public String getId() {return id;}

    public void setUsername(String username){ this.username = username; }

    public void setPassword(String password){ this.password = password; }

    public void setId(String id) { this.id = id; }

    //Constructor
    public LogIn(){}

    public LogIn(String username, String password, String id){
        this.username = username;
        this.password = password;
        this.id = id;
    }

    @Override
    public String toString() {
        return "LogIn{" +
                "userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
