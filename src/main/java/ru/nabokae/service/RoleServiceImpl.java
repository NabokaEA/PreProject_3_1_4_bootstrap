package ru.nabokae.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nabokae.DAO.RoleRepository;
import ru.nabokae.DAO.UserRepository;
import ru.nabokae.entity.Role;

import javax.persistence.EntityManager;
import java.util.List;
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService{
    private final EntityManager em;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    public RoleServiceImpl(EntityManager em, RoleRepository roleRepository, UserRepository userRepository) {
        this.em = em;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }
}
