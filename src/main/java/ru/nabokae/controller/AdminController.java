package ru.nabokae.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nabokae.service.RoleService;
import ru.nabokae.service.UserService;

@Controller
@RequestMapping("/app")
public class AdminController {
    public static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/home")
    public String HomePage() {
        logger.info("Запрошен список пользьзователей");
        return "BS5_admin";
    }

    @GetMapping("/")
    public String ListPageHome(Model model) {
        logger.info("Запрошен корневой адрес");
        model.addAttribute("usersAll", userService.findAllByOrderByIdAsc());
        return "BS5_admin";
    }
}
