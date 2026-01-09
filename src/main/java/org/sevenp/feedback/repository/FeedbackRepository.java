package org.sevenp.feedback.repository;

import org.sevenp.feedback.entity.FeedbackDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackDAO, UUID> {
}
