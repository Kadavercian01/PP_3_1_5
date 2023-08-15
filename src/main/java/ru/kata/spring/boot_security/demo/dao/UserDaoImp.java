package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserDaoImp implements UserDao, UserDetailsService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        @Autowired
        public UserDaoImp(UserRepository userRepository,@Lazy PasswordEncoder passwordEncoder) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        public void saveUser(User user) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }

        @Override
        public User findUserById(long id) {
            Optional<User> foundUser = userRepository.findById(id);
            return foundUser.orElse(null);
        }

        @Override
        public List<User> getAllUsers() {
            return userRepository.findAll();
        }

        @Override
        public void updateUser(User updateUser) {
            userRepository.save(updateUser);
        }

        @Override
        public void deleteUser(long id) {
            userRepository.deleteById(id);
        }

        @Override
        public User findByUsername(String email) {
            return userRepository.findByEmail(email);
        }

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            User user = findByUsername(email);
            if (user == null) {
                throw new UsernameNotFoundException(String.format("User '%s' not found", email));
            }
            return user;
        }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
