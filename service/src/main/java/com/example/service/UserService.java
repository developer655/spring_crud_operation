package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

//    @PostConstruct
//    public void initDB() {
//        List<User> users = IntStream.rangeClosed(1, 200).mapToObj(i -> new User(
//                String.valueOf(i),
//                "User " + i + "",
//                "user" + i + "@gmail.com",
//                i % 2 == 0 ? UserType.USER : UserType.ADMIN
//        )).toList();
//        repository.saveAll(users);
//    }

    public boolean isExists(String id) {
        return repository.existsById(id);
    }

    public String add(@NotNull User user) {
        String id = user.getId();
        if (id != null && isExists(id)) {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "User already found!");
        } else {
            repository.save(user);
            return "User create successfully!";
        }
    }

    public String update(User content, String id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!");
        } else {
            repository.save(content);
            return "User update successfully!";
        }
    }

    public User fetch(String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
    }

    public String delete(String id) {
        repository.deleteById(id);
        return "User deleted!";
    }

    public String deletes() {
        repository.deleteAll();
        return "All data deleted!";
    }

    public User get(String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
    }

    public List<User> gets() {
        if (repository != null) {
            return repository.findAll();
        }
        return new ArrayList<>();
    }

    public List<User> sort(Sort.Direction direction, String... fields) {
        Sort sort = Sort.by(direction, fields);
        return repository.findAll(sort);
    }

    public List<User> paging(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).getContent();
    }

    public List<User> specifics(List<String> ids) {
        return repository.findAllById(ids);
    }

    public long count() {
        return repository.count();
    }

}
