package com.example.springexploring.controller;

import com.example.springexploring.dto.UserDto;
import com.example.springexploring.entity.User;
import com.example.springexploring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

  @PostMapping
  public void save(@RequestBody AddUserCommand command) {
    userService.save(command);
  }

  @GetMapping
  public ResponseEntity<List<UserDto>> findAll() {
    return ResponseEntity.ok(userService.findAll());
  }
}
