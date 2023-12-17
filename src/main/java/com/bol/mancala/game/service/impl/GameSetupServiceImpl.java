package com.bol.mancala.game.service.impl;

import com.bol.mancala.base.config.properties.ApplicationProperties;
import com.bol.mancala.base.exception.CustomErrorCode;
import com.bol.mancala.base.exception.CustomErrorResult;
import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.Game.setup.GameSetupDto;
import com.bol.mancala.domain.dto.Game.setup.GameSetupResultDto;
import com.bol.mancala.domain.dto.Game.setup.GameSettingDto;
import com.bol.mancala.domain.dto.Game.player.PlayerDto;
import com.bol.mancala.domain.model.concept.Board;
import com.bol.mancala.domain.model.concept.Pit;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.domain.model.game.GameSetting;
import com.bol.mancala.domain.model.player.Player;
import com.bol.mancala.game.repository.GameRepository;
import com.bol.mancala.game.service.GameGeneratorService;
import com.bol.mancala.game.service.GameSetupService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.bol.mancala.base.util.GeneralUtility.validateProperty;

/**
 * Responsible for managing gameSetup creation,player registration
 * and initializing game board
 * and validating game physical concept rules
 */
@Service
public class GameSetupServiceImpl implements GameSetupService, GameGeneratorService {

    private final GameRepository gameRepository;
    private final ApplicationProperties applicationProperties;

    public GameSetupServiceImpl(@Qualifier("GM-IM-RP") GameRepository gameRepository, ApplicationProperties applicationProperties) {
        this.gameRepository = gameRepository;
        this.applicationProperties = applicationProperties;
    }

    /**
     *
     * @param gameDto request for creating gameSetup
     * @return GameSetupResult including all game information
     * @throws CustomException by inner validator methods
     */
    @Override
    public GameSetupResultDto createGame(GameSetupDto gameDto) throws CustomException {
        this.validateGameSetting(gameDto.getGameSetting());
        this.validatePlayer(gameDto.getPlayers());
        GameSetting gameSetting = this.createGameSetting(gameDto.getGameSetting());
        List<Player> players = this.createPlayers(gameDto.getPlayers());
        Game game = gameRepository.saveOrUpdate(new Game(gameSetting, players));
        this.initializeGame(game);
        return new GameSetupResultDto(game);
    }


    //checking game setting with preconfigured properties in bootstrap
    @Override
    public void validateGameSetting(GameSettingDto gameSetting) throws CustomException {
        List<CustomErrorResult> customErrorResults = new ArrayList<>();

        validateProperty(gameSetting.getPlayerCount(), applicationProperties.getPlayerCountLimit(), "Player Size", customErrorResults);
        validateProperty(gameSetting.getTotalPits(), applicationProperties.getPitCountLimit(), "pit count", customErrorResults);
        validateProperty(gameSetting.getTotalSeedPerPit(), applicationProperties.getSeedCountPerPitLimit(), "pit count", customErrorResults);
        validateProperty(gameSetting.getTotalStores(), applicationProperties.getStoreCountLimit(), "store count", customErrorResults);

        if (!customErrorResults.isEmpty()) {
            throw new CustomException(CustomErrorCode.VALIDATION_FAILED, "Validation Failed for some input", customErrorResults);
        }
    }

    //check size of player with predefined properties
    @Override
    public void validatePlayer(List<PlayerDto> players) throws CustomException {
        if (players.size() != applicationProperties.getPlayerCountLimit()) {
            throw new CustomException(CustomErrorCode.VALIDATION_FAILED, "player count must be " + applicationProperties.getPlayerCountLimit());
        }
    }

    //model builder for GameSetting entity
    @Override
    public GameSetting createGameSetting(GameSettingDto gameSettingDto) {
        return GameSetting.builder()
                .playerCount(gameSettingDto.getPlayerCount())
                .totalPits(gameSettingDto.getTotalPits())
                .totalPitPerPlayer(gameSettingDto.getTotalPitPerPlayer())
                .totalStores(gameSettingDto.getTotalStores())
                .totalSeedPerPit(gameSettingDto.getTotalSeedPerPit())
                .totalSeeds(gameSettingDto.getTotalSeedPerPit() * gameSettingDto.getTotalPits())
                .totalSeedPerPit(gameSettingDto.getTotalSeedPerPit())
                .build();
    }

    //model builder for player entity
    @Override
    public List<Player> createPlayers(List<PlayerDto> playerDtoBucket) {
        return playerDtoBucket.stream().map(playerDto -> Player.builder()
                        .playerId(UUID.randomUUID())
                        .userName(playerDto.getUserName())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * important method that generate board pits,stores,seeds for each players
     * pit index start from zero till total pit count
     * also stores here are pit and have an index after own pits
     * at end generated pits will be added as board entity to game entity
     * @param game
     */
    @Override
    public Game initializeGame(Game game) {
        int totalPitPerPlayer = game.getGameSetting().getTotalPitPerPlayer();
        Board board = new Board();
        board.setActivePlayer(null);
        List<Pit> pits = new ArrayList<>();

        int index = 0;
        for (Player player : game.getPlayers()) {
            for (int j = 0; j < totalPitPerPlayer; j++) {
                Pit pit = new Pit();
                pit.setSeedCount(game.getGameSetting().getTotalSeedPerPit());
                pit.setIndex(index);
                pit.setStore(false);
                pit.setPlayer(player);
                pits.add(pit);
                index = index + 1;
            }
            Pit pit = new Pit();
            pit.setIndex(index);
            pit.setStore(true);
            pit.setPlayer(player);
            pit.setSeedCount(0);
            pits.add(pit);
            index = index + 1;
        }
        board.setPits(pits);
        game.setBoard(board);
        return game;
    }
}
