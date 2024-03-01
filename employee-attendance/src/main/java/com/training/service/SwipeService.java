package com.training.service;

import com.training.entity.Swipe;

public interface SwipeService {

    public Swipe createSwipeInRecord(int id);
    public Swipe createSwipeOutRecord(int id);
    public Swipe createCustomSwipeRecord(Swipe swipe, int id);
    public void deleteSwipeRecord(int id);

}
