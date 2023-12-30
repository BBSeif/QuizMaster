package kz.App.controller;

import kz.App.entity.Question;
import kz.App.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService qService;

//    @GetMapping("/")
//    public  String mainPage(){
//        return "Hello Guys!";
//    }

    @GetMapping("/getQuestion")
    public String getQuestion(@PathVariable Long id, Model model) {
        Question question = qService.getQuestionById(id);
        model.addAttribute("questions",question);
        return "QuestionList";
    }

    @GetMapping("/getAllQuestions")
    public String getAllQuestions(Model model) {
        List<Question> questionList = qService.getAllQuestions();
        model.addAttribute("questions",questionList);
        return "QuestionList";
    }

    @GetMapping("/add")
    public String toAddQuestion(Model model){
        Question question = new Question();
        model.addAttribute(question);
        return"AddQuestion";
    }


    /**
     * add question button
     *
     * @param question
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param ans
     * @param model
     * @return
     */
    @PostMapping(value = "/addQuestion")
    public String addQuestion(@RequestParam("question") String question,
                              @RequestParam("a") String a,
                              @RequestParam("b") String b,
                              @RequestParam("c") String c,
                              @RequestParam("d") String d,
                              @RequestParam("e") String e,
                              @RequestParam("ans") String ans,
                              Model model) {
        Question newQuestion = new Question(question, a, b, c, d, e, ans);
        qService.addQuestion(newQuestion);

        model.addAttribute("message", "Question is added!");

        return "AddQuestion";
    }

    @GetMapping(value = "/quiz")
    public String startQuiz (Model model){
        model.addAttribute("questions",selectQuestion(5));

        return "Quiz1";
    }

    private List<Question> selectQuestion(int number){
        Random random = new Random();
        List<Question> questionList = qService.getAllQuestions();
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
