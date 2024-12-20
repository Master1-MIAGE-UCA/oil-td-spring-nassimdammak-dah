package TD1_SpringBoot.dice.Interface;


import TD1_SpringBoot.dice.DiceRollLog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DiceRollLogRepository extends JpaRepository<DiceRollLog, Long> {
}

