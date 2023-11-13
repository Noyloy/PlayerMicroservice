package com.intuit.player.loader;

import com.intuit.player.constant.ResourcesConstants;
import com.intuit.player.converter.PlayerConverter;
import com.intuit.player.entity.Player;
import com.intuit.player.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerLoader implements ICsvDBLoader {

    @Autowired
    private PlayerRepository repository;
    @Autowired
    private PlayerConverter converter;

    @Override
    public boolean load(String[] csvRow) {
        try {
            Player player = converter.Convert(csvRow);
            repository.save(player);
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }

    @Override
    public String getCSVPath() {
        return ResourcesConstants.PLAYER_FILE_PATH;
    }

    @Override
    public int getCSVContentRowStartIndex() {
        return ResourcesConstants.PLAYER_FILE_CONTENT_ROW;
    }

    @Override
    public String getCSVDBName() {
        return "Player";
    }
}
