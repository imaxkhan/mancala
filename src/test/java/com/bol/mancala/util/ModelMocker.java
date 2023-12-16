package com.bol.mancala.util;

import com.bol.mancala.domain.dto.Game.GameSettingDto;
import com.bol.mancala.domain.dto.Game.GameSetupDto;
import com.bol.mancala.domain.dto.Game.PlayerDto;
import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.model.concept.Board;
import com.bol.mancala.domain.model.concept.Pit;
import com.bol.mancala.domain.model.game.GameSetting;
import com.bol.mancala.domain.model.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ModelMocker {
    public static GameSetupDto createFullGameSetupDtoWithNegativeSetting() {
        GameSetupDto gameSetupDto = new GameSetupDto();
        GameSettingDto gameSetting = new GameSettingDto();
        gameSetting.setPlayerCount(-2);
        gameSetting.setTotalPits(-12);
        gameSetting.setTotalSeedPerPit(-4);
        gameSetting.setTotalStores(0);
        gameSetupDto.setGameSetting(gameSetting);
        PlayerDto player1 = new PlayerDto();
        player1.setUserName("Ragnar");
        PlayerDto player2 = new PlayerDto();
        player2.setUserName("Rollo");
        gameSetupDto.setPlayers(Arrays.asList(player1, player2));
        return gameSetupDto;
    }

    public static GameSetupDto createFullGameSetupDto() {
        GameSetupDto gameSetupDto = new GameSetupDto();
        GameSettingDto gameSetting = new GameSettingDto();
        gameSetting.setPlayerCount(2);
        gameSetting.setTotalPits(12);
        gameSetting.setTotalSeedPerPit(4);
        gameSetting.setTotalPitPerPlayer(6);
        gameSetting.setTotalStores(2);
        gameSetupDto.setGameSetting(gameSetting);
        PlayerDto player1 = new PlayerDto();
        player1.setUserName("Ragnar");
        PlayerDto player2 = new PlayerDto();
        player2.setUserName("Rollo");
        gameSetupDto.setPlayers(Arrays.asList(player1, player2));
        return gameSetupDto;
    }

    public static GameSetupDto createFullGameSetupDtoWithExtraPlayer() {
        GameSetupDto gameSetupDto = new GameSetupDto();
        GameSettingDto gameSetting = new GameSettingDto();
        gameSetting.setPlayerCount(2);
        gameSetting.setTotalPits(12);
        gameSetting.setTotalSeedPerPit(4);
        gameSetting.setTotalStores(2);
        gameSetupDto.setGameSetting(gameSetting);
        PlayerDto player1 = new PlayerDto();
        player1.setUserName("Ragnar");
        PlayerDto player2 = new PlayerDto();
        player2.setUserName("Rollo");
        PlayerDto player3 = new PlayerDto();
        player2.setUserName("Aivar");
        gameSetupDto.setPlayers(Arrays.asList(player1, player2, player3));
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

    public static PlayDto createFullPlayDto() {
        PlayDto playDto = new PlayDto();
        playDto.setPlayerId(UUID.randomUUID());
        playDto.setPitIndex(0);
        return playDto;
    }

    public static PlayDto createPlayDtoWithoutPlayerId() {
        PlayDto playDto = new PlayDto();
        playDto.setPlayerId(null);
        playDto.setPitIndex(0);
        return playDto;
    }

    public static List<Player> createPlayers() {
        Player playerOne = Player.builder()
                .playerId(UUID.randomUUID())
                .userName("Ragnar")
                .build();
        Player playerTwo = Player.builder()
                .playerId(UUID.randomUUID())
                .userName("Rollo")
                .build();
        return Arrays.asList(playerOne, playerTwo);
    }

    public static GameSetting createGameSetting() {
        return GameSetting.builder()
                .totalSeedPerPit(4)
                .playerCount(2)
                .totalPitPerPlayer(6)
                .totalSeeds(48)
                .totalStores(2)
                .totalPits(12)
                .playerCount(2).build();
    }

    public static Board createBoard() {
        List<Player> players = createPlayers();
        Board board = new Board();
        board.setActivePlayer(null);
        List<Pit> pits = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Pit pit = new Pit();
            pit.setSeedCount(4);
            pit.setStore(false);
            pit.setPlayer(players.get(0));
            pit.setIndex(i);
            pits.add(pit);
        }
        Pit pit = new Pit();
        pit.setIndex(6);
        pit.setStore(true);
        pit.setSeedCount(0);
        pit.setPlayer(players.get(0));
        pits.add(pit);
        for (int i = 7; i < 13; i++) {
            Pit opponentPit = new Pit();
            opponentPit.setSeedCount(4);
            opponentPit.setStore(false);
            opponentPit.setPlayer(players.get(1));
            opponentPit.setIndex(i);
            pits.add(opponentPit);
        }
        Pit opponentPit = new Pit();
        opponentPit.setIndex(13);
        opponentPit.setStore(true);
        opponentPit.setSeedCount(0);
        opponentPit.setPlayer(players.get(1));
        pits.add(opponentPit);

        board.setPits(pits);
        return board;
    }

    public static Board createChampionBoard() {
        List<Player> players = createPlayers();
        Player playerOne = players.get(0);
        Player playerTwo = players.get(1);
        Board board = new Board();
        board.setActivePlayer(null);
        List<Pit> pits = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Pit pit = new Pit();
            pit.setSeedCount(0);
            pit.setStore(false);
            pit.setPlayer(playerOne);
            pit.setIndex(i);
            pits.add(pit);
        }
        Pit pit = new Pit();
        pit.setIndex(6);
        pit.setStore(true);
        pit.setSeedCount(30);
        pit.setPlayer(playerOne);
        pits.add(pit);
        for (int i = 7; i < 13; i++) {
            Pit opponentPit = new Pit();
            opponentPit.setSeedCount(3);
            opponentPit.setStore(false);
            opponentPit.setPlayer(playerTwo);
            opponentPit.setIndex(i);
            pits.add(opponentPit);
        }
        Pit opponentPit = new Pit();
        opponentPit.setIndex(13);
        opponentPit.setStore(true);
        opponentPit.setSeedCount(0);
        opponentPit.setPlayer(playerTwo);
        pits.add(opponentPit);

        board.setPits(pits);
        return board;
    }
}

