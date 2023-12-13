package com.bol.mancala.domain.dto.Game;

import com.bol.mancala.domain.model.player.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PlayerResultDto extends PlayerDto {
    private UUID playerId;

    public PlayerResultDto(Player player) {
        super(player);
        this.playerId = player.getPlayerId();
    }
}
