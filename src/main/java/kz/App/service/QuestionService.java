package kz.App.service;

import kz.App.entity.Question;
import kz.App.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository repository;

    @Autowired
    public  QuestionService(QuestionRepository repository){
        this.repository = repository;
    }

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


//    public List<Question> getRandomForQuiz(){
//
//
//    }
//    public Connection connectToDB(){
//        Connection connection = null;
//        try {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection("jdbc:postgresql://localhost:6171/"+databaseProperties.getUrl(),
//                    databaseProperties.getUserName(),
//                    databaseProperties.getPassword());
//            if( connection != null){
//                System.out.println("Connection established");
//            } else {
//                System.out.println("Connection failed");
//            }
//        } catch (Exception e){
//            System.out.println(e);
//        }
//        return connection;
//    }
//
//    public void createTable(Connection connection, String tableName){
//        Statement statement;
//        try {
//            String query = "CREATE TABLE IF NOT EXISTS " + tableName + " (questionId SERIAL, question VARCHAR(300), a VARCHAR(100), b VARCHAR(100), c VARCHAR(100), d VARCHAR(100), e VARCHAR(100), ans VARCHAR(10), PRIMARY KEY (questionId));";
//            System.out.println("Table created");
//        }catch (Exception e){
//            System.out.println(e);
//        }
//    }

}
