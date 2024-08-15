package tech.kayquedev.investhub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kayquedev.investhub.dtos.CreateUserDto;
import tech.kayquedev.investhub.entity.User;
import tech.kayquedev.investhub.service.UserService;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto){
        userService.createUser(createUserDto);
        return null;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){
        //
        return null;
    }
}
