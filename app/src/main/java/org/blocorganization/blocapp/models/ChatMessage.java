package org.blocorganization.blocapp.models;

public class ChatMessage {

    public String message;
    public final Boolean left;

    public ChatMessage(boolean left, String message) {

        super();
        this.left = left;
        this.message = message;
    }
}
