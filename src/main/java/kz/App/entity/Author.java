package kz.App.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "grade")
    private String grade;


    @OneToMany(mappedBy = "author" , cascade = CascadeType.ALL)
    private List<Question> questions;

    public Author(){}


    public Author(String name, String grade) {
        this.name = name;
        this.grade = grade;
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


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
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
        return "Аты-жөні : " + name +
                ", сыныбы : " + grade;
    }
}
