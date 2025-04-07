package com.example.Crypto.Controllers;

import com.example.Crypto.DTOs.UserDTO;
import com.example.Crypto.Entities.User;
import com.example.Crypto.Services.UserCryptoService;
import com.example.Crypto.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "user")
public class UserController {
    private final UserService userService;
    private final UserCryptoService userCryptoService;

    @GetMapping(value = "/{userId}/balance")
    public ResponseEntity<Double> getUserBalance(@PathVariable Long userId) {
        var balance = userService.getUserBalance(userId);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
        var user = userService.createUser(userDTO);

        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userId}/reset")
    public ResponseEntity<?> resetUser(@PathVariable Long userId){
        userService.resetAccountBalance(userId);
        userCryptoService.removeHoldings(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
