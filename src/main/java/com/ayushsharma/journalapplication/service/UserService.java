package com.ayushsharma.journalapplication.service;

import com.ayushsharma.journalapplication.entity.User;
import com.ayushsharma.journalapplication.repository.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Data
@Component
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveExistingUserEntry(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error while saving user entry {}" , user.getUsername() , e);
            throw new RuntimeException(e);
        }
    }

    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        userRepository.save(user);
    }

    public List<User> getUserEntries() {
        return userRepository.findAll();
    }

    // idhar pe optional liya h dekhna ek baar
    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void updateUserDetails(User userInDb,User user){
        String encoded_password = passwordEncoder.encode(user.getPassword());
        userInDb.setUsername(user.getUsername());
//        userInDb.setUsername(!user.getUsername().isEmpty() ? user.getUsername() : userInDb.getUsername());
        userInDb.setPassword(!user.getPassword().isEmpty() ? encoded_password : userInDb.getPassword());
        saveExistingUserEntry(userInDb);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveAdminUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER","ADMIN"));
        userRepository.save(user);
    }
}
