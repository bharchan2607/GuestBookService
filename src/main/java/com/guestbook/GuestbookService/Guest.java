package com.guestbook.GuestbookService;

public class Guest {
    private String name;
    private String comments;

    public Guest(String name, String comments) {
        this.name = name;
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public String getComments() {
        return comments;
    }
}
