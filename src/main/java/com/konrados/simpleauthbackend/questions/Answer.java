package com.konrados.simpleauthbackend.questions;

import jakarta.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;
    private int points;

    public Answer() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
}
