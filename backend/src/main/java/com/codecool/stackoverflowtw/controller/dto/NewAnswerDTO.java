package com.codecool.stackoverflowtw.controller.dto;

import java.time.LocalDateTime;

public record NewAnswerDTO(String text, LocalDateTime date, String userID) {
}
