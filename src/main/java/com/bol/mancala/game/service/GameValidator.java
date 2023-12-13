package com.bol.mancala.game.service;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.Game.GameSettingDto;
import com.bol.mancala.domain.dto.Game.PlayerDto;

import java.util.List;

public interface GameValidator {

    void validateGameSetting(GameSettingDto gameSetting) throws CustomException;

    void validatePlayer(List<PlayerDto> players) throws CustomException;
}
