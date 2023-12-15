package com.bol.mancala.game.service;

import com.bol.mancala.domain.dto.Game.GameSettingDto;
import com.bol.mancala.domain.dto.Game.PlayerDto;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.domain.model.game.GameSetting;
import com.bol.mancala.domain.model.player.Player;

import java.util.List;

public interface GameGeneratorService {
    GameSetting createGameSetting(GameSettingDto gameSettingDto);

    List<Player> createPlayers(List<PlayerDto> playerDtoBucket);

    void initializeGame(Game game);
}
