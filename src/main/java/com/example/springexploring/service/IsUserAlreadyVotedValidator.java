package com.example.springexploring.service;

import com.example.springexploring.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IsUserAlreadyVotedValidator implements VotingValidator {

    private final RatingRepository ratingRepository;

    @Override
    public void validate(VoteCommand command) {
        if (ratingRepository.existsByVotedByIdAndVotedForId(command.getVotedByUserId(), command.getVotedForUserId())) {
            throw new RuntimeException("asda");
        }
    }
}
