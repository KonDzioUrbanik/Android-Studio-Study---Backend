package com.konrados.simpleauthbackend.questions.repository;

import com.konrados.simpleauthbackend.questions.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {}
