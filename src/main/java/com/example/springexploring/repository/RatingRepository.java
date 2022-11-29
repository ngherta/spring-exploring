package com.example.springexploring.repository;

import com.example.springexploring.entity.Rating;
import com.example.springexploring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    boolean existsByVotedByIdAndVotedForId(Long votedByUserId, Long votedForUserId);

    @Query(value = "select r.stars from Rating r where r.votedFor = :user")
    List<Integer> findAllStarsByVotedFor(@Param("user") User user);
}
