package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.Repository;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class GangNeighbourhood implements Neighbourhood {
    @Override
    public void action(Player tommyVercetti, Collection<Player> civilPlayers) {
        Repository<Gun> tommyGunRepository = tommyVercetti.getGunRepository();
        Deque<Gun> tommyGuns = new ArrayDeque<>(tommyGunRepository.getModels());
        Deque<Player> civilPlayer = new ArrayDeque<>(civilPlayers);

        Player player = civilPlayer.poll();
        Gun gun = tommyGuns.poll();

        while (player != null && gun != null) {
            while (gun.canFire() && player.isAlive()) {
                int shot = gun.fire();
                player.takeLifePoints(shot);
            }
            if (gun.canFire()) {
                player = civilPlayer.poll();
            } else {
                gun = tommyGuns.poll();
            }
        }
        for (Player civPlayer : civilPlayers) {
            if (civPlayer.isAlive()) {
                Repository<Gun> civilPlayerRepository = civPlayer.getGunRepository();
                Deque<Gun> civilPlayerGuns = new ArrayDeque<>(civilPlayerRepository.getModels());
                Gun civilPlayerGun = civilPlayerGuns.poll();

                while (civilPlayerGun != null) {
                    while (civilPlayerGun.canFire() && tommyVercetti.isAlive()) {
                        int shot = civilPlayerGun.fire();
                        tommyVercetti.takeLifePoints(shot);
                    }
                    if (!tommyVercetti.isAlive()) {
                        return;
                    }
                    civilPlayerGun = civilPlayerGuns.poll();
                }
            }
        }
    }
}
