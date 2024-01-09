package kz.App.service;

import kz.App.entity.Question;
import kz.App.repository.AnswerRepository;
import kz.App.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository repository;
    private final AnswerRepository answerRepository;

    @Autowired
    public  QuestionService(QuestionRepository repository, AnswerRepository answerRepository){
        this.repository = repository;
        this.answerRepository = answerRepository;
    }

    @Transactional
    public void addQuestion(Question question){
        repository.save(question);
    }

    public Question getQuestionById(Long id){
        Optional<Question> optionalQuestion = repository.findById(id);

        return optionalQuestion.get();
    }

    public List<Question> getAllQuestions(){
        return repository.findAll();
    }

}
