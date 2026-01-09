package org.sevenp.feedback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FeedbackNotFoundException extends RuntimeException {

    public FeedbackNotFoundException(UUID id) {
        super("Feedback not found with id: " + id);
    }
}
