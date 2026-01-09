package org.sevenp.feedback.entity;
import jakarta.persistence.*;


import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class FeedbackDAO {


    @Id
    private UUID id;
    private String message;
    private String author;
    private int helpfulCount;
}
