package com.bol.mancala.domain.dto.Game.setup;

import com.bol.mancala.base.config.oop.IValidation;
import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.Game.player.PlayerDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * A Request Dto for creating Game Setup
 * all game setting and player information
 * needed for registration resides here
 */
@Getter
@Setter
@NoArgsConstructor
public class GameSetupDto implements IValidation {
    @NotNull
    private GameSettingDto gameSetting;
    @NotNull
    @NotEmpty
    private List<PlayerDto> players;

    @Override
    public void validate() throws CustomException {
        for (PlayerDto player : players) {
            player.validate();
        }

    }
}
