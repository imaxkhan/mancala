package com.bol.mancala.domain.dto.Game;

import com.bol.mancala.base.config.oop.IValidation;
import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.model.game.Game;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GameDto implements IValidation {
    @NotNull
    private GameSettingDto gameSetting;
    @NotNull
    @NotEmpty
    private List<PlayerDto> players;

    public GameDto(Game game) {
        if (game != null) {
            this.gameSetting = new GameSettingDto(game.getGameSetting());
        }
    }

    @Override
    public void validate() throws CustomException {
        for (PlayerDto player : players) {
            player.validate();
        }

    }
}
