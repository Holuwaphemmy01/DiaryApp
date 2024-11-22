package myDiaryApp.model;

import java.util.List;

public class Diary {
    private String userName;
    private String password;
    private boolean isLocked = true;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    @Override
    public String toString() {
        return "Diary{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", isLocked=" + isLocked +
                '}';
    }
}
