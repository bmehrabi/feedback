package org.sevenp.feedback.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class FeedbackDTO {

    private UUID id;
    //TODO: this should be required
    private String message;
    //TODO: this should be optional
    private String author;
    private int helpfulCount;
}
