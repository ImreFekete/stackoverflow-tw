package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.List;

public interface QuestionsDAO {

    List<Question> getAll();
    Question getQuestionById(int id);
    int addNewQuestion(NewQuestionDTO newQuestion);
    boolean deleteQuestionByID(int id);
}
