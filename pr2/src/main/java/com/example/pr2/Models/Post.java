package com.example.pr2.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 255, message = "От 1 до 255 символов")
    private String title, anons, full_text;
    private int views;

    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Prepod prepod;

    public Post(Long id, String title, String anons, String full_text, int views, Prepod prepod) {
        this.id = id;
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
        this.views = views;
        this.prepod = prepod;
    }

    public Post() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Prepod getPrepod() {
        return prepod;
    }

    public void setPrepod(Prepod prepod) {
        this.prepod = prepod;
    }
}
