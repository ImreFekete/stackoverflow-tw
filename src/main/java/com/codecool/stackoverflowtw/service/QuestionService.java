package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    public List<QuestionDTO> getAllQuestions() {
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questionsDAO.getAll()) {
            questionDTOS.add(new QuestionDTO(question.id(), question.title(), question.text(), question.date(), question.answerCount()));
        }
        return questionDTOS;
    }

    public QuestionDTO getQuestionById(int id) {
        Question question = questionsDAO.getQuestionById(id);
        return new QuestionDTO(question.id(), question.title(), question.text(), question.date(), question.answerCount());
    }

    public boolean deleteQuestionById(int id) {
        return questionsDAO.deleteQuestionByID(id);
    }

    public int addNewQuestion(NewQuestionDTO question) {
        return questionsDAO.addNewQuestion(question);
    }
}
