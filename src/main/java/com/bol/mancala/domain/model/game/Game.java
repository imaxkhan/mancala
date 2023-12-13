package com.bol.mancala.domain.model.game;

import com.bol.mancala.domain.model.player.Champion;
import com.bol.mancala.domain.model.player.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Game {
    private UUID gameId;
    private List<Player> players;
    private GameSetting gameSetting;
    private Champion champion;

    public Game(GameSetting gameSetting, List<Player> players) {
        this.gameId = UUID.randomUUID();
        this.players = players;
        this.gameSetting = gameSetting;
    }
}
