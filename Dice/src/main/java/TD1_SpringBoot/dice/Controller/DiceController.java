package TD1_SpringBoot.dice.Controller;



import java.util.List;

import TD1_SpringBoot.dice.DiceRollLog;
import TD1_SpringBoot.dice.Interface.DiceRollLogRepository;
import TD1_SpringBoot.dice.Service.DiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DiceController {

    @Autowired
    private DiceService diceService;

    @Autowired
    private DiceRollLogRepository diceRollLogRepository;

    @GetMapping("/rollDice")
    public int rollDice() {
        return diceService.rollDices(1).get(0);
    }

    @GetMapping("/rollDices/{X}")
    public List<Integer> rollDices(@PathVariable int X) {
        return diceService.rollDices(X);
    }

    @GetMapping("/diceLogs")
    public List<DiceRollLog> getAllDiceLogs() {
        return diceRollLogRepository.findAll();
    }
}