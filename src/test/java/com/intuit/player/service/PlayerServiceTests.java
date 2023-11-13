package com.intuit.player.service;

import com.intuit.player.entity.Player;
import com.intuit.player.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PlayerServiceTests {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @Test
    public void getAllPlayers_callsRepoFindAll() {
        // Act
        playerService.getAllPlayers();
        // Assert
        verify(playerRepository, times(1)).findAll();
    }

    @Test
    public void getPlayerById_callsRepoFindByPlayerID() {
        // Arrange
        when(playerRepository.findByPlayerID("")).thenReturn(Collections.emptyList());
        // Act
        var optPlayer = playerService.getPlayerById("");
        // Assert
        verify(playerRepository, times(1)).findByPlayerID("");
        assertTrue(optPlayer.isEmpty());
    }

    @Test
    public void getPlayerById_returnsTheFirstMatchingPlayer() {
        // Arrange
        String playerId = "noyloy123";

        var p1 = new Player();
        var p2 = new Player();
        p1.setPlayerID(playerId);
        p2.setPlayerID(playerId);
        List<Player> players = new ArrayList<>() {{add(p1); add(p2);}};

        when(playerRepository.findByPlayerID(playerId)).thenReturn(players);

        // Act
        var optPlayer = playerService.getPlayerById(playerId);

        // Assert
        verify(playerRepository, times(1)).findByPlayerID(playerId);
        assertEquals(p1, optPlayer.get());
    }
}
