package kz.App.controller;

import kz.App.entity.Question;
import kz.App.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
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
    public String toAddQuestion(){
        return"CreatingTest";
    }

    @PostMapping ("/addQuestion")
    public String addQuestion(@RequestBody Map<String, String> request, Model model) {
        String Question = request.get("Question");
        String a = request.get("a");
        String b = request.get("b");
        String c = request.get("c");
        String d = request.get("d");
        String e = request.get("e");
        String ans = request.get("ans");

        Question newQuestion = new Question(Question, a, b, c, d, e, ans);
        qService.addQuestion(newQuestion);

        model.addAttribute("Question is added!");

        return "AddQuestion";
    }
}
