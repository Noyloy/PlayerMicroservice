package com.intuit.player.repository;

import com.intuit.player.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByPlayerID(String playerID);
}
