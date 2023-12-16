package com.bol.mancala.domain.dto.Game;

import com.bol.mancala.domain.model.game.GameSetting;
import jakarta.validation.constraints.Min;
import lombok.*;

/**
 * A Request DTO being used as mediator in gameSetup Creation
 */
@Getter
@Setter
@NoArgsConstructor
public class GameSettingDto {
    @Min(1)
    private int totalPits;
    @Min(1)
    private int totalStores;
    @Min(1)
    private int totalSeedPerPit;
    @Min(1)
    private int playerCount;
    @Min(1)
    private int totalPitPerPlayer;

    public GameSettingDto(GameSetting gameSetting) {
        if (gameSetting != null) {
            this.totalPits = gameSetting.getTotalPits();
            this.totalStores = gameSetting.getTotalStores();
            this.totalSeedPerPit = gameSetting.getTotalSeedPerPit();
            this.playerCount = gameSetting.getPlayerCount();
            this.totalPitPerPlayer = gameSetting.getTotalPitPerPlayer();
        }
    }

    public GameSettingDto(int totalPits, int totalStores, int totalSeedPerPit, int playerCount, int totalPitPerPlayer) {
        this.totalPits = totalPits;
        this.totalStores = totalStores;
        this.totalSeedPerPit = totalSeedPerPit;
        this.playerCount = playerCount;
        this.totalPitPerPlayer = totalPitPerPlayer;
    }
}
