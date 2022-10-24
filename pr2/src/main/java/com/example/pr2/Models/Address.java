package com.example.pr2.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Ввод обязателен")
    @Size(min = 1, max = 200, message = "От 1 до 200 символов")
    private String addreses;

    @OneToOne(optional = true, mappedBy = "address")
    private University university;

    public Address(Long id, String addreses, University university) {
        this.id = id;
        this.addreses = addreses;
        this.university = university;
    }

    public Address() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddreses() {
        return addreses;
    }

    public void setAddreses(String addreses) {
        this.addreses = addreses;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
