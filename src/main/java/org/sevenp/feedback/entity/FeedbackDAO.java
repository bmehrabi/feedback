package org.sevenp.feedback.entity;
import jakarta.persistence.*;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.sevenp.feedback.DTO.FeedbackCreateDTO;
import org.sevenp.feedback.DTO.FeedbackDTO;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
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

    public FeedbackDAO(FeedbackCreateDTO feedbackCreateDTO) {
        this.id = UUID.randomUUID();
        this.message = feedbackCreateDTO.getMessage();
        this.author = feedbackCreateDTO.getAuthor();
        this.helpfulCount = 0;
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
