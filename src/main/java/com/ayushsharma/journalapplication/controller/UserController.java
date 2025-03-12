package com.ayushsharma.journalapplication.controller;
import com.ayushsharma.journalapplication.api.response.WeatherResponse;
import com.ayushsharma.journalapplication.entity.User;
import com.ayushsharma.journalapplication.service.UserService;
import com.ayushsharma.journalapplication.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private WeatherService weatherService;
    @GetMapping
    public List<?> getUserEntries() {return userService.getUserEntries();}
    @PutMapping
    public ResponseEntity<?> updateUserEntry(@RequestBody User user) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User userInDb = userService.findByUsername(username);
        if(userInDb !=  null) {
            userInDb.setUsername(!user.getUsername().isEmpty() ? user.getUsername() : userInDb.getUsername());
            userInDb.setPassword(!user.getPassword().isEmpty() ? user.getPassword() : userInDb.getPassword());
            userService.saveNewUser(userInDb);
            return new ResponseEntity<>(userInDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/weather")
    public ResponseEntity<?> Weather(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting = "";
        if(weatherResponse != null){
            greeting = " Weather feels Like" + weatherResponse.getCurrent().feelslike;
        }
        return new ResponseEntity<>("Hi" + username + greeting, HttpStatus.OK);
    }

}
