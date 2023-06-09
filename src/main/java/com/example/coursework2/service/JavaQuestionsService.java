package com.example.coursework2.service;


import com.example.coursework2.domain.Question;
import com.example.coursework2.exception.IsNotUniqueException;
import com.example.coursework2.exception.QuestionAreEmptyException;
import com.example.coursework2.exception.QuestionsDataIsNullException;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class JavaQuestionsService implements QuestionService{
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {

        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
            throw new IsNotUniqueException();
        }
        return question;

    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new QuestionsDataIsNullException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new QuestionAreEmptyException();
        }

        return new ArrayList<>(questions).get(random.nextInt(questions.size()));
    }
}
