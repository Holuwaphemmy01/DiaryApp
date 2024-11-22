package myDiaryApp.Dtos.response;

import myDiaryApp.model.Entry;

import java.util.List;

public class DiaryLoginResponse {
    private String userName;
    private boolean isLocked;
    private List<Entry> entries;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }



}
