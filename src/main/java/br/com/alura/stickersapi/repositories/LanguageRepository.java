package br.com.alura.stickersapi.repositories;

import br.com.alura.stickersapi.models.LanguageModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LanguageRepository extends MongoRepository<LanguageModel, String> {
}
