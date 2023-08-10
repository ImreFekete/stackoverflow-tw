package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.List;

public interface QuestionsDAO {

    List<Question> getAll(String orderBy, String direction);
    Question getQuestionById(int id);
    List<Answer> getQAnswersByQuestionId(int id);
    int addNewQuestion(NewQuestionDTO newQuestion);
    int addNewAnswer(NewAnswerDTO newAnswer, int id);
    boolean deleteQuestionById(int id);
    boolean deleteAnswerById(int questionId, int answerId);
}
