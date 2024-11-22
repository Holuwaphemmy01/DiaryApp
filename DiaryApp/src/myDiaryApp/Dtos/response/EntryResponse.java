package myDiaryApp.Dtos.response;

import java.time.LocalDate;

public class EntryResponse {
    private LocalDate date;
    private int id;
    private String title;
    private String content;
    private String userName;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "{" +
                "date=" + date +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
