package ru.practicum.shareit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SharerException extends RuntimeException {
    public SharerException(String message) {
        super(message);
    }

    public SharerException(String message, Throwable cause) {
        super(message, cause);
    }
}