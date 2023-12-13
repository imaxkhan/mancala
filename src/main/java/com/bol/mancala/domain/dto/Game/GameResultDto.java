package com.bol.mancala.domain.dto.Game;

import com.bol.mancala.domain.model.game.Game;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class GameResultDto extends GameDto {
    private UUID gameId;
    private GameSettingResultDto gameSettingResultDto;
    private List<PlayerResultDto> playerResults;

    public GameResultDto(Game game) {
        super(game);
        this.gameSettingResultDto = new GameSettingResultDto(game.getGameSetting());
        this.playerResults = game.getPlayers()
                .stream()
                .map(PlayerResultDto::new)
                .collect(Collectors.toList());
    }
}


