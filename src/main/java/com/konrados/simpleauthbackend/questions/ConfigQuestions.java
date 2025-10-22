package com.konrados.simpleauthbackend.questions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.konrados.simpleauthbackend.questions.repository.QuestionsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class ConfigQuestions implements CommandLineRunner {

    private final QuestionsRepository repo;
    private final ObjectMapper mapper;

    public ConfigQuestions(QuestionsRepository repo, ObjectMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repo.count() > 0) {
            System.out.println("ℹ️ Pytania już są w bazie — pomijam import startowy.");
            return;
        }

        try (InputStream in = getClass().getResourceAsStream("/data/questions.json")) {
            if (in == null) {
                System.err.println("❌ Brak pliku /data/questions.json w resources!");
                return;
            }
            List<Questions> list = mapper.readValue(in, new TypeReference<List<Questions>>() {});
            repo.saveAll(list);
            System.out.println("✅ Załadowano " + list.size() + " pytań z JSON do bazy.");
        }
    }
}
