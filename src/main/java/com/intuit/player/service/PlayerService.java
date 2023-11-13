package com.intuit.player.service;

import com.intuit.player.entity.Player;
import com.intuit.player.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
    public Optional<Player> getPlayerById(String playerId) {
        var players = playerRepository.findByPlayerID(playerId);
        log.info("Found "+players.size()+" having player id \""+playerId+"\"");
        return players.stream().findAny();
    }
}
