package com.geekster.todoApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class TodoController {
    @Autowired
    List<Todo> todoList;

    //create todos -post APIs

    //add a todo
    @PostMapping("todo")
    public String addTodo(@RequestBody Todo myTodo){
        todoList.add(myTodo);
        return "todo added";
    }


    //get Api


    //get all Todos:
    @GetMapping("todos")
    public List<Todo> getAllTodos(){
        return todoList;
    }


    //send a todo id :id1 and String" s1

    @PutMapping("todo/id/{id}/status")
    public String setTodoStatusById(@PathVariable Integer id,@RequestParam boolean flag){
        for(Todo todo: todoList){
            if(todo.getTodoId().equals(id)){
                todo.setTodoStatus(flag);
                return "todo: " +id+"updated to"+ flag;
            }
        }
        return "Invalid id";
    }

    @DeleteMapping("todo/id/{id}")
    public String removeToBody(@PathVariable Integer id){
        for(Todo todo: todoList){
            if(todo.getTodoId().equals(id)){
                todoList.remove(todo);
                return "todo: "+ id + " removed";
           }
        }
        return "Invalid Id";
    }


    // add a list of todos
    @PostMapping("todos")
    public String addTodos(@RequestBody List<Todo> mytodos){
        todoList.addAll(mytodos);
        return mytodos.size()+" todos are added";
    }

    //I want all the task which are undone

    @GetMapping("todos/undone")
    public List<Todo> getAllUndoneTodos() {
//        return todoList.stream().filter(todo-> !todo.isTodoStatus()).collect(Collectors.toList());
        List<Todo> undoneTodo=new ArrayList<>();
        for(Todo  todo:todoList){
            if(!todo.isTodoStatus()){
                undoneTodo.add(todo);
            }

        }
        return undoneTodo;
    }

    //delete todos by ids
    @DeleteMapping("todos/ids")
    public List<Todo> removeTodos(@RequestBody List<Integer> idList){
//        for(Todo ogtodo: todoList){
//            for(Integer id:idList){
//                if(ogtodo.getTodoId().equals(id)){
//                    todoList.remove(ogtodo);
//                }
//            }
//        }

        for(int i=0;i<todoList.size();i++){
            Todo ogtodo=todoList.get(i);
            for(int j=0;j<idList.size();j++){

                if(ogtodo.getTodoId().equals(idList.get(j))){
                    todoList.remove(ogtodo);
                }
            }
        }
        return todoList;
    }
}
