package kz.App.controller;

import kz.App.entity.Question;
import kz.App.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService qService;

    @GetMapping("/")
    public  String mainPage(){
        return "Hello Guys!";
    }

    @GetMapping("/getQwestion")
    public Question getQuestion(@PathVariable Long id) {
        return qService.getQuestionById(id);
    }

    @PostMapping ("/add")
    public void addQuestion(@RequestBody Map<String, String> request) {
        String Question = request.get("Question");
        String a = request.get("a");
        String b = request.get("b");
        String c = request.get("c");
        String d = request.get("d");
        String e = request.get("e");
        String ans = request.get("ans");

        Question newQuestion = new Question(Question, a, b, c, d, e, ans);
        qService.addQuestion(newQuestion);

        System.out.println("Question is added!");
    }


    public void addQuestion(@RequestParam String Question,
                            @RequestParam String a,
                            @RequestParam String b,
                            @RequestParam String c,
                            @RequestParam String d,
                            @RequestParam String e,
                            @RequestParam String ans){
        Question newQuestion = new Question(Question,a,b,c,d,e,ans);
        qService.addQuestion(newQuestion);
        System.out.println("Question is added!");
    }


}
