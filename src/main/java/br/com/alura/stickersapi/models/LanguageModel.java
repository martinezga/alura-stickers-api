package br.com.alura.stickersapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "languages-images")
public class LanguageModel {
    @Id
    private String id;
    private String title;
    private String image;
    private String ranking;

    public LanguageModel() {
    }

    public LanguageModel(String title, String image, String ranking) {
        this.title = title;
        this.image = image;
        this.ranking = ranking;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getRanking() {
        return ranking;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }
}
