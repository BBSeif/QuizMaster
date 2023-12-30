package kz.App.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Class")
    private String grade;

    @OneToMany(mappedBy = "author")
    private List<Question> questions;

    public Author(){}



    public Author(int id, String name, String grade, List<Question> questions) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String get_Class() {
        return grade;
    }

    public void setClass(String grade) {
        this.grade = grade;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
