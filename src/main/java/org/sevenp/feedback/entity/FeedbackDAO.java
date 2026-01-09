package org.sevenp.feedback.entity;
import jakarta.persistence.*;


import lombok.Data;
import org.sevenp.feedback.DTO.FeedbackDTO;

import java.util.UUID;

@Entity
@Data
public class FeedbackDAO {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String message;

    private String author;

    @Column(nullable = false)
    private int helpfulCount;

    public FeedbackDAO(FeedbackDTO feedback) {
        this.id = feedback.getId();
        this.message = feedback.getMessage();
        this.author = feedback.getAuthor();
        this.helpfulCount = feedback.getHelpfulCount();
    }

    public FeedbackDAO() {

    }

    public FeedbackDTO toDTO() {
        FeedbackDTO dto = new FeedbackDTO();
        dto.setId(this.id);
        dto.setMessage(this.message);
        dto.setAuthor(this.author);
        dto.setHelpfulCount(this.helpfulCount);

        return dto;
    }
}
