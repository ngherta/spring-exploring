package com.example.springexploring.dto;

import com.example.springexploring.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

  private String firstName;
  private String lastName;
  private int age;

  public static UserDto map(User user) {
    return new UserDto(user.getFirstName(), user.getLastName(), user.getAge());
  }
}
