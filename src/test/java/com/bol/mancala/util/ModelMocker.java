package com.bol.mancala.util;

import com.bol.mancala.domain.dto.Game.GameSettingDto;
import com.bol.mancala.domain.dto.Game.GameSetupDto;
import com.bol.mancala.domain.dto.Game.PlayerDto;

import java.util.Arrays;

public class ModelMocker {
    public static GameSetupDto createFullGameSetupDto() {
        GameSetupDto gameSetupDto = new GameSetupDto();
        GameSettingDto gameSetting = new GameSettingDto();
        gameSetting.setPlayerCount(2);
        gameSetting.setTotalPits(12);
        gameSetting.setTotalSeedPerPit(4);
        gameSetting.setTotalStores(2);
        gameSetupDto.setGameSetting(gameSetting);
        PlayerDto player1 = new PlayerDto();
        player1.setUserName("imax");
        PlayerDto player2 = new PlayerDto();
        player2.setUserName("reza");
        gameSetupDto.setPlayers(Arrays.asList(player1, player2));
        return gameSetupDto;
    }

    public static GameSetupDto createGameSetupWithoutGameSettingDto() {
        GameSetupDto gameSetupDto = new GameSetupDto();
        gameSetupDto.setGameSetting(null);
        PlayerDto player1 = new PlayerDto();
        player1.setUserName("imax");
        PlayerDto player2 = new PlayerDto();
        player2.setUserName("rozhin");
        gameSetupDto.setPlayers(Arrays.asList(player1, player2));
        return gameSetupDto;

    }

    public static GameSetupDto createGameSetupWithoutPlayerListDto() {
        GameSetupDto gameSetupDto = new GameSetupDto();
        GameSettingDto gameSetting = new GameSettingDto();
        gameSetting.setPlayerCount(2);
        gameSetting.setTotalPits(12);
        gameSetting.setTotalSeedPerPit(4);
        gameSetupDto.setGameSetting(gameSetting);
        gameSetupDto.setPlayers(null);
        return gameSetupDto;
    }

    public static GameSetupDto createGameSetupWhenPlayerUsernameIsNullOrEmpty() {
        GameSetupDto gameSetupDto = new GameSetupDto();
        GameSettingDto gameSetting = new GameSettingDto();
        gameSetting.setPlayerCount(2);
        gameSetting.setTotalPits(12);
        gameSetting.setTotalSeedPerPit(4);
        gameSetupDto.setGameSetting(gameSetting);
        PlayerDto player1 = new PlayerDto();
        player1.setUserName("imax");
        PlayerDto player2 = new PlayerDto();
        player2.setUserName(null);
        gameSetupDto.setPlayers(Arrays.asList(player1, player2));
        return gameSetupDto;
    }

    public static GameSetupDto createGameSetupDtoWithoutGameSettingDtoAndPlayerListDto() {
        GameSetupDto gameSetupDto = new GameSetupDto();
        gameSetupDto.setGameSetting(null);
        gameSetupDto.setPlayers(null);
        return gameSetupDto;
    }

    public static GameSetupDto createGameSetupDtoWhenGameSettingFieldsAreNegative() {
        GameSetupDto gameSetupDto = new GameSetupDto();
        GameSettingDto gameSetting = new GameSettingDto();
        gameSetting.setPlayerCount(-1);
        gameSetting.setTotalPits(0);
        gameSetting.setTotalSeedPerPit(-2);
        gameSetting.setTotalStores(-6);
        gameSetupDto.setGameSetting(gameSetting);
        PlayerDto player1 = new PlayerDto();
        player1.setUserName("imax");
        PlayerDto player2 = new PlayerDto();
        player2.setUserName("reza");
        gameSetupDto.setPlayers(Arrays.asList(player1, player2));
        return gameSetupDto;
    }

}
