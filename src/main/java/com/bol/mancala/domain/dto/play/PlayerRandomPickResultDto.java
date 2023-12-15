package com.bol.mancala.domain.dto.play;

import com.bol.mancala.domain.enums.GameStatus;
import com.bol.mancala.domain.model.player.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PlayerRandomPickResultDto {
    private UUID gameId;
    private Player player;
    private GameStatus status;

    public PlayerRandomPickResultDto(UUID gameId, Player player) {
        this.gameId = gameId;
        this.player = player;
        this.status = GameStatus.RUNNING;
    }
}
