package be.hanagami.springframework.spring6webapp.controllers;

import be.hanagami.springframework.spring6webapp.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String authors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors";
    }
}
