package kz.App.controller;

import kz.App.entity.Question;
import kz.App.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
}
