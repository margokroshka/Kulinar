package com.tms.kulinar.exception;

public class FeedbackNotFoundException extends Exception{
    private int id;

    public FeedbackNotFoundException(int id) {
        this.id=id;
    }

    @Override
    public String toString() {
        return "Feedback with id=" + id + "Not Found :(";
    }
}
