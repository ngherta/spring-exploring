package com.example.springexploring.controller;

import com.example.springexploring.dto.UserDto;
import com.example.springexploring.service.RatingService;
import com.example.springexploring.service.UserService;
import com.example.springexploring.service.VoteCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor // DI
public class UserController {

    private final UserService userService;
    private final RatingService ratingService;

    @PostMapping
    public void save(@RequestBody AddUserCommand command) {
        userService.save(command);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/vote")
    public void vote(@RequestBody VoteCommand command) {
        ratingService.vote(command);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
