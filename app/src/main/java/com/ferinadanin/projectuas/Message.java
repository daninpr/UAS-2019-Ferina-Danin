package com.ferinadanin.projectuas;

public class Message {
    private String title, message, name;

    public Message(){}

    public Message(String title, String message, String name) {
        this.title = title;
        this.message = message;
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }
}
