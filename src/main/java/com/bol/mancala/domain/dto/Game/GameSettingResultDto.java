package com.bol.mancala.domain.dto.Game;

import com.bol.mancala.domain.model.game.GameSetting;
import lombok.Getter;
import lombok.Setter;

/**
 * A Response mediator DTO in gameSetup Creation Response
 */
@Getter
@Setter
public class GameSettingResultDto extends GameSettingDto {
    private int totalSeeds;

    public GameSettingResultDto(GameSetting gameSetting) {
        super(gameSetting);
        this.totalSeeds = gameSetting.getTotalSeeds();
    }
}
