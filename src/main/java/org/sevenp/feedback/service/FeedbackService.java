package org.sevenp.feedback.service;

import org.sevenp.feedback.DTO.FeedbackCreateDTO;
import org.sevenp.feedback.DTO.FeedbackDTO;
import org.sevenp.feedback.entity.FeedbackDAO;
import org.sevenp.feedback.exception.FeedbackNotFoundException;
import org.sevenp.feedback.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeedbackService {
    private final FeedbackRepository repository;

    public FeedbackService(FeedbackRepository repository) {
        this.repository = repository;
    }

    public FeedbackDTO create(FeedbackCreateDTO feedbackCreateDTO) {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        feedbackDAO.setId(UUID.randomUUID());
        feedbackDAO.setMessage(feedbackCreateDTO.getMessage());
        feedbackDAO.setAuthor(feedbackCreateDTO.getAuthor());
        feedbackDAO.setHelpfulCount(0);

        FeedbackDAO created = repository.save(feedbackDAO);

        return created.toDTO();
    }

    public FeedbackDTO update(FeedbackDTO feedbackDTO) {
        FeedbackDAO feedbackDAO = new FeedbackDAO(feedbackDTO);
        feedbackDAO.setId(feedbackDTO.getId());
        feedbackDAO.setMessage(feedbackDTO.getMessage());
        feedbackDAO.setAuthor(feedbackDTO.getAuthor());
        feedbackDAO.setHelpfulCount(feedbackDTO.getHelpfulCount());

        FeedbackDAO updated = repository.save(feedbackDAO);

        return updated.toDTO();
    }

    public List<FeedbackDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(FeedbackDAO::toDTO)
                .toList();
    }

    public FeedbackDTO findById(UUID id) {
        Optional<FeedbackDAO> feedback = repository.findById(id);
        if (feedback.isPresent()) {
            return feedback.get().toDTO();
        }
        throw new FeedbackNotFoundException(id);
    }

    public FeedbackDTO markAsHelpful(UUID id) {
        FeedbackDTO dto = findById(id);

        dto.setHelpfulCount(dto.getHelpfulCount() + 1);

        return update(dto);
    }
}
