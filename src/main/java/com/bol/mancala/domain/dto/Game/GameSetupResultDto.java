package com.bol.mancala.domain.dto.Game;

import com.bol.mancala.domain.enums.GameStatus;
import com.bol.mancala.domain.model.game.Game;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * A Response DTO responsible handle gameSetup creation Response
 */
@Getter
@Setter
@NoArgsConstructor
public class GameSetupResultDto {
    private UUID gameId;
    private GameStatus status;
    private List<PlayerResultDto> players;
    private GameSettingResultDto gameSetting;
    //overview of pits and seeds and players on board
    private BoardResultDto board;

    public GameSetupResultDto(Game game) {
        this.gameId = game.getGameId();
        this.board = new BoardResultDto(game.getBoard());
        this.gameSetting = new GameSettingResultDto(game.getGameSetting());
        this.players = game.getPlayers()
                .stream()
                .map(PlayerResultDto::new)
                .collect(Collectors.toList());
        this.status = game.getStatus();
    }
}


