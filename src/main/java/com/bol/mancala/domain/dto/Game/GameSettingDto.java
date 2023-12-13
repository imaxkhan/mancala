package com.bol.mancala.domain.dto.Game;

import com.bol.mancala.domain.model.game.GameSetting;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameSettingDto {
    @Min(1)
    private int totalPits;
    @Min(1)
    private int totalStores;
    @Min(1)
    private int totalSeedPerPit;
    @Min(1)
    private int totalSeeds;
    @Min(1)
    private int playerCount;

    public GameSettingDto(GameSetting gameSetting) {
        if (gameSetting != null) {
            this.totalPits = gameSetting.getTotalPits();
            this.totalStores = gameSetting.getTotalStores();
            this.totalSeedPerPit = gameSetting.getTotalSeedPerPit();
            this.playerCount = gameSetting.getPlayerCount();
            this.totalSeeds = gameSetting.getTotalSeeds();
        }
    }
}
