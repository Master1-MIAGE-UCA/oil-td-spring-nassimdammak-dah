package com.example.Dice;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Dice {
    private final Random random;

    public Dice() {
        this.random = new Random();
    }

    public int roll() {
        return random.nextInt(6) + 1; // Returns a number between 1 and 6
    }
}
