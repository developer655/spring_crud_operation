package com.example.controller;

import com.example.dto.Response;
import com.example.entity.User;
import com.example.service.UserService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    UserService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response<String> add(@Valid @RequestBody @NotNull User user) {
        String message = service.add(user);
        Response<String> response = new Response<>();
        response.setMessage(message);
        return response;
    }

    @PutMapping("/{id}")
    public Response<String> update(@RequestBody User user, @PathVariable String id) {
        String message = service.update(user, id);
        Response<String> response = new Response<>();
        response.setMessage(message);
        return response;
    }

    @PatchMapping("/{id}")
    public Response<User> fetch(@PathVariable String id) {
        User user = service.fetch(id);
        Response<User> response = new Response<>();
        response.setResponse(user);
        return response;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable String id) {
        String message = service.delete(id);
        Response<String> response = new Response<>();
        response.setMessage(message);
        return response;
    }

    @DeleteMapping("")
    public Response<String> clear() {
        String message = service.deletes();
        Response<String> response = new Response<>();
        response.setMessage(message);
        return response;
    }

    @GetMapping("/{id}")
    public Response<User> get(@PathVariable String id) {
        User user = service.get(id);
        Response<User> response = new Response<>();
        response.setResponse(user);
        return response;
    }

    @GetMapping("")
    public Response<List<User>> gets() {
        List<User> users = service.gets();
        Response<List<User>> response = new Response<>();
        response.setResponse(users);
        response.setSize(users.size());
        return response;
    }

    @GetMapping("/size")
    public Response<Long> count() {
        long size = service.count();
        Response<Long> response = new Response<>();
        response.setResponse(size);
        return response;
    }

}
