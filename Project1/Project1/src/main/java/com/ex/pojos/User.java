package com.ex.pojos;

public class User {
    //Vars
    private String name;//Jordi LaForge
    private String position;//Chief Engineer
    private String rank;//Lieutenant Commander
    private String id;



    //Getters and Setters
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPosition() { return position; }

    public String getRank() { return rank; }

    public void setRank(String rank) { this.rank = rank; }

    public void setPosition(String position) { this.position = position; }

    //Returns a blank document
    public String getId() { return id; }

    public void setId(String id) { this.id = id; }


    //Constructors
    public User(String name, String rank, String position){
        this.name = name;
        this.position = position;
        this.rank = rank;
        //this.id = id;
    }

    public User(){}//No args

    /**
     * Override java toString() for User
     * @return String of Object
     */
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }

    //
}
