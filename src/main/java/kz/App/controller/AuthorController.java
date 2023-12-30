package kz.App.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import kz.App.entity.Author;
import kz.App.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/getAuthors")
    public String getAllAuthors(Model model){
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors",authors);
        return "AuthorList";
    }
}
