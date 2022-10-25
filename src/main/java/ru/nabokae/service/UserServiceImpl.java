package ru.nabokae.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nabokae.repository.RoleRepository;
import ru.nabokae.repository.UserRepository;
import ru.nabokae.entity.User;
import ru.nabokae.sequrity.UserDetailsImpl;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@Transactional (readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {
    private final EntityManager em;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(EntityManager em, RoleRepository roleRepository, UserRepository repo) {
        this.em = em;
        this.roleRepository = roleRepository;
        this.userRepository = repo;
    }

    @Transactional
    @Override
    public void save(User user) {
       // user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Iterable<User> findAllByOrderByIdAsc() {
        return userRepository.findAllByOrderByIdAsc();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    
    @Override
    public Optional<User> findByName(String name) {

        Optional<User> user = userRepository.findByName(name);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetailsImpl(user.get());
    }
}
