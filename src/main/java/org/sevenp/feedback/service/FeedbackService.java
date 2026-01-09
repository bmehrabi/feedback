package org.sevenp.feedback.service;

import org.sevenp.feedback.DTO.FeedbackDTO;
import org.sevenp.feedback.entity.FeedbackDAO;
import org.sevenp.feedback.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FeedbackService {
    private final FeedbackRepository repository;

    public FeedbackService(FeedbackRepository repository) {
        this.repository = repository;
    }

    public FeedbackDTO save(FeedbackDTO feedbackDTO) {
        FeedbackDAO feedbackDAO = new FeedbackDAO(feedbackDTO);
        feedbackDAO.setId(UUID.randomUUID());
        feedbackDAO.setHelpfulCount(0);

        FeedbackDAO created = repository.save(feedbackDAO);

        return created.toDTO();
    }
}
