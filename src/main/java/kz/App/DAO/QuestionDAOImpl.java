package kz.App.DAO;

import jakarta.persistence.EntityManager;
import kz.App.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuestionDAOImpl implements QuestionDAO{

    private EntityManager manager;

    @Autowired
    public QuestionDAOImpl(EntityManager manager){
        this.manager = manager;
    }
    @Override
    public void save(Question question) {

    }

    @Override
    public Question findBiId(int Id) {
        return null;
    }

    @Override
    public List<Question> findAll() {
        return null;
    }
}
