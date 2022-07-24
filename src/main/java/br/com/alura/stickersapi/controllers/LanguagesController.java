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
                    String title = request.getTitle();
                    String image = request.getImage();
                    String ranking = request.getRanking();

                    if (title == null) {
                        title = obj.getTitle();
                    }
                    if (image == null) {
                        image = obj.getImage();
                    }
                    if (ranking == null) {
                        ranking = obj.getRanking();
                    }
                    obj.setTitle(title);
                    obj.setImage(image);
                    obj.setRanking(ranking);
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
