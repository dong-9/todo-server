package org.example.controller;

import org.example.model.UserRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @GetMapping("/main")
    public String main(){
        return "main.html";
    }


    @ResponseBody
    @GetMapping("/user")
    public UserRequest user(){
        return UserRequest.builder()
                .name("Name")
                .build();
    }
}
