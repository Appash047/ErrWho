package com.ErrWho.exception;

public class PostNotSaved extends BadDataException {

    public PostNotSaved(String message, String redirectUrl) {
        super(message, redirectUrl);
    }

}
