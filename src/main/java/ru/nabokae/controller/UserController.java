package ru.nabokae.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.nabokae.service.UserService;


@Controller
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/Userhome")
    public String UserНоmePage() {
        logger.info("Запрошена домашняя страница пользователя");
        return "Userhome";
    }
}
