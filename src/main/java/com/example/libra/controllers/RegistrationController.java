package com.example.libra.controllers;

import com.example.libra.domain.User;
import com.example.libra.servise.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {
    private final UserSevice userSevice;

    public RegistrationController(UserSevice userSevice) {
        this.userSevice = userSevice;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2") String passwordConfirm,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        boolean isConfirmEmpty =StringUtils.isEmpty(passwordConfirm);
        if(isConfirmEmpty){
            model.addAttribute("password2Error","Необходимо повторить пароль!");
        }

        if(user.getUsername()!=null){
            model.addAttribute("username",user.getUsername());
        }

        if(user.getPassword()!=null){
            model.addAttribute("pass",user.getPassword());
        }

        if(user.getEmail()!=null){
            model.addAttribute("email",user.getEmail());
        }

        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Пароли не совпадают!");
        }

        if (isConfirmEmpty || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "registration";
        }

        if (!userSevice.addUser(user)) {
            model.addAttribute("usernameError", "Такой пользователь уже есть");
            return "registration";
        }

        return "redirect:/login";
    }

}