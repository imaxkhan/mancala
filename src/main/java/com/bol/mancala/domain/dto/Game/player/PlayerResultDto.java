package com.bol.mancala.domain.dto.Game.player;

import com.bol.mancala.domain.model.player.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * A Response DTO as mediator in gameSetup Creation,Player Random Pick And Game Play
 */
@Getter
@Setter
@NoArgsConstructor
public class PlayerResultDto extends PlayerDto {
    private UUID playerId;

    public PlayerResultDto(Player player) {
        super(player);
        if (player != null) {
            this.playerId = player.getPlayerId();
        }
    }
}
