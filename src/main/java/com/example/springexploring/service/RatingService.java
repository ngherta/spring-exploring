package com.example.springexploring.service;

import org.springframework.transaction.annotation.Transactional;

public interface RatingService {
    @Transactional
    void vote(VoteCommand command);
}
