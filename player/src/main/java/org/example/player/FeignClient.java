package org.example.player.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "dice-service", url = "http://localhost:8081")
public interface DiceClient {
    @GetMapping("/roll")
    int rollDice();

    @GetMapping("/roll/{count}")
    int[] rollDices(@PathVariable("count") int count);
}