package com.example.springexploring.service;

import com.example.springexploring.entity.Rating;
import com.example.springexploring.entity.User;
import com.example.springexploring.repository.RatingRepository;
import com.example.springexploring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final List<VotingValidator> validators;

    @Override
    @Transactional
    public void vote(VoteCommand command) {
        validators.forEach(e -> e.validate(command));
        createNewRating(command);
    }

    private void createNewRating(VoteCommand command) {
        User votedFor = userRepository.findById(command.getVotedForUserId()).orElseThrow();
        User votedBy = userRepository.findById(command.getVotedByUserId()).orElseThrow();
        int newRating = calculateNewRatingFor(votedFor, command);
        votedFor.setRating(newRating);
        Rating rating = new Rating(votedBy, votedFor, command.getStars());
        ratingRepository.save(rating);
    }

    private int calculateNewRatingFor(User votedFor, VoteCommand command) {
        List<Integer> ratings = ratingRepository.findAllStarsByVotedFor(votedFor);
        long newRating = command.getStars();
        for (Integer rating : ratings) {
            newRating += rating;
        }
        return (int) (newRating / (ratings.size() + 1));
    }
}
