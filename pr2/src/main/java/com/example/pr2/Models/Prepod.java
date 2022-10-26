package com.example.pr2.Models;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.websocket.OnMessage;
import java.util.Collection;

@Entity
@Table(name = "prepod")
public class Prepod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 100, message = "От 1 до 100 символов")
    private String surname, name;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 250, message = "От 1 до 250 символов")
    private String predmets;

    @NotNull(message = "Не может быть пустым")
    @DecimalMax("200000.0") @DecimalMin("0.0")
    private Double salary;

    @NotNull(message = "Не может быть пустым")
    @Range(min = 0, max = 300, message = "Диапазон от 0 до 300")
    private Integer OpeningHours;

    @OneToMany(mappedBy = "prepod", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Post> post;

    public Prepod(Long id, String surname, String name, String predmets, Double salary, Integer openingHours, Collection<Post> post) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.predmets = predmets;
        this.salary = salary;
        OpeningHours = openingHours;
        this.post = post;
    }

    public Prepod() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPredmets() {
        return predmets;
    }

    public void setPredmets(String predmets) {
        this.predmets = predmets;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getOpeningHours() {
        return OpeningHours;
    }

    public void setOpeningHours(Integer openingHours) {
        OpeningHours = openingHours;
    }

    public Collection<Post> getPost() {
        return post;
    }

    public void setPost(Collection<Post> post) {
        this.post = post;
    }
}
