package com.example.springexploring.service;

import org.springframework.stereotype.Component;

@Component
public class AreUsersEqual implements VotingValidator {
    @Override
    public void validate(VoteCommand command) {
        if (command.getVotedByUserId().equals(command.getVotedForUserId())) {
            throw new RuntimeException("asd");
        }
    }
}
