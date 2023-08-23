package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDateTime;

public record Answer(int id, String text, LocalDateTime date, String username) {
}
