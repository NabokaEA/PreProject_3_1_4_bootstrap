package ru.nabokae.controller;

import net.bytebuddy.implementation.bytecode.Throw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.nabokae.entity.User;
import ru.nabokae.sequrity.UserDetailsImpl;
import ru.nabokae.service.UserServiceImpl;
import ru.nabokae.util.UserErrorResponse;
import ru.nabokae.util.UserNotCreatedException;
import ru.nabokae.util.UserNotFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class myRestController {
    public static final Logger logger = LoggerFactory.getLogger(myRestController.class);
    private final UserServiceImpl userService;

    @Autowired
    public myRestController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<User> getUsers() {
        logger.info("Запрошен список всех пользователей REST");
        return (List<User>) userService.findAllByOrderByIdAsc();
    }

    @GetMapping("/user")
    public User getUser() {
        logger.info("Запрошен авторизованный пользователь REST");
        Authentication autentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) autentication.getPrincipal();
        return userDetailsImpl.getUser();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id) {
        Optional<User> user = userService.findById(id);
        return user.orElseThrow(UserNotFoundException::new);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        logger.info("Запрошен метод удаления REST");
        userService.delete(id);
    }

    @PostMapping("/user")
    public ResponseEntity<HttpStatus> create(@RequestBody User user, BindingResult bindingResult) {
        logger.info("Запрошен метод ADD REST");
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage
                        .append(error.getField())
                        .append("-")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new UserNotCreatedException(errorMessage.toString());
        }
        User newUser;
        newUser = user;
        userService.save(newUser);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<HttpStatus> edit(@RequestBody @Valid User user, BindingResult bindingResult) {
        logger.info("Запрошен метод EDIT REST");
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage
                        .append(error.getField())
                        .append("-")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new UserNotCreatedException(errorMessage.toString());
        }
        userService.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse("User with this ID not found",
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException e) {
        UserErrorResponse response = new UserErrorResponse(e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

