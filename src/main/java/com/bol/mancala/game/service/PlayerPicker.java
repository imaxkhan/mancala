package com.bol.mancala.game.service;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.PlayerPickResultDto;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.domain.model.player.Player;

import java.util.UUID;

public interface PlayerPicker {

    PlayerPickResultDto findFirstPlayerTurn(UUID gameId) throws CustomException;

    Game checkGameStatus(UUID gameId) throws CustomException;

    Player checkPlayer(Game game) throws CustomException;

    void updateGameSetup(Game game, Player firstPlayer);
}
