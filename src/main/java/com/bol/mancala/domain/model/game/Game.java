package com.bol.mancala.domain.model.game;

import com.bol.mancala.domain.model.player.Champion;
import com.bol.mancala.domain.model.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Game {
    private UUID gameId;
    private List<Player> players;
    private GameSetting gameSetting;
    private Champion champion;
}
