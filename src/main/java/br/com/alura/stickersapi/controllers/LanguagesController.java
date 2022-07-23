package br.com.alura.stickersapi.controllers;

import br.com.alura.stickersapi.models.LanguageModel;
import br.com.alura.stickersapi.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguagesController {

    @Autowired
    private LanguageRepository repository;

    // Retrieve all languages
    @GetMapping
    public List<LanguageModel> getAllLanguages() {
        return repository.findAll();
    }

    // Create language
    @PostMapping
    public LanguageModel createLanguage(@RequestBody LanguageModel request) {
        return repository.save(request);
    }

    // Update language by ID
    @PatchMapping("/{id}")
    public LanguageModel updateLanguage(@PathVariable("id") String id, @RequestBody LanguageModel request) {
        return repository.findById(id)
                .map(obj -> {
                    obj.setTitle(request.getTitle());
                    obj.setImage(request.getImage());
                    obj.setRanking(request.getRanking());
                    return repository.save(obj);
                })
                .orElseGet(() -> {
                    // TODO: implement NotFoundException
                    return new LanguageModel();
                });
    }

    // Delete language by ID
    @DeleteMapping("/{id}")
    public String deleteLanguage(@PathVariable("id") String id) {
        String message;
        try {
            repository.deleteById(id);
            message = "Successfully deleted";
        } catch (Exception e) {
            message = "Error";
        }
        return message;
    }
}
