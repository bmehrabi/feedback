package org.sevenp.feedback.controller;

import org.sevenp.feedback.DTO.FeedbackDTO;
import org.sevenp.feedback.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("")
    public ResponseEntity<List<FeedbackDTO>> list() {
        FeedbackDTO feedback = new FeedbackDTO();
        feedback.setId(UUID.randomUUID());
        feedback.setMessage("test message");
        feedback.setAuthor("Babak");
        feedback.setHelpfulCount(0);

        return new ResponseEntity<>(List.of(feedback), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<FeedbackDTO> create(@RequestBody FeedbackDTO feedback) {
        return new ResponseEntity<>(feedbackService.save(feedback), HttpStatus.CREATED);
    }
}
