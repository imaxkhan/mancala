package com.bol.mancala.domain.dto.play;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PlayDto {
    @NotNull
    private UUID playerId;
    @Min(0)
    private int pitIndex;
}
