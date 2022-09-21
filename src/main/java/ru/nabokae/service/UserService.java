package ru.nabokae.service;

import ru.nabokae.entity.User;

import java.util.Optional;

public interface UserService {
    void save(User entity);

    Optional<User> findById(Long id);

    Iterable<User> findAll();

    void delete(Long id);

   Optional<User>  findByName(String name);
}
