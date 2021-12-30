package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.model.TodoResponse;
import org.example.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService service;

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request){
        log.info("CREATE");
        if(ObjectUtils.isEmpty(request)) {
            return ResponseEntity.badRequest().build();
        }

        if(ObjectUtils.isEmpty(request.getOrder())){
            request.setOrder(0L);
        }

        if(ObjectUtils.isEmpty(request.getCompleted())){
            request.setCompleted(false);
        }

        TodoEntity result = service.add(request);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id){
        log.info("READ ONE");
        TodoEntity result = service.searchById(id);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> readAll(){
        log.info("READ ALL");
        List<TodoEntity> result = service.searchAll();
        List<TodoResponse> responses = result.stream().map(TodoResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request){
        log.info("UPDATE");
        TodoEntity result = service.updateById(id, request);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id){
        log.info("DELETE ONE");
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll(){
        log.info("DELETE ALL");
        service.deleteAll();
        return ResponseEntity.ok().build();
    }
}
