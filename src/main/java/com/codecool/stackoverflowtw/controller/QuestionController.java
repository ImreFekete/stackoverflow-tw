package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionWithAnswersDTO;
import com.codecool.stackoverflowtw.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public List<QuestionDTO> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public QuestionDTO getQuestionById(@PathVariable int id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/{id}/answers")
    public QuestionWithAnswersDTO getQuestionWithAnswersById(@PathVariable int id) {
        return questionService.getQuestionWithAnswersById(id);
    }

    @PostMapping("/")
    public int addNewQuestion(@RequestBody NewQuestionDTO question) {
        return questionService.addNewQuestion(question);
    }

    @PostMapping("/{id}/add-new-answer")
    public int addNewAnswer(@RequestBody NewAnswerDTO answer, @PathVariable int id) {
        return questionService.addNewAnswer(answer, id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteQuestionById(@PathVariable int id) {
        return questionService.deleteQuestionById(id);
    }
    @DeleteMapping("/{questionId}/answers/{answerId}")
    public boolean deleteAnswerById(@PathVariable int questionId, @PathVariable int answerId) {
        return questionService.deleteAnswerById(questionId, answerId);
    }
}
