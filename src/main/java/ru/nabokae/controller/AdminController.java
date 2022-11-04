package ru.nabokae.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nabokae.entity.Role;
import ru.nabokae.entity.User;
import ru.nabokae.sequrity.UserDetailsImpl;
import ru.nabokae.service.RoleService;
import ru.nabokae.service.UserService;

import javax.validation.Valid;
import java.util.*;


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
    public String HomePage(Model model, User user) {
        logger.info("Запрошен список пользьзователей");
       /* model.addAttribute("usersAll", userService.findAllByOrderByIdAsc());
        Authentication autentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) autentication.getPrincipal();
        User userss=userDetailsImpl.getUser();
        model.addAttribute("userss", userss);
        HashSet<GrantedAuthority> hashSet = new HashSet<GrantedAuthority>(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        String role = "";
        for (GrantedAuthority gauth : hashSet) {
            role = role + " " + gauth.getAuthority().substring(5);
        }
        List<Role> roleList = roleService.findAll();
        model.addAttribute("PrincipalRoles", role);
        model.addAttribute("AllRoles", roleList);*/
        return "BS5_admin";
    }

    @GetMapping("/")
    public String ListPageHome(Model model) {
        logger.info("Запрошен корневой адрес");
        model.addAttribute("usersAll", userService.findAllByOrderByIdAsc());
        return "BS5_admin";
    }

    @PostMapping("/new")
    public String NewUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
        logger.info("Запрошена страница создания нового пользователя");
        User newUser = new User();
        newUser = user;
        userService.save(user);
        return "redirect:/admin/all";
    }

    @PostMapping("/all")
    public String UpdateUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
        logger.info("Запрошен обновленный список пользователей");
        userService.save(user);
        return "redirect:/admin/all";
    }

    @ResponseBody
    @GetMapping("/{id}")
    public String EditUserForm(@PathVariable("id") Long id, Model model) {
        logger.info("Запрошена страница редактирования пользователя");
     model.addAttribute("user", String.valueOf(userService.findById(id)));
        return "User";
    }

    @GetMapping("/{id}/delete")
    public String EditUserForm(@PathVariable("id") Long id) {
        logger.info("Запрошена страница удаления пользователя c id={}", id);
        userService.delete(id);
        return "redirect:/admin/all";
    }

    @RequestMapping(value = "/update", method = {RequestMethod.PUT,RequestMethod.GET})
    public String Update(User user) {
        logger.info("Запрошен новый апдейт метод");
        userService.save(user);
        return "redirect:/admin/all";
    }

}
