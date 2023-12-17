package com.bol.mancala.game.service;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.Game.setup.GameSettingDto;
import com.bol.mancala.domain.dto.Game.setup.GameSetupDto;
import com.bol.mancala.domain.dto.Game.setup.GameSetupResultDto;
import com.bol.mancala.domain.dto.Game.player.PlayerDto;

import java.util.List;

public interface GameSetupService {
    GameSetupResultDto createGame(GameSetupDto gameDto) throws CustomException;

    void validateGameSetting(GameSettingDto gameSetting) throws CustomException;

    void validatePlayer(List<PlayerDto> players) throws CustomException;
}
