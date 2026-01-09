package org.sevenp.feedback.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class FeedbackDTO {

    private UUID id;
    private String message;
    private String author;
    private int helpfulCount;
}
