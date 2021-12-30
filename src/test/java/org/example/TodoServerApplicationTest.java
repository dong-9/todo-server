package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.UserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class TodoServerApplicationTest {

    @DisplayName(value = "Object Mapper Test")
    @Test
    void contextLoads() throws JsonProcessingException {
        var objectMapper = new ObjectMapper();

        // object -> json
        // object mapper get method 활용
        var user = new UserRequest("kim","a@gmail.com",10, "010-1111-2222");
        var json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        // json -> object
        // object mapper는 default 생성자를 필요로함
        var userObject = objectMapper.readValue(json, UserRequest.class);
        System.out.println(userObject);
    }
}