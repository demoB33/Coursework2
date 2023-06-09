package service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;
import com.example.coursework2.domain.Question;
import com.example.coursework2.exception.IncorrectParamException;
import com.example.coursework2.service.ExaminerServiceImpl;
import com.example.coursework2.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;


    @InjectMocks
    private ExaminerServiceImpl examinerService;


    private final Collection<Question> questions = Set.of(
            new Question("Q1", "A1"),
            new Question("Q2", "A2"),
            new Question("Q3", "A3"),
            new Question("Q4", "A4"),
            new Question("Q5", "A5")
    );

    @Test
    public void getQuestionsNegativeTest() {
        when(questionService.getAll()).thenReturn(questions);

        assertThatExceptionOfType(IncorrectParamException.class)
                .isThrownBy(() -> examinerService.getQuestions(-1));
        assertThatExceptionOfType(IncorrectParamException.class)
                .isThrownBy(() -> examinerService.getQuestions(questions.size() + 1));
    }
    @Test
    public void getQuestionsTest() {
        when(questionService.getAll()).thenReturn(questions);

        when(questionService.getRandomQuestion()).thenReturn(
                new Question("Q4", "A4"),
                new Question("Q4", "A4"),
                new Question("Q5", "A5"),
                new Question("Q2", "A2")
        );

        assertThat(examinerService.getQuestions(3))
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("Q4", "A4"),
                        new Question("Q2", "A2"),
                        new Question("Q5", "A5")
                );
    }


}
