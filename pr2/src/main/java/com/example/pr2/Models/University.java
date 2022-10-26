package com.example.pr2.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Поле не может быть пустым")
    @Size(min = 1, max = 255, message = "От 1 до 255 символов")
    private String titleuniversity;

    @NotNull(message = "Поле не может быть пустым")
    private Long NumbTel;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;



    @ManyToMany
    @JoinTable(name = "UniverStudent",
            joinColumns = @JoinColumn(name = "university_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> studentList;

    public University() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleuniversity() {
        return titleuniversity;
    }

    public void setTitleuniversity(String titleuniversity) {
        this.titleuniversity = titleuniversity;
    }

    public Long getNumbTel() {
        return NumbTel;
    }

    public void setNumbTel(Long numbTel) {
        NumbTel = numbTel;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public University(Long id, String titleuniversity, Long numbTel, Address address, List<Student> studentList) {
        this.id = id;
        this.titleuniversity = titleuniversity;
        NumbTel = numbTel;
        this.address = address;
        this.studentList = studentList;
    }
}
