package org.example.player.controller;

import org.example.player.client.DiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    @Autowired
    private DiceClient diceClient;

    @GetMapping("/player/rollDice")
    public int rollDice() {
        return diceClient.rollDice();
    }

    @GetMapping("/player/rollDices/{count}")
    public int[] rollDices(@PathVariable int count) {
        return diceClient.rollDices(count);
    }
}