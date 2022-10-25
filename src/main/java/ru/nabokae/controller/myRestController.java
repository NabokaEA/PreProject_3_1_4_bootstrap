package ru.nabokae.controller;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.nabokae.entity.User;
import ru.nabokae.service.UserServiceImpl;
import ru.nabokae.util.UserErrorResponse;
import ru.nabokae.util.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class myRestController {
    private final UserServiceImpl userService;

    @Autowired
    public myRestController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userService.findAllByOrderByIdAsc();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id) {
        Optional<User> user = userService.findById(id);
        return user.orElseThrow(UserNotFoundException::new);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse("User with this ID not found",
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

