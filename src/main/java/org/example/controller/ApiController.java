package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.model.AccountRequest;
import org.example.model.UserRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController                // 해당 Class는 REST API를 처리하는 Controller
@RequestMapping("/api")     // RequestMapping URI를 지정해주는 어노테이션
public class ApiController {

    @GetMapping(path = "/hello")
    public String hello(){
        return "Hello !";
    }

    @GetMapping("/path-variable/{name}")
    public String pathVariable(@PathVariable String name){
//    public String pathVariable(@PathVariable(name = "name") String pathName, String name){ 변수 이름을 일치시킬수 없을때
        log.info("PathVariable : {}", name);
        return name;
    }

    @GetMapping("/query-param01")
    public String queryParam01(@RequestParam Map<String, String> queryParam){
        StringBuilder builder = new StringBuilder();
        queryParam.entrySet().forEach(entry -> {
           log.info(entry.getKey());
           log.info(entry.getValue());
           builder.append(entry.getKey() +" = " +  entry.getValue() +"\n");
        });
        return builder.toString();
    }

    @GetMapping("/query-param02")
    public String queryParam02(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam int age
    ){
        return name + " " + email + " " + age;
    }

    @GetMapping("/query-param03")
    public String queryParma03(UserRequest request){
        log.info("User {}: ", request);
        return request.toString();
    }

    @PostMapping("/post")
    public void post(@RequestBody AccountRequest request){
        log.info("Request: {}", request);
    }
}
