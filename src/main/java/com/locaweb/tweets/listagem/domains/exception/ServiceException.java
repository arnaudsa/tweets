package com.locaweb.tweets.listagem.domains.exception;

public class ServiceException extends RuntimeException{

    public ServiceException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}
