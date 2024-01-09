package kz.App.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column
    private String text;
//    @Column(columnDefinition = "BOOLEAN")
    private Boolean correct;

//    @ManyToOne
//    @JoinColumn(name = "question_id")
//    private Question question;

    public Answer() {
    }

    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getCorrect() {
        return correct != null ? correct : false; // If correct is null, treat it as false
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}