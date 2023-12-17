package com.bol.mancala.game.service;

import com.bol.mancala.domain.dto.Game.setup.GameSettingDto;
import com.bol.mancala.domain.dto.Game.player.PlayerDto;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.domain.model.game.GameSetting;
import com.bol.mancala.domain.model.player.Player;

import java.util.List;

public interface GameGeneratorService {
    GameSetting createGameSetting(GameSettingDto gameSettingDto);

    List<Player> createPlayers(List<PlayerDto> playerDtoBucket);

    Game initializeGame(Game game);
}
