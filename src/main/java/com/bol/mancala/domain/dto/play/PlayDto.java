package com.bol.mancala.domain.dto.play;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * A request DTO for game play
 */
@Getter
@Setter
public class PlayDto {
    //which player
    @NotNull
    private UUID playerId;
    //which none-empty and none-store pit
    @Min(0)
    private int pitIndex;
}
