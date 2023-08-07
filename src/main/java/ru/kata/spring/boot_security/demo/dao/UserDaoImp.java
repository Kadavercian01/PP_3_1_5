package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao, UserDetailsService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        @Autowired
        public UserDaoImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
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
        public void updateUser(User updateUser, long id) {
            updateUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
            updateUser.setId(id);
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
            return userRepository.findByEmail(email);
        }
}
