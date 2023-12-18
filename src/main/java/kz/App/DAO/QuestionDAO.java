package kz.App.DAO;

import kz.App.entity.Question;

import java.util.List;

public interface QuestionDAO {
    void save(Question question );

    Question findBiId(int Id);

    List<Question> findAll();

}
