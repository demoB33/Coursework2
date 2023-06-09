package com.example.coursework2.controller;

import com.example.coursework2.domain.Question;
import com.example.coursework2.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {
    private final QuestionService questionsService;

    public JavaQuestionController(QuestionService questionsService) {
        this.questionsService = questionsService;
    }


    @GetMapping(path = "/add")
    public Question add(@RequestParam String question,
                        @RequestParam String answer) {
        return questionsService.add(question, answer);
    }
    @GetMapping(path = "remove")
    public Question remove(@RequestParam String question,
                           @RequestParam String answer) {
        return questionsService.remove(new Question(question, answer));
    }

    @GetMapping
    public Collection<Question> getAll() {

        return questionsService.getAll();
    }


}
