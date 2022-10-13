package ru.nabokae.service;

import ru.nabokae.entity.Role;
import ru.nabokae.entity.User;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
}
