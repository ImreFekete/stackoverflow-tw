package com.codecool.stackoverflowtw.controller.dto;


import com.codecool.stackoverflowtw.dao.model.Answer;

import java.time.LocalDateTime;
import java.util.List;

public record QuestionWithAnswersDTO(int id, String title, String description, LocalDateTime created, List<Answer> answers, String username) {
}
