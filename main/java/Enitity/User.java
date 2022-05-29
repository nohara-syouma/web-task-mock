package Enitity;

public class User {

    private String userId;
    private String userName;
    private String price;

    public User() {
    }

    public User(String userId,String userName, String price) {
        this.userId = userId;
        this.userName = userName;
        this.price = price;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setPassword(String price) {
        this.price = price;
    }

    public String getPassword() {
        return this.price;
    }
}
