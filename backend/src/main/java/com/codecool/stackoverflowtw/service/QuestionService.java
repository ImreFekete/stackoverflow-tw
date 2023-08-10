package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionWithAnswersDTO;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    public List<QuestionDTO> getAllQuestions(String sortBy, String orderBy) {

        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questionsDAO.getAll(sortBy, orderBy)) {
            questionDTOS.add(new QuestionDTO(question.id(), question.title(), question.text(), question.date(), question.answerCount()));
        }
        return questionDTOS;
    }

    public QuestionDTO getQuestionById(int id) {
        Question question = questionsDAO.getQuestionById(id);
        return new QuestionDTO(question.id(), question.title(), question.text(), question.date(), question.answerCount());
    }

    public boolean deleteQuestionById(int id) {
        return questionsDAO.deleteQuestionById(id);
    }
    public boolean deleteAnswerById(int questionId, int answerId){ return questionsDAO.deleteAnswerById(questionId, answerId);}

    public int addNewQuestion(NewQuestionDTO question) {
        return questionsDAO.addNewQuestion(question);
    }

    public int addNewAnswer(NewAnswerDTO answerDTO, int id){
        return questionsDAO.addNewAnswer(answerDTO, id);
    }

    public QuestionWithAnswersDTO getQuestionWithAnswersById(int id){
        Question question = questionsDAO.getQuestionById(id);
        List<Answer> answers = questionsDAO.getQAnswersByQuestionId(id);
        return new QuestionWithAnswersDTO(question.id(), question.title(), question.text(), question.date(), answers);
    }
}
