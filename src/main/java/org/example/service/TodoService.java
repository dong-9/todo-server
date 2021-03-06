package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoEntity add(TodoRequest request){
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(request.getTitle());
        todoEntity.setOrder(request.getOrder());
        todoEntity.setCompleted(request.getCompleted());

        return todoRepository.save(todoEntity);
    }

    public TodoEntity searchById(Long id){
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<TodoEntity> searchAll(){
        return todoRepository.findAll();
    }

    public TodoEntity updateById(Long id, TodoRequest request){
        TodoEntity entity = searchById(id);
        if(request.getTitle() != null){
            entity.setTitle(request.getTitle());
        }
        if(request.getOrder() != null){
            entity.setOrder(request.getOrder());
        }
        if(request.getCompleted() != null){
            entity.setCompleted(request.getCompleted());
        }
        return todoRepository.save(entity);
    }

    public void deleteById(Long id){
        todoRepository.deleteById(id);
    }

    public void deleteAll(){
        todoRepository.deleteAll();
    }
}
