package com.konrados.simpleauthbackend.questions.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.konrados.simpleauthbackend.questions.Questions;
import com.konrados.simpleauthbackend.questions.repository.QuestionsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*") // dla Androida/emulatora
public class QuestionsController {

    private final QuestionsRepository repo;
    private final ObjectMapper mapper;

    public QuestionsController(QuestionsRepository repo, ObjectMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @GetMapping("/api/getByCategory")
    public List<Questions> getByCategory(@RequestParam String category) {
        return repo.findByCategory(category);
    }

    // ✅ Android: GET /getQuestions (Retrofit)
    @GetMapping("/api/getQuestions")
    public List<Questions> getAll() {
        return repo.findAll();
    }

    // (Opcjonalnie) GET bez sufiksu: /api/questions
    @GetMapping
    public List<Questions> getAllShort() {
        return repo.findAll();
    }

    // Ręczny re-import z pliku JSON
    @PostMapping("/api/import")
    public ResponseEntity<String> importQuestions() {
        try (InputStream in = getClass().getResourceAsStream("/data/questions.json")) {
            if (in == null) {
                return ResponseEntity.badRequest().body("Brak pliku /data/questions.json");
            }
            List<Questions> list = mapper.readValue(in, new TypeReference<List<Questions>>() {});
            repo.deleteAll();
            repo.saveAll(list);
            return ResponseEntity.ok("Zaimportowano: " + list.size() + " pytań");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Błąd importu: " + e.getMessage());
        }
    }
}
