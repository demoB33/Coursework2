package com.example.coursework2.service;

import com.example.coursework2.domain.Question;
import com.example.coursework2.exception.IncorrectParamException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


@Service
public class ExaminerServiceImpl implements ExaminerService {
    Random random = new Random();
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {

        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0 || amount > questionService.getAll().size()){
            throw new IncorrectParamException();
    }
    Set<Question> result = new HashSet<>();
    while (result.size() < amount) {
        result.add(questionService.getRandomQuestion());
    }
    return result;
}

}
