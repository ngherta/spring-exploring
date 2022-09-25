package com.example.springexploring.service;

import com.example.springexploring.controller.AddUserCommand;
import com.example.springexploring.dto.UserDto;
import com.example.springexploring.entity.User;
import com.example.springexploring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  @Transactional
  public void save(AddUserCommand command) {
    User user = new User(command.getFirstName(), command.getLastName(), command.getAge());
    userRepository.save(user);
  }

  @Override
  @Transactional
  public List<UserDto> findAll() {
    List<User> users = userRepository.findAll();
    List<UserDto> dtos = new ArrayList<>();
    for (User user : users) {
      dtos.add(UserDto.map(user));
    }
    return dtos;
  }
}
