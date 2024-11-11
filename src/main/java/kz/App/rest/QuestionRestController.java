package kz.App.rest;

import kz.App.entity.Answer;
import kz.App.entity.Author;
import kz.App.entity.Question;
import kz.App.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class QuestionRestController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/start")
    public String sendData() {
        // Some logic to fetch or generate data
        String data = "Hello from the server!";
        return data;
    }


    @GetMapping(value = "/quiz")
    public List<Question> startQuiz (){
        List<Question> questions = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Lion", false));
        answers.add(new Answer("Zebra", false));
        answers.add(new Answer("Elephant", true));
        answers.add(new Answer("Wolf", false));
        answers.add(new Answer("Monkey", false));

        Question question1 = new Question("Biggest animal ?",answers,new Author("Admin", "Admin"));
        questions.add(question1);
//        model.addAttribute("questions",selectQuestion(3));
//        return selectQuestion(1);

        return questions;
    }

    private List<Question> selectQuestion(int number){
        Random random = new Random();
        List<Question> questionList = questionService.getAllQuestions();
        int size  = questionList.size();
        List<Question> quizQuestions = new ArrayList<>();
        int[] check = new int[number];
        int randomNumber;
        for (int i = 0; i < number; i++){
            while(true){
                randomNumber = random.nextInt(size-1);
                if (containsNumber(check, randomNumber)){
                    check[i] = randomNumber;
                    break;}
            }
            quizQuestions.add(questionList.get(randomNumber));
        }
        return quizQuestions;
    }

    private boolean containsNumber(int[] array, int target) {
        for (int num : array) {if (num == target){return false;}}
        return true;
    }
}


