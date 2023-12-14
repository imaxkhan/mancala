package com.bol.mancala.game.service.impl;

import com.bol.mancala.base.config.properties.ApplicationProperties;
import com.bol.mancala.base.exception.CustomErrorCode;
import com.bol.mancala.base.exception.CustomErrorResult;
import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.Game.GameDto;
import com.bol.mancala.domain.dto.Game.GameResultDto;
import com.bol.mancala.domain.dto.Game.GameSettingDto;
import com.bol.mancala.domain.dto.Game.PlayerDto;
import com.bol.mancala.domain.model.concept.Pit;
import com.bol.mancala.domain.model.concept.Store;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.domain.model.game.GameSetting;
import com.bol.mancala.domain.model.player.Player;
import com.bol.mancala.domain.model.player.PlayerBoard;
import com.bol.mancala.game.repository.GameRepository;
import com.bol.mancala.game.service.GameGenerator;
import com.bol.mancala.game.service.GameValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameSetupService implements GameValidator, GameGenerator {

    private final GameRepository gameRepository;
    private final ApplicationProperties applicationProperties;

    public GameSetupService(@Qualifier("GM-IM-RP") GameRepository gameRepository, ApplicationProperties applicationProperties) {
        this.gameRepository = gameRepository;
        this.applicationProperties = applicationProperties;
    }

    public GameResultDto createGame(GameDto gameDto) throws CustomException {
        this.validateGameSetting(gameDto.getGameSetting());
        this.validatePlayer(gameDto.getPlayers());
        GameSetting gameSetting = this.createGameSetting(gameDto.getGameSetting());
        List<Player> players = this.createPlayers(gameDto.getPlayers());
        Game game = gameRepository.saveOrUpdate(new Game(gameSetting, players));
        this.initializeGame(game);
        return new GameResultDto(game);
    }

    @Override
    public void validateGameSetting(GameSettingDto gameSetting) throws CustomException {
        List<CustomErrorResult> customErrorResults = new ArrayList<>();

        validateProperty(gameSetting.getPlayerCount(), applicationProperties.getPlayerCountLimit(), "Player Size", customErrorResults);
        validateProperty(gameSetting.getTotalPits(), applicationProperties.getPitCountLimit(), "pit count", customErrorResults);
        validateProperty(gameSetting.getTotalSeedPerPit(), applicationProperties.getSeedCountPerPitLimit(), "pit count", customErrorResults);
        validateProperty(gameSetting.getTotalStores(), applicationProperties.getStoreCountLimit(), "store count", customErrorResults);

        if (!customErrorResults.isEmpty()) {
            throw new CustomException(CustomErrorCode.VALIDATION_FAILED, "Validation Failed for some input", null, customErrorResults);
        }
    }

    private void validateProperty(int actual, int expected, String propertyName, List<CustomErrorResult> errorResults) {
        if (actual != expected) {
            errorResults.add(new CustomErrorResult(CustomErrorCode.VALIDATION_FAILED,
                    propertyName + " must be " + expected, propertyName, null));
        }
    }

    @Override
    public void validatePlayer(List<PlayerDto> players) throws CustomException {
        if (players.size() != applicationProperties.getPlayerCountLimit()) {
            throw new CustomException(CustomErrorCode.VALIDATION_FAILED, "player count must be " + applicationProperties.getPlayerCountLimit());
        }

    }

    @Override
    public GameSetting createGameSetting(GameSettingDto gameSettingDto) {
        return GameSetting.builder()
                .playerCount(gameSettingDto.getPlayerCount())
                .totalPits(gameSettingDto.getTotalPits())
                .totalStores(gameSettingDto.getTotalStores())
                .totalSeedPerPit(gameSettingDto.getTotalSeedPerPit())
                .totalSeeds(gameSettingDto.getTotalSeedPerPit() * gameSettingDto.getTotalPits())
                .totalSeedPerPit(gameSettingDto.getTotalSeedPerPit())
                .build();
    }

    @Override
    public List<Player> createPlayers(List<PlayerDto> playerDtoBucket) {
        return playerDtoBucket.stream().map(playerDto -> Player.builder()
                        .playerId(UUID.randomUUID())
                        .userName(playerDto.getUserName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void initializeGame(Game game) {
        for (Player player : game.getPlayers()) {
            PlayerBoard playerBoard = new PlayerBoard();
            playerBoard.setPlayer(player);
            int totalPitPerPlayer = game.getGameSetting().getTotalPitPerPlayer();
            int totalSeedPerPit = game.getGameSetting().getTotalSeedPerPit();
            Set<Pit> pits = new HashSet<>();
            for (int i = 0; i < totalPitPerPlayer; i++) {
                Pit pit = new Pit();
                pit.setIndex(i);
                pit.setSeedCount(totalSeedPerPit);
                pits.add(pit);
            }
            playerBoard.setPits(pits);
            playerBoard.setStore(new Store(0));
            player.setPlayerBoard(playerBoard);
        }
    }
}
