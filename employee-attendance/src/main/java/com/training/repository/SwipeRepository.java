package com.training.repository;

import com.training.entity.Swipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwipeRepository extends JpaRepository<Swipe, Integer> {
}
