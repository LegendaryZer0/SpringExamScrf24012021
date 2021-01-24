package ru.itis.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
@Slf4j
@Controller
public class SignInController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(){
        return "Login";
    }
    @PostMapping("/loginIn")
    public String loginIn(@RequestParam Integer id, HttpServletRequest request){
        if(userService.catchUser(id).getId()!=null){
            log.info(id.toString());
            request.getSession().setAttribute("id",userService.catchUser(id).getId());
            return "Profile";
        }
        else {
            System.out.println("theres no id");
            return "Login";
        }
    }
}
