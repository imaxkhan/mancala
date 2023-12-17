package com.bol.mancala.presentation.constants;

public abstract class GameRestApi {
    public static final String GAME_SETUP = "/games";
    public static final String PLAYER_RANDOM_PICK = "/games/{gameId}/dices";
    public static final String GAME_PLAY = "/games/{gameId}/actions";
}
