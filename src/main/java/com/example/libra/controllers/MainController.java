package com.example.libra.controllers;

import com.example.libra.domain.Message;
import com.example.libra.domain.User;
import com.example.libra.reposit.MessageReposit;
import com.example.libra.servise.BookService;
import com.example.libra.servise.FileService;
import com.example.libra.servise.GenreServise;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Controller
public class MainController extends FileService {
    private final MessageReposit messageRepo;

    private final BookService bookService;

    private final GenreServise genreServise;

    @Value("${upload.path}")
    private String uploadPath;

    public MainController(MessageReposit messageRepo, BookService bookService, GenreServise genreServise) {
        this.messageRepo = messageRepo;
        this.bookService = bookService;
        this.genreServise = genreServise;
    }

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("books",bookService.find20UpdateBooks());
        model.addAttribute("firstGenres",genreServise.findTop8Genres());
        return "greeting";
    }



    @GetMapping("/main/sucses")
    public String sucs() {
        return "sucses";
    }

    @GetMapping("/main/rules")
    public String rul() {
        return "rules";
    }

    @GetMapping("/main/about")
    public String about() {
        return "about";
    }
    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages ;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        message.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        } else {
            if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));

                message.setFilename(resultFilename);
            }

            model.addAttribute("message", null);

            messageRepo.save(message);
        }

        Iterable<Message> messages = messageRepo.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }



}
