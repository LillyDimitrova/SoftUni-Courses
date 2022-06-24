package bg.softuni.battleShips_ExamPreparation.service;

import bg.softuni.battleShips_ExamPreparation.model.dto.StartBattleDTO;
import bg.softuni.battleShips_ExamPreparation.model.entity.ShipEntity;
import bg.softuni.battleShips_ExamPreparation.repository.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BattleService {

    private final ShipRepository shipRepository;

    public BattleService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public void attack(StartBattleDTO startBattleDTO) {
        Optional<ShipEntity> attackerOpt = shipRepository.findById((long)startBattleDTO.getAttackerId());
        Optional<ShipEntity> defenderOpt = shipRepository.findById((long)startBattleDTO.getDefenderId());

        if (attackerOpt.isEmpty() || defenderOpt.isEmpty()) {
            throw new NoSuchElementException();
        }

        ShipEntity attacker = attackerOpt.get();
        ShipEntity defender = defenderOpt.get();

        long newDefenderHealth  = defender.getHealth() - attacker.getPower();

        if (newDefenderHealth  <= 0) {
            shipRepository.delete(defender);
        } else {
            defender.setHealth(newDefenderHealth);
            shipRepository.save(defender);
        }
    }
}
