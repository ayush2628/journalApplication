package com.ayushsharma.journalapplication.controller;

import com.ayushsharma.journalapplication.entity.User;
import com.ayushsharma.journalapplication.repository.UserRepositoryImpl;
import com.ayushsharma.journalapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @PostMapping("/create-user")
    public void createUserEntry(@RequestBody User user) {
        userService.saveNewUser(user);
    }
    @Autowired
    private UserRepositoryImpl userRepository;
    @GetMapping
    public ResponseEntity<?> getSentimentalPeople(){
        List<User> users = userRepository.getUserForSA();
        if(!users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("check-for-redis")
    public ResponseEntity<?> getRedisValue(){

        Object name = redisTemplate.opsForValue().get("mothername");

//        Object name2 = redisTemplate.opsForValue().get("email");
//        String t = "ayush";
        return new ResponseEntity<>(name,HttpStatus.OK);

    }

}
