package com.bol.mancala.domain.dto.Game;

import com.bol.mancala.domain.model.game.GameSetting;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameSettingResultDto extends GameSettingDto {
    public GameSettingResultDto(GameSetting gameSetting) {
        super(gameSetting);
    }
}
