package com.Ray.pojo;

import java.util.Date;

public class Search {
    private int id;
    private String userCode;
    private String word;
    private Date creationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Search{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", word='" + word + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
