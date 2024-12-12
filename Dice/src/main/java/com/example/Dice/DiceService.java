package com.example.Dice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiceService {

    @Autowired
    private Dice dice;

    @Autowired
    private DiceRollLogRepository diceRollLogRepository;

    public List<Integer> rollDices(int diceCount) {
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < diceCount; i++) {
            results.add(dice.roll());
        }
        logDiceRoll(diceCount, results);
        return results;
    }

    private void logDiceRoll(int diceCount, List<Integer> results) {
        DiceRollLog log = new DiceRollLog(diceCount, results, LocalDateTime.now());
        diceRollLogRepository.save(log);
    }
}