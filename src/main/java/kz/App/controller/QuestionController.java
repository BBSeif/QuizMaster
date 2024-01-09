package kz.App.controller;

import kz.App.entity.Answer;
import kz.App.entity.Author;
import kz.App.entity.Question;
import kz.App.service.AuthorService;
import kz.App.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private AuthorService authorService;

//    @GetMapping("/")
//    public  String mainPage(){
//        return "Hello Guys!";
//    }

    @GetMapping("/getQuestion")
    public String getQuestion(@PathVariable Long id, Model model) {
        Question question = questionService.getQuestionById(id);
        model.addAttribute("questions",question);
        return "QuestionList";
    }

    @GetMapping("/getAllQuestions")
    public String getAllQuestions(Model model) {
        List<Question> questionList = questionService.getAllQuestions();
        model.addAttribute("questions",questionList);
        return "QuestionList";
    }

    @GetMapping("/add")
    public String toAddQuestion(Model model){
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return"AddQuestion";
    }



    @PostMapping(value = "/addQuestion")
    public String addQuestion( @RequestParam("question") String question,
                              @RequestParam("a") String a,
                               @RequestParam(name = "checkA",defaultValue = "false") boolean checkA,
                               @RequestParam("b") String b,
                               @RequestParam(name = "checkB",defaultValue = "false") boolean checkB,
                               @RequestParam("c") String c,
                               @RequestParam(name = "checkC",defaultValue = "false") boolean checkC,
                               @RequestParam("d") String d,
                               @RequestParam(name = "checkD",defaultValue = "false") boolean checkD,
                               @RequestParam("e") String e,
                               @RequestParam(name = "checkE",defaultValue = "false") boolean checkE,
                              @RequestParam("author") long authorId,
                              Model model) {

        Question newQuestion = new Question();
        newQuestion.setQuestion(question);
        List<Answer> answers = new ArrayList<>();

        answers.add(new Answer(a, checkA));
        answers.add(new Answer(b, checkB));
        answers.add(new Answer(c, checkC));
        answers.add(new Answer(d, checkD));
        answers.add(new Answer(e, checkE));

            for (Answer answer : answers) {
                if (answer.getCorrect() == null) {
                    // Checkbox not checked, set correct to false
                    answer.setCorrect(false);
                }
            }


        newQuestion.setAnswers(answers);

        Author author1 = authorService.getAuthorById(authorId);
        newQuestion.setAuthor(author1);
        questionService.addQuestion(newQuestion);

        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("message", "Question is added!");
        model.addAttribute("authors", authors );
//        model.addAttribute("question", new Question());
        return  "AddQuestion";
    }

    @GetMapping(value = "/quiz")
    public String startQuiz (Model model){
        model.addAttribute("questions",selectQuestion(3));

        return "Quiz2";
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
