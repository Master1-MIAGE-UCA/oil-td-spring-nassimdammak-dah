package TD1_SpringBoot.dice.Service;

import TD1_SpringBoot.dice.Dice;
import TD1_SpringBoot.dice.DiceRollLog;
import TD1_SpringBoot.dice.Interface.DiceRollLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DiceService {

    private final Dice dice;
    private final DiceRollLogRepository repository;

    public DiceService(Dice dice, DiceRollLogRepository repository) {
        this.dice = dice;
        this.repository = repository;
    }

    public List<Integer> rollDices(int count) {
        // Génération des résultats des dés
        List<Integer> results = IntStream.range(0, count)
                .mapToObj(i -> dice.roll())
                .collect(Collectors.toList());

        // Création d'un log avec Lombok
        DiceRollLog log = new DiceRollLog();
        log.setDiceCount(count);
        log.setResults(results);
        log.setTimestamp(LocalDateTime.now());

        // Sauvegarde dans la base de données
        repository.save(log);

        return results;
    }
}

