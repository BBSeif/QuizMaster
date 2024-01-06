package kz.App.service;

import kz.App.entity.Author;
import kz.App.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository repository;

    @Autowired
    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void addAuthor(Author author){
        repository.save(author);
    }

    public List<Author> getAllAuthors(){
        return repository.findAll();
    }

    public Author getAuthorById(long id){
        Optional<Author> byId = repository.findById(id);
        return byId.get();
    }

}
