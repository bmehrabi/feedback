package org.sevenp.feedback.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sevenp.feedback.DTO.FeedbackCreateDTO;
import org.sevenp.feedback.DTO.FeedbackDTO;
import org.sevenp.feedback.entity.FeedbackDAO;
import org.sevenp.feedback.exception.FeedbackNotFoundException;
import org.sevenp.feedback.repository.FeedbackRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FeedbackServiceTest {
    @Mock
    private FeedbackRepository repository;

    @InjectMocks
    private FeedbackService service;

    @Test
    void shouldSaveFeedbackAndReturnDTO() {
        // given
        FeedbackCreateDTO inputDto = new FeedbackCreateDTO("Great!", "babak");

        FeedbackDAO savedDao = new FeedbackDAO(inputDto);
        savedDao.setId(UUID.randomUUID());
        savedDao.setHelpfulCount(0);

        when(repository.save(any(FeedbackDAO.class)))
                .thenReturn(savedDao);

        // when
        FeedbackDTO result = service.create(inputDto);

        // then
        assertNotNull(result.getId());
        assertEquals("Great!", result.getMessage());
        assertEquals(0, result.getHelpfulCount());

        verify(repository, times(1)).save(any(FeedbackDAO.class));
    }

    @Test
    void shouldUpdateFeedbackAndReturnUpdatedDTO() {
        // given
        UUID id = UUID.randomUUID();

        FeedbackDTO inputDto = new FeedbackDTO(id, "Updated message", "John", 5);

        FeedbackDAO savedDao = new FeedbackDAO(inputDto);
        savedDao.setId(id);
        savedDao.setMessage("Updated message");
        savedDao.setAuthor("John");
        savedDao.setHelpfulCount(5);

        when(repository.save(any(FeedbackDAO.class)))
                .thenReturn(savedDao);

        // when
        FeedbackDTO result = service.update(inputDto);

        // then
        assertEquals(id, result.getId());
        assertEquals("Updated message", result.getMessage());
        assertEquals("John", result.getAuthor());
        assertEquals(5, result.getHelpfulCount());

        verify(repository, times(1)).save(any(FeedbackDAO.class));
    }

    @Test
    void shouldReturnAllFeedbackAsDTOs() {
        // given
        FeedbackDAO dao1 = new FeedbackDAO();
        dao1.setId(UUID.randomUUID());
        dao1.setMessage("First");
        dao1.setHelpfulCount(1);

        FeedbackDAO dao2 = new FeedbackDAO();
        dao2.setId(UUID.randomUUID());
        dao2.setMessage("Second");
        dao2.setHelpfulCount(2);

        when(repository.findAll()).thenReturn(List.of(dao1, dao2));

        // when
        List<FeedbackDTO> result = service.findAll();

        // then
        assertEquals(2, result.size());
        assertEquals("First", result.get(0).getMessage());
        assertEquals(2, result.get(1).getHelpfulCount());

        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldReturnFeedbackWhenFoundById() {
        UUID id = UUID.randomUUID();

        FeedbackDAO dao = new FeedbackDAO();
        dao.setId(id);
        dao.setMessage("Hello");
        dao.setHelpfulCount(3);

        when(repository.findById(id)).thenReturn(Optional.of(dao));

        // when
        FeedbackDTO result = service.findById(id);

        // then
        assertEquals(id, result.getId());
        assertEquals("Hello", result.getMessage());
        assertEquals(3, result.getHelpfulCount());

        verify(repository).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenFeedbackNotFound() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        // then
        assertThrows(
                FeedbackNotFoundException.class,
                () -> service.findById(id)
        );

        verify(repository).findById(id);
    }

    @Test
    void shouldIncrementHelpfulCount() {
        UUID id = UUID.randomUUID();

        FeedbackDAO dao = new FeedbackDAO();
        dao.setId(id);
        dao.setMessage("Nice");
        dao.setHelpfulCount(2);

        // findById() path
        when(repository.findById(id)).thenReturn(Optional.of(dao));

        // update() path → save returns updated entity
        when(repository.save(any(FeedbackDAO.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // when
        FeedbackDTO result = service.markAsHelpful(id);

        // then
        assertEquals(3, result.getHelpfulCount());

        verify(repository).findById(id);
        verify(repository).save(any(FeedbackDAO.class));
    }
}
