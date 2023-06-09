package service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import com.example.coursework2.domain.Question;
import com.example.coursework2.exception.IsNotUniqueException;
import com.example.coursework2.exception.QuestionsDataIsNullException;
import com.example.coursework2.service.JavaQuestionsService;
import com.example.coursework2.service.QuestionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.HashSet;

public class JavaQuestionServiceTest {
    private final QuestionService questionService = new JavaQuestionsService();

    @BeforeEach
    public void beforeEach() {
        questionService.add(new Question("Q1", "A1"));
        questionService.add(new Question("Q2", "A2"));
        questionService.add(new Question("Q3", "A3"));
    }


    @AfterEach
    public void afterEach() {

        new HashSet<>(questionService.getAll()).forEach(questionService::remove);
    }


    @Test
    public void add1Test() {
        int beforeCount = questionService.getAll().size();
        Question question = new Question("Q4", "A4");

        assertThat(questionService.add(question))
                .isEqualTo(question)
                .isIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(beforeCount + 1);
    }

    @Test
    public void add2Test() {
        int beforeCount = questionService.getAll().size();
        Question question = new Question("Q4", "A4");

        assertThat(questionService.add("Q4", "A4"))
                .isEqualTo(question)
                .isIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(beforeCount + 1);
    }

    @Test
    public void add1NegativeTest() {
        Question question = new Question("Q1", "A1");

        assertThatExceptionOfType(IsNotUniqueException.class)
                .isThrownBy(() -> questionService.add(question));
    }

    @Test
    public void removeTest(){
        int beforeCount = questionService.getAll().size();
        Question question = new Question("Q1", "A1");
        assertThat(questionService.remove(question))
                .isEqualTo(question)
                .isNotIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(beforeCount - 1);
    }

    @Test
    public void removeNegativeTest() {
        assertThatExceptionOfType(QuestionsDataIsNullException.class)
                .isThrownBy(() -> questionService.remove(new Question("Q4", "A4")));
    }
    @Test
    public void getAllTest() {
        assertThat(questionService.getAll())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("Q1", "A1"),
                        new Question("Q2", "A2"),
                        new Question("Q3", "A3")
                );
    }

}
