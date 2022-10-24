package com.example.pr2.Models;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.websocket.OnMessage;

@Entity
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

    public Prepod(String Surname, String Name, Double salary, String Predmets, Integer OpeningHours) {
        this.surname = Surname;
        this.name = Name;
        this.salary = salary;
        this.predmets = Predmets;
        this.OpeningHours = OpeningHours;
    }

    public Prepod() {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
