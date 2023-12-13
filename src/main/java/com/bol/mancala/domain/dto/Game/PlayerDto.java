package com.bol.mancala.domain.dto.Game;

import com.bol.mancala.domain.model.player.Player;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerDto {
    @NotNull
    private String userName;

    public PlayerDto(Player player) {
        if (player != null) {
            this.userName = player.getUserName();
        }
    }
}
