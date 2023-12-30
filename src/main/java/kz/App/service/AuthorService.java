package kz.App.service;

import kz.App.entity.Author;
import kz.App.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository repository;

    @Autowired
    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public void addAuthor(Author author){
        repository.save(author);
    }

    public List<Author> getAllAuthors(){
        return repository.findAll();
    }

}
