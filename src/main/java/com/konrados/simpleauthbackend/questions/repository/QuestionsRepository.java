package com.konrados.simpleauthbackend.questions.repository;

import com.konrados.simpleauthbackend.questions.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    List<Questions> findByCategory(String category);
}
