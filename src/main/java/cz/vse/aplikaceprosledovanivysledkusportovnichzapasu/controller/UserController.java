package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.LoginRespDTO;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.UserLoginDTO;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
       return userService.getUserById(id);
    }
    @PostMapping("/login")
    public LoginRespDTO loginUser(@RequestBody UserLoginDTO user) {
        return userService.loginUser(user);
    }
    @GetMapping("/getAll")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
